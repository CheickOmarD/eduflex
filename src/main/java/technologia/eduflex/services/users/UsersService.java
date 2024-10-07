package technologia.eduflex.services.users;

import technologia.eduflex.dto.UsersResponse;
import technologia.eduflex.enums.Statut;
import technologia.eduflex.models.Users;

import java.util.List;

public interface UsersService {
    UsersResponse save(Users users);

    UsersResponse findByIdAndStatut(Long id, Statut statut);

    List<UsersResponse> findByStatut(Statut statut);

    List<UsersResponse> findByStatutNot(Statut statut);

    UsersResponse update(Users users);

    void delete(Long id);

    UsersResponse activate(Users users);

    UsersResponse mapToResponse(Users users);
}
