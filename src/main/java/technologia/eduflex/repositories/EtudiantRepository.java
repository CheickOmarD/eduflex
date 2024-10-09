package technologia.eduflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import technologia.eduflex.enums.Statut;
import technologia.eduflex.models.Classe;
import technologia.eduflex.models.Etudiant;

import java.time.LocalDate;
import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    Etudiant findByEmailAndStatut(String email, Statut statut);
    Etudiant findByIdAndClasseAndBirthday(Long id, Classe classe, LocalDate Birthday);
    Etudiant findByIdAndFirstNameAndLastNameAndBirthday(Long id,String lastName,String firstName, LocalDate Birthday);

    Optional<Etudiant> findByFirstNameAndLastnameAndClasse(String firstName, String lastName, Classe classe);

    Optional<Etudiant> findByIdAndFirstNameAndLastnameAndClasse(Long id, String firstName, String lastName, Classe classe);
}
