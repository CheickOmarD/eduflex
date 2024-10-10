package technologia.eduflex.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technologia.eduflex.dto.FormationResponse;
import technologia.eduflex.models.Formation;
import technologia.eduflex.services.formation.FormationService;

import java.util.List;

@RestController
@RequestMapping("/formation")
@RequiredArgsConstructor
public class FormationController {

    private final FormationService formationService;

    @PostMapping
    public ResponseEntity<FormationResponse> save(@RequestBody Formation formation) {
        FormationResponse response = formationService.save(formation);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/name/{name}")
    public ResponseEntity<FormationResponse> findByIdAndName(@PathVariable Long id, @PathVariable String name) {
        FormationResponse response = formationService.findByIdAndName(id, name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/nameNot/{name}")
    public ResponseEntity<FormationResponse> findByIdAndNameNot(@PathVariable Long id, @PathVariable String name) {
        FormationResponse response = formationService.findByIdAndNameNot(id, name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<FormationResponse> findByName(@PathVariable String name) {
        FormationResponse response = formationService.findByName(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/nameNot/{name}")
    public ResponseEntity<FormationResponse> findByNameNot(@PathVariable String name) {
        FormationResponse response = formationService.findByNameNot(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<FormationResponse>> findAll() {
        List<FormationResponse> responses = formationService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormationResponse> update(@PathVariable Long id, @RequestBody Formation formation) {
        formation.setId(id);
        FormationResponse response = formationService.update(formation);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        formationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

