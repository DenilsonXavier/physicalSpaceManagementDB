package com.ucsal.physicalSpaceManagement.evaluation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucsal.physicalSpaceManagement.evaluation.entities.Evaluation;
import com.ucsal.physicalSpaceManagement.evaluation.enums.EvaluationStatus;
import com.ucsal.physicalSpaceManagement.reservationRequest.ReservationRequestRepository;
import com.ucsal.physicalSpaceManagement.reservationRequest.entities.ReservationRequest;
import com.ucsal.physicalSpaceManagement.user.UserRepository;
import com.ucsal.physicalSpaceManagement.user.Enums.UserType;
import com.ucsal.physicalSpaceManagement.user.entities.User;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EvaluationService {

        @Autowired
        private EvaluationRepository evaluationRepository;

        @Autowired
        private ReservationRequestRepository reservationRequestRepository;

        @Autowired
        private UserRepository userRepository;

        public Evaluation createEvaluation(Long requestId, Long evaluatorId, EvaluationStatus evaluationStatus,
                        String comments) {
                ReservationRequest reservationRequest = reservationRequestRepository.findById(requestId)
                                .orElseThrow(() -> new EntityNotFoundException("ReservationRequest not found"));
                User evaluator = userRepository.findById(evaluatorId)
                                .orElseThrow(() -> new EntityNotFoundException("Evaluator not found"));
                if (evaluator.getUserType() != UserType.MANAGER) {
                        throw new SecurityException("Somente usuários do tipo MANAGER podem validar avaliações.");
                }
                boolean evaluationExists = evaluationRepository.existsByReservationRequestId(requestId);
                if (evaluationExists) {
                        throw new IllegalStateException("Já existe uma avaliação para essa solicitação de reserva.");
                }

                Evaluation evaluation = new Evaluation(reservationRequest, evaluator, evaluationStatus, comments);
                return evaluationRepository.save(evaluation);
        }

        public Evaluation getEvaluationById(Long id) {
                return evaluationRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Evaluation not found"));
        }

        public List<Evaluation> getAllEvaluations(Optional<EvaluationStatus> status) {
                if (status.isPresent()) {
                        return evaluationRepository.findByEvaluationStatus(status.get());
                } else {
                        return evaluationRepository.findAll();
                }
        }

        public List<Evaluation> getEvaluationsByUserId(Long userId) {
                return evaluationRepository.findByEvaluatorId(userId);
        }
}
