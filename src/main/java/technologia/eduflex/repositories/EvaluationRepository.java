package technologia.eduflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technologia.eduflex.models.Classe;
import technologia.eduflex.models.Evaluation;

import java.util.List;
@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation,Long> {
    Evaluation findByIdAndName(Long id, String name);
    Evaluation findByIdAndNameNot(Long id,String name);
    Evaluation findByName(String name);
    Evaluation findByNameNot(String name);

    List<Evaluation> findAllByClasseId(Long id);
}
