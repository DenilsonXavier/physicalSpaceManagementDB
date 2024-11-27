package com.ucsal.physicalSpaceManagement.holiday;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucsal.physicalSpaceManagement.holiday.dto.HolidayDTO;
import com.ucsal.physicalSpaceManagement.holiday.entities.Holiday;

@Service
public class HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Holiday createHoliday(HolidayDTO holidayDTO) {
        Holiday holiday = modelMapper.map(holidayDTO, Holiday.class);
        return holidayRepository.save(holiday);
    }
}
