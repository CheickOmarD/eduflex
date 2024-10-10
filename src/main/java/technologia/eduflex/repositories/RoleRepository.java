package technologia.eduflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technologia.eduflex.enums.Statut;
import technologia.eduflex.models.Role;
import technologia.eduflex.models.Users;

import java.util.List;
@Repository
public interface RoleRepository extends JpaRepository<Role , Long>{
    Role findByName(String name);
    Role findByIdAndStatut(Long id, Statut statut);
    Role findByNameAndStatut(String ADMIN,Statut statut);
    List<Role> findByStatutNot(Statut statut);
}
