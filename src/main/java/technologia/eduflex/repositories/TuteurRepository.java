package technologia.eduflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technologia.eduflex.models.Tuteur;
@Repository
public interface TuteurRepository extends JpaRepository<Tuteur,Long> {
    Tuteur findByIdAndFirstname(Long id, String firstname);
    Tuteur findByFirstNameAndLastName(String firstname,String lastname);
    Tuteur findByIdAndFistname(Long id,String firstname);
}
