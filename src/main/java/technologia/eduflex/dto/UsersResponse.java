package technologia.eduflex.dto;

import lombok.*;
import technologia.eduflex.enums.Statut;

import java.util.List;


@Builder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UsersResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<RoleResponse> roles;
    private Statut statut;
}
