package technologia.eduflex.repositories;

import org.springframework.stereotype.Repository;
import technologia.eduflex.models.Module;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ModuleRepository {

    private final List<Module> modules = new ArrayList<>(); // Stockage des modules en mémoire

    public List<Module> findAll() {
        return new ArrayList<>(modules); // Renvoie une nouvelle liste pour éviter les modifications externes
    }

    public Module findById(Long id) {
        for (Module module : modules) {
            if (module.getId().equals(id)) {
                return module; // Renvoie le module trouvé
            }
        }
        return null; // Renvoie null si non trouvé
    }

    public void deleteById(Long id) {
        modules.removeIf(module -> module.getId().equals(id));
    }

    public Module save(Module module) {
        if (module.getId() == null) {
            module.setId((long) (modules.size() + 1)); // Assignation d'un ID simple
        }
        modules.removeIf(existingModule -> existingModule.getId().equals(module.getId())); // Supprime un module existant
        modules.add(module);
        return module;
    }

    public List<Module> findAllByClasseId(Long classeId) {
        // Logique pour filtrer par classe si applicable
        return List.of(); // Remplacer par la logique réelle
    }
}
