package kz.iitu.office.reservation.system.controller;

import kz.iitu.office.reservation.system.model.Employee;
import kz.iitu.office.reservation.system.service.EmployeeInfoService;
import kz.iitu.office.reservation.system.service.ReservedRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeInfoController {

    @Autowired
    private EmployeeInfoService employeeInfoService;
    @Autowired
    private ReservedRoomService reservedRoomService;

    @GetMapping("/")
    public Employee[] getAllEmployees(){
       return employeeInfoService.getEmployeesInfo();
    }
}
