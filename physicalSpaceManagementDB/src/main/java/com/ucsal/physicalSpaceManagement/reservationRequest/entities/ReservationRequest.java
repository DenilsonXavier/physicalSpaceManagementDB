package com.ucsal.physicalSpaceManagement.reservationRequest.entities;

import com.ucsal.physicalSpaceManagement.user.entities.User;
import com.ucsal.physicalSpaceManagement.physicalSpace.entities.PhysicalSpace;
import com.ucsal.physicalSpaceManagement.reservationRequest.enums.RequestStatus;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "RESERVATION_REQUEST")
@Data
@NoArgsConstructor
public class ReservationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;

    @ManyToOne
    @JoinColumn(name = "requester_id", referencedColumnName = "user_id", nullable = false)
    private User requester;

    @ManyToOne
    @JoinColumn(name = "space_id", referencedColumnName = "space_id", nullable = false)
    private PhysicalSpace space;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'PENDING'")
    private RequestStatus status = RequestStatus.PENDING;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    public ReservationRequest(LocalDate reservationDate, LocalTime startTime, LocalTime endTime) {
        this.reservationDate = reservationDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
