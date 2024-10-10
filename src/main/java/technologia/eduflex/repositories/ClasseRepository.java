package technologia.eduflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technologia.eduflex.models.Classe;
@Repository
public interface ClasseRepository extends JpaRepository<Classe,Long> {
    Classe findByIdAndName(Long id,String name);
    Classe findByIdAndNameNot(Long id,String name);
    Classe findByName(String name);
    Classe findByNameNot(String name);
}
