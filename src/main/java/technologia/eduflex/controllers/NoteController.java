package technologia.eduflex.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technologia.eduflex.dto.NoteResponse;
import technologia.eduflex.models.Etudiant;
import technologia.eduflex.models.Evaluation;
import technologia.eduflex.models.Note;
import technologia.eduflex.services.note.NoteService;

import java.util.List;

@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<NoteResponse> save(@RequestBody Note note) {
        NoteResponse response = noteService.save(note);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/note/{note}")
    public ResponseEntity<NoteResponse> findByIdAndNote(@PathVariable Long id, @PathVariable String note) {
        NoteResponse response = noteService.findByIdAndNote(id, note);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/noteNot/{note}")
    public ResponseEntity<NoteResponse> findByIdAndNoteNot(@PathVariable Long id, @PathVariable String note) {
        NoteResponse response = noteService.findByIdAndNoteNot(id, note);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/etudiant/{etudiant}/evaluation/{evaluation}")
    public ResponseEntity<NoteResponse> findByIdAndEtudiantAndEvaluation(
            @PathVariable Long id,
            @PathVariable Etudiant etudiant,
            @PathVariable Evaluation evaluation) {
        NoteResponse response = noteService.findByIdAndEtudiantAndEvaluation(id, etudiant, evaluation);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<NoteResponse>> findAll() {
        List<NoteResponse> responses = noteService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> update(@PathVariable Long id, @RequestBody Note note) {
        note.setId(id);
        NoteResponse response = noteService.update(note);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        noteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
