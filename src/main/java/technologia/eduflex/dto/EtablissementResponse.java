package technologia.eduflex.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import technologia.eduflex.enums.EtablissementType;
import technologia.eduflex.models.Classe;
import technologia.eduflex.models.Formation;

import java.util.List;

public class EtablissementResponse {

    private Long id;
    private  String name;
    private  String adress;

    @OneToMany
    private List<Classe> classes;
    @OneToMany
    private List<Formation> formations;
    @Enumerated(EnumType.STRING)
    private EtablissementType etablissementType;

    public EtablissementResponse(Long id, String name, Object address, Object description) {
    }
}
