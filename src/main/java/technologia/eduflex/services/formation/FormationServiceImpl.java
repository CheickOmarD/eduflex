package technologia.eduflex.services.formation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import technologia.eduflex.dto.FormationResponse;
import technologia.eduflex.exceptions.NotFoundException;
import technologia.eduflex.models.Formation;
import technologia.eduflex.repositories.FormationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormationServiceImpl implements FormationService {

    private final FormationRepository formationRepository;

    @Override
    public FormationResponse save(Formation formation) {
        Formation savedFormation = formationRepository.save(formation);
        return mapToResponse(savedFormation);
    }

    @Override
    public FormationResponse findByIdAndName(Long id, String name) {
        Formation formation = formationRepository.findByIdAndName(id, name);
        if (formation == null) {
            throw new NotFoundException("Formation avec l'ID " + id + " et le nom '" + name + "' non trouvée.");
        }
        return mapToResponse(formation);
    }

    @Override
    public FormationResponse findByIdAndNameNot(Long id, String name) {
        Formation formation = formationRepository.findByIdAndNameNot(id, name);
        if (formation == null) {
            throw new NotFoundException("Formation avec l'ID " + id + " et non le nom '" + name + "' non trouvée.");
        }
        return mapToResponse(formation);
    }

    @Override
    public FormationResponse findByName(String name) {
        Formation formation = formationRepository.findByName(name);
        if (formation == null) {
            throw new NotFoundException("Formation avec le nom '" + name + "' non trouvée.");
        }
        return mapToResponse(formation);
    }

    @Override
    public FormationResponse findByNameNot(String name) {
        Formation formation = formationRepository.findByNameNot(name);
        if (formation == null) {
            throw new NotFoundException("Formation avec le nom '" + name + "' non trouvée.");
        }
        return mapToResponse(formation);
    }

    @Override
    public List<FormationResponse> findAll() {
        return formationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList(); // Utilisation de toList() pour collecter les résultats
    }

    @Override
    public FormationResponse update(Formation formation) {
        if (!formationRepository.existsById(formation.getId())) {
            throw new NotFoundException("Formation avec l'ID " + formation.getId() + " non trouvée pour la mise à jour.");
        }
        Formation updatedFormation = formationRepository.save(formation);
        return mapToResponse(updatedFormation);
    }

    @Override
    public void delete(Long id) {
        if (!formationRepository.existsById(id)) {
            throw new NotFoundException("Formation avec l'ID " + id + " non trouvée pour la suppression.");
        }
        formationRepository.deleteById(id);
    }

    private FormationResponse mapToResponse(Formation formation) {
        return new FormationResponse(
                formation.getId(),
                formation.getName(),
                formation.getDescription() // Ajustez selon les champs réels de la classe Formation
        );
    }
}
