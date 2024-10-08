package technologia.eduflex.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EtudiantResponse {
    private Long id;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String classe;
    private String tuteur;
}
