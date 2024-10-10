package technologia.eduflex.services.classe;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import technologia.eduflex.dto.ClasseResponse;
import technologia.eduflex.models.Classe;
import technologia.eduflex.models.Etablissement;
import technologia.eduflex.models.Etudiant;
import technologia.eduflex.models.Evaluation;
import technologia.eduflex.models.Module;
import technologia.eduflex.repositories.*;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ClasseServiceImpl implements ClasseService {
    private final ClasseRepository classeRepository;
    private final EtablissementRepository etablissementRepository;
    private final EtudiantRepository etudiantRepository;
    private final ModuleRepository moduleRepository;
    private final EvaluationRepository evaluationRepository;

    @Override
    public ClasseResponse save(Classe classe) {
        // Sauvegarde directe de la classe
        Classe savedClasse = classeRepository.save(classe);
        return mapToClasseResponse(savedClasse);
    }

    @Override
    public ClasseResponse findByIdAndName(Long id, String name) {
        // Recherche de la classe avec une condition simple sur l'ID et le nom
        Classe classe = classeRepository.findByIdAndName(id, name);
        return classe != null ? mapToClasseResponse(classe) : null;
    }

    @Override
    public ClasseResponse findByIdAndNameNot(Long id, String name) {
        // Recherche de la classe avec exclusion d'un nom
        Classe classe = classeRepository.findByIdAndNameNot(id, name);
        return classe != null ? mapToClasseResponse(classe) : null;
    }

    @Override
    public ClasseResponse findByName(String name) {
        // Recherche d'une classe par nom
        Classe classe = classeRepository.findByName(name);
        return classe != null ? mapToClasseResponse(classe) : null;
    }

    @Override
    public ClasseResponse findByNameNot(String name) {
        // Recherche d'une classe excluant un nom spécifique
        Classe classe = classeRepository.findByNameNot(name);
        return classe != null ? mapToClasseResponse(classe) : null;
    }

    @Override
    public List<ClasseResponse> findAll() {
        // Récupération de toutes les classes et mapping vers ClasseResponse
        List<Classe> classes = classeRepository.findAll();
        return classes.stream()
                .map(this::mapToClasseResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClasseResponse update(Classe classe) {
        // Mise à jour simple d'une classe
        Classe updatedClasse = classeRepository.save(classe);
        return mapToClasseResponse(updatedClasse);
    }

    @Override
    public void delete(Long id) {
        // Suppression d'une classe
        classeRepository.deleteById(id);
    }

    private ClasseResponse mapToClasseResponse(Classe classe) {
        // Mapping simple avec récupération des données liées
        Etablissement etablissement = etablissementRepository.findById(classe.getEtablissement().getId()).orElse(null);
        List<Etudiant> etudiants = etudiantRepository.findAllByClasseId(classe.getId());
        List<Module> modules = moduleRepository.findAllByClasseId(classe.getId());
        List<Evaluation> evaluations = evaluationRepository.findAllByClasseId(classe.getId());

        return new ClasseResponse(
                classe.getId(),
                classe.getName(),
                etablissement,
                etudiants,
                modules,
                evaluations
        );
    }

}
