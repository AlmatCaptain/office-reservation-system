package kz.iitu.office.reservation.system.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import kz.iitu.office.reservation.system.model.Employee;
import kz.iitu.office.reservation.system.model.ReservedRooms;
import kz.iitu.office.reservation.system.model.dto.ReservedRoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservedService {

    @Autowired
    RestTemplate restTemplate;

    private final KafkaProducerService producerService;
    private final EmployeeInfoService employeeInfoService;

    public ReservedService(KafkaProducerService producerService, EmployeeInfoService employeeInfoService) {
        this.producerService = producerService;
        this.employeeInfoService = employeeInfoService;
    }

    @HystrixCommand(
            fallbackMethod = "getResRoomFallback",
            threadPoolKey = "getAllResRoom",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maxQueueSize", value="50")
            }
    )
    public List<ReservedRooms> getAllResRoom() {
        return restTemplate.exchange("http://office-reserved-room:8082/reserves", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ReservedRooms>>() {}).getBody();
    }

    public String addReserve(ReservedRooms reserve) {
        Employee e = employeeInfoService.getEmployeeById(reserve.getEmployeeId());
        ReservedRoomDTO dto = new ReservedRoomDTO(
                reserve.getEmployeeId(),
                e.getUsername(),
                reserve.getRoomNumber(),
                reserve.getDate(),
                reserve.getToDate()
        );
        restTemplate.postForEntity("http://office-reserved-room:8082/reserves/add", reserve, ReservedRooms.class);
        return this.producerService.sendReserveRequest(dto);
    }

    public void removeReserve(Long id) {
        restTemplate.delete("http://office-reserved-room:8082/reserves/delete/" + id);
    }


    public void updateReserve(Long id, ReservedRooms reserve) {
        restTemplate.put("http://office-reserved-room:8082/reserves/update/" + id, reserve);
    }

    @HystrixCommand(
            fallbackMethod = "getResRoomFallback",
            threadPoolKey = "getReservesByRoom",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maxQueueSize", value="50")
            }
    )
    public List<ReservedRooms> getReservesByRoom(String num) {
        return restTemplate.exchange("http://office-reserved-room:8082/reserves" + num, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ReservedRooms>>() {}).getBody();
    }

    public List<ReservedRooms> getResRoomFallback() {
        List<ReservedRooms> list = new ArrayList<>();
        ReservedRooms r = new ReservedRooms();
        r.setId(-1L);
        r.setDate(LocalDate.now().toString());
        r.setRoomNumber("-1");
        r.setToDate(LocalDate.now().toString());
        list.add(r);
        return list;
    }

}
