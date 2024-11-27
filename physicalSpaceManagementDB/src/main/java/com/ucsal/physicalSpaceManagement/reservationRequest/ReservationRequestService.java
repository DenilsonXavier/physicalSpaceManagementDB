package com.ucsal.physicalSpaceManagement.reservationRequest;

import com.ucsal.physicalSpaceManagement.holiday.HolidayRepository;
import com.ucsal.physicalSpaceManagement.physicalSpace.PhysicalSpaceRepository;
import com.ucsal.physicalSpaceManagement.physicalSpace.entities.PhysicalSpace;
import com.ucsal.physicalSpaceManagement.reservationRequest.dto.ReservationRequestDTO;
import com.ucsal.physicalSpaceManagement.reservationRequest.entities.ReservationRequest;
import com.ucsal.physicalSpaceManagement.user.UserRepository;
import com.ucsal.physicalSpaceManagement.user.entities.User;

import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationRequestService {

    @Autowired
    private ReservationRequestRepository reservationRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HolidayRepository holidayRepository;

    @Autowired
    private PhysicalSpaceRepository physicalSpaceRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ReservationRequest createReservationRequest(ReservationRequestDTO reservationRequestDTO) {
        ReservationRequest reservationRequest = modelMapper.map(reservationRequestDTO, ReservationRequest.class);

        return reservationRequestRepository.save(reservationRequest);
    }

    public ReservationRequest createReservationRequest(Long requesterId, Long spaceId, LocalDate reservationDate,
            LocalTime startTime,
            LocalTime endTime) {

        if (isHoliday(reservationDate)) {
            throw new IllegalArgumentException("A reserva não pode ser feita em um feriado.");
        }

        if (startTime.isBefore(LocalTime.of(7, 0))) {
            throw new IllegalArgumentException("O horário de início não pode ser antes das 07:00.");
        }

        if (endTime.isAfter(LocalTime.of(22, 0))) {
            throw new IllegalArgumentException("O horário de término não pode ser após as 22:00.");
        }

        if (hasTimeConflict(spaceId, reservationDate, startTime, endTime)) {
            throw new IllegalArgumentException("Já existe uma reserva no mesmo espaço e horário.");
        }

        User requester = userRepository.findById(requesterId)
                .orElseThrow(() -> new EntityNotFoundException("Requester not found"));
        PhysicalSpace space = physicalSpaceRepository.findById(spaceId)
                .orElseThrow(() -> new EntityNotFoundException("PhysicalSpace not found"));

        ReservationRequest newReservationRequest = new ReservationRequest();
        newReservationRequest.setRequester(requester);
        newReservationRequest.setSpace(space);
        newReservationRequest.setReservationDate(reservationDate);
        newReservationRequest.setStartTime(startTime);
        newReservationRequest.setEndTime(endTime);

        return reservationRequestRepository.save(newReservationRequest);
    }

    private boolean isHoliday(LocalDate date) {
        return holidayRepository.existsByHolidayDate(date);
    }

    private boolean hasTimeConflict(Long spaceId, LocalDate reservationDate, LocalTime startTime, LocalTime endTime) {
        List<ReservationRequest> conflictingReservations = reservationRequestRepository
                .findBySpaceIdAndReservationDate(spaceId, reservationDate);

        for (ReservationRequest reservation : conflictingReservations) {
            if ((startTime.isBefore(reservation.getEndTime()) && endTime.isAfter(reservation.getStartTime()))) {
                return true;
            }
        }
        return false;
    }

    public List<ReservationRequest> getAllReservationRequests() {
        return reservationRequestRepository.findAll();
    }

    public List<ReservationRequest> getReservationRequestsByUserId(Long userId) {
        return reservationRequestRepository.findByRequesterId(userId);
    }
}
