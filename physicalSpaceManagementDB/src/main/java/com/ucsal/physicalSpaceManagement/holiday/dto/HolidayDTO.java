package com.ucsal.physicalSpaceManagement.holiday.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class HolidayDTO {
    private LocalDate holidayDate;
    private String description;
}
