package technologia.eduflex.models;

import jakarta.persistence.*;
import lombok.*;

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
}
