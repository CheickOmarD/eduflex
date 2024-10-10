package technologia.eduflex.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technologia.eduflex.dto.TuteurResponse;
import technologia.eduflex.models.Tuteur;
import technologia.eduflex.services.tuteur.TuteurService;

import java.util.List;

@RestController
@RequestMapping("/tuteur")
@RequiredArgsConstructor
public class TuteurController {

    private final TuteurService tuteurService;

    @PostMapping
    public ResponseEntity<TuteurResponse> save(@RequestBody Tuteur tuteur) {
        TuteurResponse response = tuteurService.save(tuteur);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/firstname/{firstname}")
    public ResponseEntity<TuteurResponse> findByIdAndFirstname(@PathVariable Long id, @PathVariable String firstname) {
        TuteurResponse response = tuteurService.findByIdAndFirstname(id, firstname);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/firstname/{firstname}/lastname/{lastname}")
    public ResponseEntity<TuteurResponse> findByFirstNameAndLastName(@PathVariable String firstname, @PathVariable String lastname) {
        TuteurResponse response = tuteurService.findByFirstNameAndLastName(firstname, lastname);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/firstname/{firstname}")
    public ResponseEntity<TuteurResponse> findByIdAndFistname(@PathVariable Long id, @PathVariable String firstname) {
        TuteurResponse response =  tuteurService.findByIdAndFistname(id, firstname);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TuteurResponse>> findAll() {
        List<TuteurResponse> responses = tuteurService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TuteurResponse> update(@PathVariable Long id, @RequestBody Tuteur tuteur) {
        tuteur.setId(id);
        TuteurResponse response = tuteurService.update(tuteur);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tuteurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}