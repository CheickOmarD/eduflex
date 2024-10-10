package technologia.eduflex.services.classe;

import technologia.eduflex.dto.ClasseResponse;
import technologia.eduflex.models.Classe;

import java.util.List;

public interface ClasseService {
    ClasseResponse save(Classe classe);


    ClasseResponse findByIdAndName(Long id, String name);

    ClasseResponse findByIdAndNameNot(Long id, String name);

    ClasseResponse findByName(String name);

    ClasseResponse findByNameNot(String name);

    List<ClasseResponse> findAll();

    void delete(Long id);

    ClasseResponse update(Classe classe);
}
