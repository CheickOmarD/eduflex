package technologia.eduflex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technologia.eduflex.dto.PersonnelResponse;
import technologia.eduflex.exceptions.AlreadyExistException;
import technologia.eduflex.exceptions.NotFoundException;
import technologia.eduflex.models.Personnel;
import technologia.eduflex.services.Personnel.PersonnelServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/personnels")
public class PersonnelController {

    @Autowired
    private PersonnelServiceImpl personnelService;

    @PostMapping
    public ResponseEntity<PersonnelResponse> creerPersonnel(@RequestBody Personnel personnel) {
        try {
            PersonnelResponse personnelResponse = personnelService.creerPersonnel(personnel);
            return new ResponseEntity<>(personnelResponse, HttpStatus.CREATED);
        } catch (AlreadyExistException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonnelResponse> modifierPersonnel(@PathVariable Long id, @RequestBody Personnel personnelDetails) {
        try {
            PersonnelResponse personnelResponse = personnelService.modifierPersonnel(id, personnelDetails);
            return new ResponseEntity<>(personnelResponse, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<PersonnelResponse>> visualiserPersonnel() {
        List<PersonnelResponse> personnels = personnelService.visualiserPersonnel();
        return new ResponseEntity<>(personnels, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerPersonnel(@PathVariable Long id) {
        try {
            personnelService.supprimerPersonnel(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
