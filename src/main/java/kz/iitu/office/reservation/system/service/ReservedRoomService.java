package kz.iitu.office.reservation.system.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import kz.iitu.office.reservation.system.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReservedRoomService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(
            fallbackMethod = "getUserBooksFallback",
            threadPoolKey = "getUserBooks",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "100"),
                    @HystrixProperty(name = "maximumSize", value = "120"),
                    @HystrixProperty(name = "maxQueueSize", value = "50"),
                    @HystrixProperty(name = "allowMaximumSizeToDivergeFromCoreSize", value = "true"),
            }
    )
    public Employee getEmployeesInfo() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return restTemplate.exchange("http://employee-info/api/v1/employees", HttpMethod.GET,
                new HttpEntity<>(headers), Employee.class).getBody();
    }

}
