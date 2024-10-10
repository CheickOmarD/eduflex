package technologia.eduflex.dto;


import technologia.eduflex.models.Classe;
import technologia.eduflex.models.Note;

import java.util.List;

public class ModuleResponse {
    private Long id;
    private String name;
    private List<Note> notes;
    private List<Classe> classes;

    public ModuleResponse(Long id, String name, String description) {
    }
}
