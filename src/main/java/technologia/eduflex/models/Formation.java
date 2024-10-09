package technologia.eduflex.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Name;
    private LocalDate startAt = LocalDate.now();
    private LocalDate endAt = LocalDate.now();

    @OneToMany
    private Evaluation evaluation;
}
