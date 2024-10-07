package technologia.eduflex.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EtudiantResponse {
    private Long id;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String classe;
    private String tuteur;
}
