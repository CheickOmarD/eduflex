package technologia.eduflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import technologia.eduflex.enums.Statut;
import technologia.eduflex.models.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role , Long>{
    Role findByName(String name);
    Role findByIdAndStatut(Long id, Statut statut);
//    Role findByNameAndStatut(String SuperAdmin,Statut statut);
    List<Role> findByStatut(Statut statut);
    List<Role> findByStatutNot(Statut statut);
}
