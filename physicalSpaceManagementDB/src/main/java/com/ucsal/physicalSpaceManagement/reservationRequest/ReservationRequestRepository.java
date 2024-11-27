package com.ucsal.physicalSpaceManagement.reservationRequest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ucsal.physicalSpaceManagement.reservationRequest.entities.ReservationRequest;

@Repository
public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Long> {
    @Query("SELECT r FROM ReservationRequest r WHERE r.space.id = :spaceId AND r.reservationDate = :reservationDate")
    List<ReservationRequest> findBySpaceIdAndReservationDate(@Param("spaceId") Long spaceId,
            @Param("reservationDate") LocalDate reservationDate);

    @Query("SELECT r FROM ReservationRequest r WHERE r.requester.id = :requesterId")
    List<ReservationRequest> findByRequesterId(@Param("requesterId") Long requesterId);

}
