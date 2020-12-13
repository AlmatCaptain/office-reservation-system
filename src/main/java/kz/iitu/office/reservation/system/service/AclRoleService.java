package kz.iitu.office.reservation.system.service;

import kz.iitu.office.reservation.system.model.EmployeeRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AclRoleService {

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<?> getEmployeeRole(String userId) {
        return restTemplate.getForEntity("http://office-acl-api:8083/api/v1/roles/" + userId, EmployeeRole.class);
    }
}
