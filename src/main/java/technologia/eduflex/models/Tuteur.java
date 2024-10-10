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
public class Tuteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @OneToMany
    private List<Etudiant> etudiants;

    public String  getFirstname() {
        return "";
    }

    public String  getLastname() {
        return "";
    }

    public String  getEmail() {
        return "";
    }
}
