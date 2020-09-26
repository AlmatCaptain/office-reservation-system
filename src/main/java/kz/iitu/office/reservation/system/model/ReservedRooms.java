package kz.iitu.office.reservation.system.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class ReservedRooms {
    private Long id;
    private Long employeeId;
    private String roomNumber;
    private LocalDate date;
    private LocalDate toDate;
}
