package com.ucsal.physicalSpaceManagement.evaluation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ucsal.physicalSpaceManagement.evaluation.entities.Evaluation;
import com.ucsal.physicalSpaceManagement.evaluation.enums.EvaluationStatus;
import com.ucsal.physicalSpaceManagement.user.Enums.UserType;
import com.ucsal.physicalSpaceManagement.user.entities.User;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    @Query("SELECT COUNT(e) > 0 FROM Evaluation e WHERE e.reservationRequest.id = :requestId")
    boolean existsByReservationRequestId(@Param("requestId") Long requestId);

    @Query("SELECT e FROM Evaluation e WHERE e.evaluator.id = :userId")
    List<Evaluation> findByEvaluatorId(@Param("userId") Long userId);

    public List<Evaluation> findByEvaluationStatus(EvaluationStatus status);

}
