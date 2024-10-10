package technologia.eduflex.services.evaluation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import technologia.eduflex.dto.EvaluationResponse;
import technologia.eduflex.exceptions.NotFoundException;
import technologia.eduflex.models.Evaluation;
import technologia.eduflex.repositories.EvaluationRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService{

    private final EvaluationRepository evaluationRepository;

    @Override
    public EvaluationResponse save(Evaluation evaluation) {
        Evaluation savedEvaluation = evaluationRepository.save(evaluation);
        return mapToResponse(savedEvaluation);
    }

    @Override
    public EvaluationResponse findByIdAndName(Long id, String name) {
        Optional<Evaluation> evaluation = Optional.ofNullable(evaluationRepository.findByIdAndName(id, name));
        if (evaluation.isPresent()) {
            return mapToResponse(evaluation.get());
        } else {
            throw new NotFoundException("Evaluation with ID " + id + " and Name '" + name + "' not found.");
        }
    }

    @Override
    public EvaluationResponse findByIdAndNameNot(Long id, String name) {
        Optional<Evaluation> evaluation = Optional.ofNullable(evaluationRepository.findByIdAndNameNot(id, name));
        if (evaluation.isPresent()) {
            return mapToResponse(evaluation.get());
        } else {
            throw new NotFoundException("Evaluation with ID " + id + " and not Name '" + name + "' not found.");
        }
    }

    @Override
    public EvaluationResponse findByName(String name) {
        Optional<Evaluation> evaluation = Optional.ofNullable(evaluationRepository.findByName(name));
        if (evaluation.isPresent()) {
            return mapToResponse(evaluation.get());
        } else {
            throw new NotFoundException("Evaluation with Name '" + name + "' not found.");
        }
    }

    @Override
    public EvaluationResponse findByNameNot(String name) {
        Optional<Evaluation> evaluation = Optional.ofNullable(evaluationRepository.findByNameNot(name));
        if (evaluation.isPresent()) {
            return mapToResponse(evaluation.get());
        } else {
            throw new NotFoundException("Evaluation not found with Name '" + name + "'.");
        }
    }

    @Override
    public List<EvaluationResponse> findAll() {
        return evaluationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EvaluationResponse update(Evaluation evaluation) {
        if (!evaluationRepository.existsById(evaluation.getId())) {
            throw new NotFoundException("Evaluation with ID " + evaluation.getId() + " not found for update.");
        }
        Evaluation updatedEvaluation = evaluationRepository.save(evaluation);
        return mapToResponse(updatedEvaluation);
    }

    @Override
    public void delete(Long id) {
        if (!evaluationRepository.existsById(id)) {
            throw new NotFoundException("Evaluation with ID " + id + " not found for deletion.");
        }
        evaluationRepository.deleteById(id);
    }

    private EvaluationResponse mapToResponse(Evaluation evaluation) {
        return new EvaluationResponse(
                evaluation.getId(),
                evaluation.getName(),
                evaluation.getModules(),
                evaluation.getCreatedAt(),
                evaluation.getEndDate()
        );
    }
}
