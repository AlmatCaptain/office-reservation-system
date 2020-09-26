package kz.iitu.office.reservation.system.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class EmployeeList {
    private List<Employee> employees;

    public EmployeeList() {
        employees = new ArrayList<>();
    }
}