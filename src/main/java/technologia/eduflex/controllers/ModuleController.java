package technologia.eduflex.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technologia.eduflex.dto.ModuleResponse;
import technologia.eduflex.services.module.ModuleService;

import java.util.List;

@RestController
@RequestMapping("/module")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    @PostMapping
    public ResponseEntity<ModuleResponse> save(@RequestBody Module module) {
        ModuleResponse response = moduleService.save(module);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuleResponse> findById(@PathVariable Long id) {
        ModuleResponse response = moduleService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ModuleResponse>> findAll() {
        List<ModuleResponse> responses = moduleService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModuleResponse> update(@PathVariable Long id, @RequestBody Module module) {
        ModuleResponse response = moduleService.update(module);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        moduleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

