package kz.iitu.office.reservation.system.service;

import kz.iitu.office.reservation.system.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class EmployeeInfoService {

    @Autowired
    RestTemplate restTemplate;

    public Employee[] getEmployeesInfo() {
        return restTemplate.getForEntity("http://localhost:8081/api/v1/employee/",
                Employee[].class).getBody();
    }
}
