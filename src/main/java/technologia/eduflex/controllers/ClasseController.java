package technologia.eduflex.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technologia.eduflex.dto.ClasseResponse;
import technologia.eduflex.models.Classe;
import technologia.eduflex.services.classe.ClasseService;

import java.util.List;

@RestController
@RequestMapping("/classe")
@RequiredArgsConstructor
public class ClasseController {

    private final ClasseService classeService;


    @PostMapping
    public ResponseEntity<ClasseResponse> save(@RequestBody Classe classe) {
        ClasseResponse response = classeService.save(classe);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/name/{name}")
    public ResponseEntity<ClasseResponse> findByIdAndName(@PathVariable Long id, @PathVariable String name) {
        ClasseResponse response = classeService.findByIdAndName(id, name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/nameNot/{name}")
    public ResponseEntity<ClasseResponse> findByIdAndNameNot(@PathVariable Long id, @PathVariable String name) {
        ClasseResponse response = classeService.findByIdAndNameNot(id, name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ClasseResponse> findByName(@PathVariable String name) {
        ClasseResponse response = classeService.findByName(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/nameNot/{name}")
    public ResponseEntity<ClasseResponse> findByNameNot(@PathVariable String name) {
        ClasseResponse response = classeService.findByNameNot(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ClasseResponse>> findAll() {
        List<ClasseResponse> responses = classeService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClasseResponse> update(@PathVariable Long id, @RequestBody Classe classe) {
        classe.setId(id);
        ClasseResponse response = classeService.update(classe);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        classeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
