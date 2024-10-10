package technologia.eduflex.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technologia.eduflex.dto.EtablissementResponse;
import technologia.eduflex.models.Etablissement;
import technologia.eduflex.services.etablissement.EtablissementService;

import java.util.List;

@RestController
@RequestMapping("/etablissement")
@RequiredArgsConstructor
public class EtablissementController {

    private final EtablissementService etablissementService;

    @PostMapping
    public ResponseEntity<EtablissementResponse> save(@RequestBody Etablissement etablissement) {
        EtablissementResponse response = etablissementService.save(etablissement);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/name/{name}")
    public ResponseEntity<EtablissementResponse> findByIdAndName(@PathVariable Long id, @PathVariable String name) {
        EtablissementResponse response = etablissementService.findByIdAndName(id, name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/nameNot/{name}")
    public ResponseEntity<EtablissementResponse> findByIdAndNameNot(@PathVariable Long id, @PathVariable String name) {
        EtablissementResponse response = etablissementService.findByIdAndNameNot(id, name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<EtablissementResponse> findByName(@PathVariable String name) {
        EtablissementResponse response = etablissementService.findByName(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}/adress/{adress}")
    public ResponseEntity<EtablissementResponse> findByNameAndAdress(@PathVariable String name, @PathVariable String adress) {
        EtablissementResponse response = etablissementService.findByNameAndAdress(name, adress);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EtablissementResponse>> findAll() {
        List<EtablissementResponse> responses = etablissementService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EtablissementResponse> update(@PathVariable Long id, @RequestBody Etablissement etablissement) {
        etablissement.setId(id);
        EtablissementResponse response = etablissementService.update(etablissement);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        etablissementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
