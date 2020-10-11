package kz.iitu.office.reservation.system.controller;

import kz.iitu.office.reservation.system.model.EmployeeRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public EmployeeRole[] getAllRoles() {
        return restTemplate.getForEntity("http://localhost:8083/roles", EmployeeRole[].class).getBody();
    }
}
