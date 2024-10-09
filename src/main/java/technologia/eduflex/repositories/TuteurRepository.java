package technologia.eduflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import technologia.eduflex.models.Classe;
import technologia.eduflex.models.Tuteur;

public interface TuteurRepository extends JpaRepository<Tuteur,Long> {
    Tuteur findByIdAndName(Long id, String firstname);
    Tuteur findByFirstNameAndLastName(Long id,String lastname);
    Tuteur findByIdAndFistname(String firstname);
}
