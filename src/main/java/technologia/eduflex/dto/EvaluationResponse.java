package technologia.eduflex.dto;

import technologia.eduflex.models.Etudiant;
import technologia.eduflex.models.Module;
import technologia.eduflex.models.Note;

import java.time.LocalDateTime;
import java.util.List;

public class EvaluationResponse {
    private Long id;
    private String Name;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime endDate = LocalDateTime.now();
    private Etudiant etudiant;
    private List<Module> modules;
    private List<Note> notes;



    public EvaluationResponse(Long id, String name, List<Module> modules, LocalDateTime createdAt, LocalDateTime endDate) {
    }
}
