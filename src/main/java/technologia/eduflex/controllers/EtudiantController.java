package technologia.eduflex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technologia.eduflex.dto.EtudiantResponse;
import technologia.eduflex.exceptions.AlreadyExistException;
import technologia.eduflex.exceptions.NotFoundException;
import technologia.eduflex.models.Etudiant;
import technologia.eduflex.services.Etudiant.EtudiantServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantServiceImpl etudiantService;

    @PostMapping
    public ResponseEntity<EtudiantResponse> creerEtudiant(@RequestBody Etudiant etudiant) {
        try {
            EtudiantResponse etudiantResponse = etudiantService.creerEtudiant(etudiant);
            return new ResponseEntity<>(etudiantResponse, HttpStatus.CREATED);
        } catch (AlreadyExistException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EtudiantResponse> modifierEtudiant(@PathVariable Long id, @RequestBody Etudiant etudiantDetails) {
        try {
            EtudiantResponse etudiantResponse = etudiantService.modifierEtudiant(id, etudiantDetails);
            return new ResponseEntity<>(etudiantResponse, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<EtudiantResponse>> visualiserEtudiants() {
        List<EtudiantResponse> etudiants = etudiantService.visualiserEtudiants();
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEtudiant(@PathVariable Long id) {
        try {
            etudiantService.supprimerEtudiant(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
