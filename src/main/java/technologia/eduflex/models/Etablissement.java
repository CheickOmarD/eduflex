package technologia.eduflex.models;

import jakarta.persistence.*;
import lombok.*;
import technologia.eduflex.enums.EtablissementType;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Etablissement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String name;
    private  String adress;

    @OneToMany
    private List <Classe> classes;
    @OneToMany
    private List<Formation> formations;
    @Enumerated(EnumType.STRING)
    private EtablissementType etablissementType;

    public Object getAddress() {
        return null;
    }

    public Object getDescription() {
        return null;
    }

    public boolean isPresent() {
        return false;
    }
}
