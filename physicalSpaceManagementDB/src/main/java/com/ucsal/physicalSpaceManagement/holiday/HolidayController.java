package com.ucsal.physicalSpaceManagement.holiday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucsal.physicalSpaceManagement.holiday.dto.HolidayDTO;
import com.ucsal.physicalSpaceManagement.holiday.entities.Holiday;

@RestController
@RequestMapping("/holidays")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @PostMapping
    public ResponseEntity<Holiday> createHoliday(@RequestBody HolidayDTO holidayDTO) {
        Holiday holiday = holidayService.createHoliday(holidayDTO);

        return new ResponseEntity<>(holiday, HttpStatus.CREATED);
    }
}
