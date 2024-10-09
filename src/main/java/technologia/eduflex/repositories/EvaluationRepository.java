package technologia.eduflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import technologia.eduflex.models.Classe;
import technologia.eduflex.models.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation,Long> {
    Evaluation findByIdAndName(Long id, String name);
    Evaluation findByIdAndNameNot(Long id,String name);
    Evaluation findByName(String name);
    Evaluation findByNameNot(String name);
}
