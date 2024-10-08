package technologia.eduflex.models;

import jakarta.persistence.*;
import lombok.*;
import technologia.eduflex.enums.Statut;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@ToString
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String password;
    private LocalDateTime createdAt = LocalDateTime.now();

   @ManyToMany (fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Role> roles = new ArrayList<>();
//    @ManyToMany
//    private Set<Role> roles;
    @Enumerated(value = EnumType.STRING)
    private Statut statut = Statut.ACTIVATER;
}
