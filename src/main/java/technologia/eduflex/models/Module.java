package technologia.eduflex.models;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    @@ManyToOne
//    private Enseignant enseignant;

    @ManyToOne
    private Classe classe;
    @OneToMany
    private Note note;
}
