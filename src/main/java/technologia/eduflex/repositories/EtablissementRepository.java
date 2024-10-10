package technologia.eduflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import technologia.eduflex.models.Etablissement;

import java.util.Optional;

public interface EtablissementRepository extends JpaRepository<Etablissement,Long> {
    Etablissement findByIdAndName(Long id, String name);
    Etablissement findByIdAndNameNot(Long id,String name);
    Etablissement findByName(String name);
    Etablissement findByNameAndAdress(String name,String adress);

    Etablissement findByNameAndAddress(String name, String address);
}
