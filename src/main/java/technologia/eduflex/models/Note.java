package technologia.eduflex.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String note;

    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private Evaluation evaluation;

    @ManyToOne
    private Module module;

    public String  getValue() {
        return "Valeur est........";
    }

    public String  getEtudiantId() {
        return "ID Est......";
    }

    public String getEvaluationId() {
        return "Evaluation de........";
    }
}


