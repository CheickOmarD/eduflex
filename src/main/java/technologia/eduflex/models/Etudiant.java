package technologia.eduflex.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String classe;
    private String tuteur;
    @Column(unique = true)
    private String email;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime endDate = LocalDateTime.now();
}

