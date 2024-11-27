package com.ucsal.physicalSpaceManagement.reservationRequest.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class ReservationRequestDTO {
    private Long requesterId;
    private Long spaceId;
    private LocalDate reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
}
