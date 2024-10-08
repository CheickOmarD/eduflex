package technologia.eduflex.models;



import jakarta.persistence.*;
import lombok.*;
import technologia.eduflex.enums.StatutTransaction;

import java.time.LocalDateTime;

@Data
@Entity
@ToString
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double montant;

    @Enumerated(EnumType.STRING)
    private StatutTransaction statut;

    private LocalDateTime dateTransaction= LocalDateTime.now();
    @ManyToOne
    private Users users;
}


