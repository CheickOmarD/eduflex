package technologia.eduflex.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technologia.eduflex.dto.EtudiantResponse;
import technologia.eduflex.models.Classe;
import technologia.eduflex.models.Etudiant;
import technologia.eduflex.services.Etudiant.EtudiantServiceImpl;

import java.util.Optional;

@RestController
@RequestMapping("/etudiants")
@RequiredArgsConstructor
public class EtudiantController {

    private final EtudiantServiceImpl etudiantService;

    @PostMapping
    public ResponseEntity<EtudiantResponse> createEtudiant(@RequestBody Etudiant etudiant) {
        EtudiantResponse response = etudiantService.save(etudiant);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable Long id, @RequestBody Etudiant updatedEtudiant) {
        Etudiant etudiant = etudiantService.update(id, updatedEtudiant);
        return ResponseEntity.ok(etudiant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Etudiant> deleteEtudiant(@PathVariable Long id, @RequestBody Etudiant etudiant) {
        etudiant.setId(id);
        Etudiant deletedEtudiant = etudiantService.delete(etudiant);
        return ResponseEntity.ok(deletedEtudiant);
    }

    @GetMapping("/search")
    public ResponseEntity<Etudiant> findByFirstNameAndLastNameAndClasse(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam Classe classe) {
        Optional<Etudiant> etudiantOpt = etudiantService.findByFirstNameAndLastnameAndClasse(firstName, lastName, classe);
        return etudiantOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
