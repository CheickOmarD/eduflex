package technologia.eduflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technologia.eduflex.enums.Statut;
import technologia.eduflex.models.Users;

import java.util.List;
@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByIdAndStatut(Long id, Statut statut);
    Users findByEmailAndStatut(String email , Statut statut);
    Users findByIdAndStatutNot(Long id, Statut statut);
    List<Users> findByStatut(Statut statut);
    List<Users> findByStatutNot(Statut statut);
    Users findByEmailAndStatutNot(String email , Statut statut);
    List<Users> findByRolesName(String roleName);

}
