package technologia.eduflex.services.formation;

import technologia.eduflex.dto.FormationResponse;
import technologia.eduflex.models.Formation;

import java.util.List;

public interface FormationService {
    FormationResponse save(Formation formation);

    FormationResponse findByIdAndName(Long id, String name);

    FormationResponse findByIdAndNameNot(Long id, String name);

    FormationResponse findByName(String name);

    FormationResponse findByNameNot(String name);

    List<FormationResponse> findAll();

    FormationResponse update(Formation formation);

    void delete(Long id);
}
