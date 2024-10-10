package technologia.eduflex.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
    @JoinColumn(name = "etablissement_id", nullable = false) // Ajoutez un nom de colonne
    private Etablissement etablissement;

    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL, orphanRemoval = true) // Ajoutez la relation inverse
    @ToString.Exclude // Évite une boucle infinie lors de l'utilisation de toString
    private List<Etudiant> etudiants = new ArrayList<>(); // Initialisez la liste

    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Module> modules = new ArrayList<>();

    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Evaluation> evaluations = new ArrayList<>();
}
