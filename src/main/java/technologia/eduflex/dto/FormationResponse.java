package technologia.eduflex.dto;

import technologia.eduflex.models.Evaluation;

import java.time.LocalDate;
import java.util.List;

public class FormationResponse {
    private Long id;
    private String Name;
    private LocalDate startAt = LocalDate.now();
    private LocalDate endAt = LocalDate.now();
    private List<Evaluation> evaluations;

    public FormationResponse(Long id, String name, String description) {
    }
}
