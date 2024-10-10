package technologia.eduflex.services.note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import technologia.eduflex.dto.NoteResponse;
import technologia.eduflex.exceptions.NotFoundException;
import technologia.eduflex.models.Note;
import technologia.eduflex.models.Etudiant;
import technologia.eduflex.models.Evaluation;
import technologia.eduflex.repositories.NoteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Override
    public NoteResponse save(Note note) {
        if (note == null) {
            throw new IllegalArgumentException("La note ne peut pas être null.");
        }
        Note savedNote = noteRepository.save(note);
        return mapToResponse(savedNote);
    }

    @Override
    public NoteResponse findByIdAndNote(Long id, String note) {
        Note foundNote = noteRepository.findByIdAndNote(id, note);
        if (foundNote == null) {
            throw new NotFoundException("Note avec l'ID " + id + " et la note '" + note + "' non trouvée.");
        }
        return mapToResponse(foundNote);
    }

    @Override
    public NoteResponse findByIdAndNoteNot(Long id, String note) {
        Note foundNote = noteRepository.findByIdAndNoteNot(id, note);
        if (foundNote == null) {
            throw new NotFoundException("Note avec l'ID " + id + " et ne contenant pas la note '" + note + "' non trouvée.");
        }
        return mapToResponse(foundNote);
    }

    @Override
    public List<NoteResponse> findAll() {
        return noteRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public NoteResponse findByIdAndEtudiantAndEvaluation(Long id, Etudiant etudiant, Evaluation evaluation) {
        Note foundNote = noteRepository.findByIdAndEtudiantAndEvaluation(id, etudiant, evaluation);
        if (foundNote == null) {
            throw new NotFoundException("Note avec l'ID " + id + ", étudiant " + etudiant.getId() + " et évaluation " + evaluation.getId() + " non trouvée.");
        }
        return mapToResponse(foundNote);
    }

    @Override
    public NoteResponse update(Note note) {
        if (note == null || noteRepository.findById(note.getId()) == null) {
            throw new NotFoundException("Note avec l'ID " + (note != null ? note.getId() : "inconnu") + " non trouvée pour la mise à jour.");
        }
        Note updatedNote = noteRepository.save(note);
        return mapToResponse(updatedNote);
    }

    @Override
    public void delete(Long id) {
        Optional<Note> foundNote = noteRepository.findById(id);
        if (foundNote == null) {
            throw new NotFoundException("Note avec l'ID " + id + " non trouvée pour la suppression.");
        }
        noteRepository.deleteById(id);
    }

    private NoteResponse mapToResponse(Note note) {
        return new NoteResponse(
                note.getId(),
                note.getValue(),  // Remplacez par le champ approprié
                note.getEtudiantId(),  // Remplacez par le champ approprié
                note.getEvaluationId()  // Remplacez par le champ approprié
        );
    }
}
