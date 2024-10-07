package technologia.eduflex.dto;


import lombok.*;
import technologia.eduflex.enums.Statut;
import technologia.eduflex.models.Role;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthentificationResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Role> roles;
    private LocalDateTime createdAt;
    private Statut statut;
    private String accessToken;

    public <E> AuthentificationResponse(Long id, String firstName, String lastName, String email, ArrayList<E> es, Statut statut, String token) {
    }
}
