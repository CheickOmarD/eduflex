package technologia.eduflex.models;

import jakarta.persistence.*;
import lombok.*;
import technologia.eduflex.enums.Statut;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@ToString
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate Birthday;
    private Classe classe;
    private String note;
    private String tuteur;
    @Column(unique = true)
    private String email;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime endDate = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private Statut statut;
    @ManyToOne
    private Role role;
    @OneToMany
    private List<Note> notes;
}

