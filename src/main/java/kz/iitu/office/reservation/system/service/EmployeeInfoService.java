package kz.iitu.office.reservation.system.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import kz.iitu.office.reservation.system.model.Employee;
import kz.iitu.office.reservation.system.model.EmployeeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmployeeInfoService {

    @Autowired
    RestTemplate restTemplate;

    public Employee[] getEmployeesInfo() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return restTemplate.getForEntity("http://localhost:8081/api/v1/employee/",
                Employee[].class).getBody();
    }
}
