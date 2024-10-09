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

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime endDate = LocalDateTime.now();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private Statut statut = Statut.ACTIVATER;

}
