package technologia.eduflex.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@ToString
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Personnel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String phoneNumber;
    private String email;
    private String password;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime endDate = LocalDateTime.now();
    private String role;

}
