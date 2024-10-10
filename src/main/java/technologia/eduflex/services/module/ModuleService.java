package technologia.eduflex.services.module;

import technologia.eduflex.dto.ModuleResponse;

import java.util.List;

public interface ModuleService {
    ModuleResponse save(technologia.eduflex.models.Module module);

    ModuleResponse save(Module module);

    ModuleResponse findById(Long id);

    List<ModuleResponse> findAll();

    ModuleResponse update(Module module);

    ModuleResponse update(technologia.eduflex.models.Module module);

    void delete(Long id);
}
