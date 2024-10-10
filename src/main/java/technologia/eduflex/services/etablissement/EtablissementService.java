package technologia.eduflex.services.etablissement;

import technologia.eduflex.dto.EtablissementResponse;
import technologia.eduflex.models.Etablissement;

import java.util.List;

public interface EtablissementService {
    EtablissementResponse save(Etablissement etablissement);

    EtablissementResponse findByIdAndName(Long id, String name);

    EtablissementResponse findByIdAndNameNot(Long id, String name);

    EtablissementResponse findByName(String name);

    EtablissementResponse findByNameAndAdress(String name, String adress);

    List<EtablissementResponse> findAll();

    EtablissementResponse update(Etablissement etablissement);

    void delete(Long id);
}
