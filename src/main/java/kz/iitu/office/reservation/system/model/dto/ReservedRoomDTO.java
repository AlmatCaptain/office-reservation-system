package kz.iitu.office.reservation.system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ReservedRoomDTO {
    private Long employeeId;
    private String login;
    private String roomNumber;
    private String date;
    private String toDate;
}
