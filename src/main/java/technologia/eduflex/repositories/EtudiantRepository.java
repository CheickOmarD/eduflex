package technologia.eduflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import technologia.eduflex.models.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    boolean existsByEmail(String email); // Vérifiez que cette méthode existe

}
