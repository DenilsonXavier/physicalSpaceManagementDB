package com.ucsal.physicalSpaceManagement.evaluation;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ucsal.physicalSpaceManagement.evaluation.entities.Evaluation;
import com.ucsal.physicalSpaceManagement.evaluation.enums.EvaluationStatus;

@RestController
@RequestMapping("/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping
    public ResponseEntity<Evaluation> createEvaluation(@RequestBody Map<String, Object> body) {
        Long requestId = Long.valueOf(body.get("requestId").toString());
        Long evaluatorId = Long.valueOf(body.get("evaluatorId").toString());
        String evaluationStatusStr = body.get("evaluationStatus").toString();
        String comments = body.get("comments") != null ? body.get("comments").toString() : null;

        EvaluationStatus evaluationStatus = EvaluationStatus.valueOf(evaluationStatusStr);

        Evaluation evaluation = evaluationService.createEvaluation(requestId, evaluatorId, evaluationStatus, comments);

        return new ResponseEntity<>(evaluation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluation> getEvaluationById(@PathVariable Long id) {
        Evaluation evaluation = evaluationService.getEvaluationById(id);
        return new ResponseEntity<>(evaluation, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Evaluation>> getAllEvaluations(@RequestParam Optional<EvaluationStatus> status) {
        List<Evaluation> evaluations = evaluationService.getAllEvaluations(status);
        return ResponseEntity.ok(evaluations);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Evaluation>> getEvaluationsByUserId(@PathVariable Long userId) {
        List<Evaluation> evaluations = evaluationService.getEvaluationsByUserId(userId);
        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }
}
