package technologia.eduflex.dto;

import jakarta.persistence.ManyToOne;
import technologia.eduflex.models.Etudiant;
import technologia.eduflex.models.Evaluation;
import technologia.eduflex.models.Module;

public class NoteResponse {
    private Long id;
    private String note;
    private Etudiant etudiant;
    private Evaluation evaluation;
    private Module module;

    public NoteResponse(Long id, String value, String etudiantId, String evaluationId) {
    }
}
