package technologia.eduflex.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technologia.eduflex.dto.EvaluationResponse;
import technologia.eduflex.models.Evaluation;
import technologia.eduflex.services.evaluation.EvaluationService;

import java.util.List;

@RestController
@RequestMapping("/evaluation")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;

    @PostMapping
    public ResponseEntity<EvaluationResponse> save(@RequestBody Evaluation evaluation) {
        EvaluationResponse response = evaluationService.save(evaluation);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/name/{name}")
    public ResponseEntity<EvaluationResponse> findByIdAndName(@PathVariable Long id, @PathVariable String name) {
        EvaluationResponse response = evaluationService.findByIdAndName(id, name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/nameNot/{name}")
    public ResponseEntity<EvaluationResponse> findByIdAndNameNot(@PathVariable Long id, @PathVariable String name) {
        EvaluationResponse response = evaluationService.findByIdAndNameNot(id, name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<EvaluationResponse> findByName(@PathVariable String name) {
        EvaluationResponse response = evaluationService.findByName(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/nameNot/{name}")
    public ResponseEntity<EvaluationResponse> findByNameNot(@PathVariable String name) {
        EvaluationResponse response = evaluationService.findByNameNot(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EvaluationResponse>> findAll() {
        List<EvaluationResponse> responses = evaluationService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluationResponse> update(@PathVariable Long id, @RequestBody Evaluation evaluation) {
        evaluation.setId(id);
        EvaluationResponse response = evaluationService.update(evaluation);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        evaluationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

