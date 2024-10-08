package technologia.eduflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import technologia.eduflex.models.Personnel;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    boolean existsByEmail(String email);

}
