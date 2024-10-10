package technologia.eduflex.services.note;

import technologia.eduflex.dto.NoteResponse;
import technologia.eduflex.models.Etudiant;
import technologia.eduflex.models.Evaluation;
import technologia.eduflex.models.Note;

import java.util.List;

public interface NoteService {
    NoteResponse save(Note note);

    NoteResponse findByIdAndNote(Long id, String note);

    NoteResponse findByIdAndNoteNot(Long id, String note);


    List<NoteResponse> findAll();

    NoteResponse findByIdAndEtudiantAndEvaluation(Long id, Etudiant etudiant, Evaluation evaluation);

    NoteResponse update(Note note);

    void delete(Long id);
}
