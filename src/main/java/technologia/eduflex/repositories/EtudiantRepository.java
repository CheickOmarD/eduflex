package technologia.eduflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import technologia.eduflex.enums.Statut;
import technologia.eduflex.models.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    boolean existsByEmail(String email);
    Etudiant findByEmailAndStatut(String email, Statut statut);

}
