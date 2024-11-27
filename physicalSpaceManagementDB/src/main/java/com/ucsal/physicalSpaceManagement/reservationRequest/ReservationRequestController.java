package com.ucsal.physicalSpaceManagement.reservationRequest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucsal.physicalSpaceManagement.reservationRequest.entities.ReservationRequest;

@RestController
@RequestMapping("/reservation-requests")
public class ReservationRequestController {

    @Autowired
    private ReservationRequestService reservationRequestService;

    @PostMapping
    public ResponseEntity<ReservationRequest> createReservationRequest(
            @RequestBody Map<String, Object> body) {

        Long requesterId = Long.valueOf(body.get("requesterId").toString());
        Long spaceId = Long.valueOf(body.get("spaceId").toString());
        String reservationDateStr = body.get("reservationDate").toString();
        String startTimeStr = body.get("startTime").toString();
        String endTimeStr = body.get("endTime").toString();

        LocalDate reservationDate = LocalDate.parse(reservationDateStr);
        LocalTime startTime = LocalTime.parse(startTimeStr);
        LocalTime endTime = LocalTime.parse(endTimeStr);

        ReservationRequest reservationRequest = reservationRequestService.createReservationRequest(
                requesterId, spaceId, reservationDate, startTime, endTime);

        return new ResponseEntity<>(reservationRequest, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReservationRequest>> getAllReservationRequests() {
        List<ReservationRequest> reservationRequests = reservationRequestService.getAllReservationRequests();
        return new ResponseEntity<>(reservationRequests, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationRequest>> getReservationRequestsByUserId(@PathVariable Long userId) {
        List<ReservationRequest> reservationRequests = reservationRequestService.getReservationRequestsByUserId(userId);
        return new ResponseEntity<>(reservationRequests, HttpStatus.OK);
    }
}
