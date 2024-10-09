package technologia.eduflex.services.Etudiant;

import technologia.eduflex.dto.EtudiantResponse;
import technologia.eduflex.models.Classe;
import technologia.eduflex.models.Etudiant;

import java.util.List;
import java.util.Optional;

public interface EtudiantService {

    EtudiantResponse save(Etudiant etudiant);

    Etudiant update(Long id, Etudiant updatedEtudiant);

    Etudiant delete(Etudiant etudiant);

    Optional<Etudiant> findByFirstNameAndLastnameAndClasse(String firstName, String lastName, Classe classe);
}
