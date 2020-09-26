package kz.iitu.office.reservation.system.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Role {
    private Long id;
    private String name;
    private List<Employee> employees;
}
