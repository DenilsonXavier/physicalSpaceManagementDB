package com.ucsal.physicalSpaceManagement.holiday;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucsal.physicalSpaceManagement.holiday.entities.Holiday;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    boolean existsByHolidayDate(LocalDate holidayDate);

}
