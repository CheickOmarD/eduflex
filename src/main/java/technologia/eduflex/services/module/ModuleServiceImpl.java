package technologia.eduflex.services.module;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import technologia.eduflex.dto.ModuleResponse;
import technologia.eduflex.exceptions.NotFoundException;
import technologia.eduflex.models.Module;
import technologia.eduflex.repositories.ModuleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;

    @Override
    public ModuleResponse save(Module module) {
        Module savedModule = moduleRepository.save(module);
        return mapToResponse(savedModule);
    }

    @Override
    public ModuleResponse save(java.lang.Module module) {
        return null;
    }

    @Override
    public ModuleResponse findById(Long id) {
        Module module = moduleRepository.findById(id);
        if (module == null) {
            throw new NotFoundException("Module avec l'ID " + id + " non trouvé.");
        }
        return mapToResponse(module);
    }

    @Override
    public List<ModuleResponse> findAll() {
        List<Module> modules = moduleRepository.findAll();
        // Convertir manuellement chaque module en ModuleResponse
        List<ModuleResponse> responses = new ArrayList<>();
        for (Module module : modules) {
            responses.add(mapToResponse(module));
        }
        return responses;
    }

    @Override
    public ModuleResponse update(java.lang.Module module) {
        return null;
    }

    @Override
    public ModuleResponse update(Module module) {
        if (module == null || moduleRepository.findById(module.getId()) == null) {
            throw new NotFoundException("Module avec l'ID " + (module != null ? module.getId() : "inconnu") + " non trouvé pour la mise à jour.");
        }
        Module updatedModule = moduleRepository.save(module);
        return mapToResponse(updatedModule);
    }

    @Override
    public void delete(Long id) {
        Module module = moduleRepository.findById(id);
        if (module == null) {
            throw new NotFoundException("Module avec l'ID " + (id != null ? id : "inconnu") + " non trouvé pour la suppression.");
        }
        moduleRepository.deleteById(id);
    }

    private ModuleResponse mapToResponse(Module module) {
        return new ModuleResponse(
                module.getId(),
                module.getName(),
                module.getDescription() // Ajustez selon les champs réels de la classe Module
        );
    }
}
