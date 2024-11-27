package com.ucsal.physicalSpaceManagement.evaluation.entities;

import java.time.LocalDateTime;

import com.ucsal.physicalSpaceManagement.evaluation.enums.EvaluationStatus;
import com.ucsal.physicalSpaceManagement.reservationRequest.entities.ReservationRequest;
import com.ucsal.physicalSpaceManagement.user.entities.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EVALUATION")
@Data
@NoArgsConstructor
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_id")
    private Long evaluationId;

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "request_id", nullable = false)
    private ReservationRequest reservationRequest;

    @ManyToOne
    @JoinColumn(name = "evaluator_id", referencedColumnName = "user_id", nullable = false)
    private User evaluator;

    @Column(name = "evaluation_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime evaluationDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "evaluation_status", nullable = false)
    private EvaluationStatus evaluationStatus;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    public Evaluation(ReservationRequest reservationRequest, User evaluator, EvaluationStatus evaluationStatus,
            String comments) {
        this.reservationRequest = reservationRequest;
        this.evaluator = evaluator;
        this.evaluationStatus = evaluationStatus;
        this.comments = comments;
    }
}
