package technologia.eduflex.services.tuteur;

import technologia.eduflex.dto.TuteurResponse;
import technologia.eduflex.models.Tuteur;

import java.util.List;

public interface TuteurService{
    TuteurResponse save(Tuteur tuteur);

    TuteurResponse findByIdAndFirstname(Long id, String firstname);

    TuteurResponse findByFirstNameAndLastName(String firstname, String lastname);

    TuteurResponse findByIdAndFistname(Long id, String firstname);

    List<TuteurResponse> findAll();

    TuteurResponse update(Tuteur tuteur);

    void delete(Long id);
}
