package technologia.eduflex.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Etablissement etablissement;
    @OneToMany
    private List<Etudiant> etudiants;
    @OneToMany
    private List<Module> modules;
    @OneToMany
    private List<Evaluation> evaluations;
}
