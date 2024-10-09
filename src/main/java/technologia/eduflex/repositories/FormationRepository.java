package technologia.eduflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import technologia.eduflex.models.Formation;

public interface FormationRepository extends JpaRepository<Formation,Long> {
    Formation findByIdAndName(Long id, String name);
    Formation findByIdAndNameNot(Long id,String name);
    Formation findByName(String name);
    Formation findByNameNot(String name);
}
