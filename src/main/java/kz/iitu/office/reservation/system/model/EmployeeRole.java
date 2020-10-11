package kz.iitu.office.reservation.system.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EmployeeRole {
    private Long roleId;
    private Long employeeId;
    private String roleName;
}
