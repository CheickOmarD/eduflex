package technologia.eduflex.services.evaluation;

import technologia.eduflex.dto.EvaluationResponse;
import technologia.eduflex.models.Evaluation;

import java.util.List;

public interface EvaluationService {
    EvaluationResponse save(Evaluation evaluation);

    EvaluationResponse findByIdAndName(Long id, String name);

    EvaluationResponse findByIdAndNameNot(Long id, String name);

    EvaluationResponse findByName(String name);

    EvaluationResponse findByNameNot(String name);

    List<EvaluationResponse> findAll();

    EvaluationResponse update(Evaluation evaluation);

    void delete(Long id);
}
