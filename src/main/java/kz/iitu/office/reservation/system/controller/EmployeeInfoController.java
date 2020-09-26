package kz.iitu.office.reservation.system.controller;

import kz.iitu.office.reservation.system.model.Employee;
import kz.iitu.office.reservation.system.service.AclRoleService;
import kz.iitu.office.reservation.system.service.EmployeeInfoService;
import kz.iitu.office.reservation.system.service.ReservedRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeInfoController {

    @Autowired
    private EmployeeInfoService employeeInfoService;
    @Autowired
    private ReservedRoomService reservedRoomService;
    @Autowired
    private AclRoleService aclRoleService;

    @GetMapping("/")
    public Employee[] getAllEmployees(){
       return employeeInfoService.getEmployeesInfo();
    }

    @GetMapping("/{userId}/role")
    public ResponseEntity<?> getEmployeeRole(@PathVariable String userId) {
        return aclRoleService.getEmployeeRole(userId);
    }

}
