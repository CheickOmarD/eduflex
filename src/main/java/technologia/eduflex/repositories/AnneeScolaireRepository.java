package technologia.eduflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import technologia.eduflex.models.AnneeScolaire;

import java.util.Optional;

public interface AnneeScolaireRepository extends JpaRepository<AnneeScolaire,Long> {
    Optional<AnneeScolaire> findById(Long id);
}
