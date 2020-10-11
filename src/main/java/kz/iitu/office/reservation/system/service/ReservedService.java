package kz.iitu.office.reservation.system.service;

import kz.iitu.office.reservation.system.model.ReservedRooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReservedService {

    @Autowired
    RestTemplate restTemplate;

    public ReservedRooms[] getAllResRoom() {
        return restTemplate.getForEntity("http://localhost:8082/reserves", ReservedRooms[].class).getBody();
    }

    public void addReserve(ReservedRooms reserve) {
        System.out.println(reserve);
        restTemplate.postForEntity("http://localhost:8082/reserves/add", reserve, ReservedRooms.class);
    }

    public void removeReserve(Long id) {
        restTemplate.delete("http://localhost:8082/reserves/delete/" + id);
    }


    public void updateReserve(Long id, ReservedRooms reserve) {
        restTemplate.put("http://localhost:8082/reserves/update/" + id, reserve);
    }

    public ReservedRooms[] getReservesByRoom(String num) {
        return restTemplate.getForEntity("http://localhost:8082/reserves/" + num, ReservedRooms[].class).getBody();
    }

}
