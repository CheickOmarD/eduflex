package technologia.eduflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import technologia.eduflex.enums.Statut;
import technologia.eduflex.models.Users;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByIdAndStatut(Long id, Statut statut);
    Users findByEmailAndStatut(String email , Statut statut);
    Users findByIdAndStatutNot(Long id, Statut statut);
    List<Users> findByStatut(Statut statut);
    List<Users> findByStatutNot(Statut statut);
    Users findByEmailAndStatutNot(String email , Statut statut);
    List<Users> findByRoles_Name(String roleName);

}
