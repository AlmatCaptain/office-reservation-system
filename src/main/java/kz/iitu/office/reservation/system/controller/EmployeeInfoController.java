package kz.iitu.office.reservation.system.controller;

import kz.iitu.office.reservation.system.model.Employee;
import kz.iitu.office.reservation.system.service.EmployeeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeInfoController {

    private final EmployeeInfoService employeeService;

    @Autowired
    public EmployeeInfoController(EmployeeInfoService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getListEmployee() {
        return employeeService.getListEmployee();
    }


    @PostMapping("/registration")
    public void addEmployee(@RequestBody Employee e) {
        employeeService.addEmployee(e);
    }

    @PutMapping("/admin/update/{id}")
    public void updateMember(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        employeeService.updateEmployee(id, employee);
    }

    @PatchMapping("/admin/update/{id}")
    public void updateUserName(@PathVariable Long id, @RequestParam String name) {
        employeeService.updateName(id, name);
    }

    @PatchMapping("/admin/role/{id}")
    public void updateUserRole(@PathVariable Long id, @RequestParam String role) {
        employeeService.updateRole(id, role);
    }

    @DeleteMapping("/admin/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @PostMapping("/admin/add")
    public void createEmployee(@RequestBody Employee e) {
        employeeService.addEmployee(e);
    }


}