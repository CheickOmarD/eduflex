package technologia.eduflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import technologia.eduflex.models.Classe;

public interface ClasseRepository extends JpaRepository<Classe,Long> {
    Classe findByIdAndName(Long id,String name);
    Classe findByIdAndNameNot(Long id,String name);
    Classe findByName(String name);
    Classe findByNameNot(String name);
}
