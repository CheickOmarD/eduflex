package technologia.eduflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import technologia.eduflex.models.Classe;
import technologia.eduflex.models.Etudiant;
import technologia.eduflex.models.Evaluation;
import technologia.eduflex.models.Note;

public interface NoteRepository extends JpaRepository<Note,Long> {
    Note findByIdAndNote(Long id, String note);
    Note findByIdAndNoteNot(Long id,String note);
    Note findByIdAndEtudiantAndEvaluation(Long id, Etudiant etudiant, Evaluation evaluation);
}
