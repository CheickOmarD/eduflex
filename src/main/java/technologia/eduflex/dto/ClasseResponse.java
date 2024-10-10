package technologia.eduflex.dto;

import technologia.eduflex.models.Etablissement;
import technologia.eduflex.models.Etudiant;
import technologia.eduflex.models.Evaluation;
import technologia.eduflex.models.Module;

import java.util.List;

public class ClasseResponse {
    private Long id;
    private String name;
    private Etablissement etablissement;
    private List<Etudiant> etudiants;
    private List<Module> modules;
    private List<Evaluation> evaluations;


    public ClasseResponse(Long id, String name, Etablissement etablissement, List<Etudiant> etudiants, List<Module> modules, List<Evaluation> evaluations) {
    }
}
