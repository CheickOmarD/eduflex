package technologia.eduflex.dto;

import lombok.*;
import technologia.eduflex.models.Classe;

import java.time.LocalDate;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EtudiantResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate Birthday;
    private Classe classe;
    private String tuteur;
}
