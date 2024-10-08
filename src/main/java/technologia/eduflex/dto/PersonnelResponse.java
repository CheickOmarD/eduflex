package technologia.eduflex.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonnelResponse {
    private Long id;
    private String nom;
    private String prenom;
    private String phoneNumber;
    private String email;
    private String password;

}

