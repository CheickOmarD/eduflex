package technologia.eduflex.models;

import jakarta.persistence.*;
import lombok.*;
import technologia.eduflex.enums.Statut;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    @ToString.Exclude
    private List<Users> users = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private Statut statut = Statut.ACTIVATER;

    public Role(String name) {
        this.name = name;
    }

}
