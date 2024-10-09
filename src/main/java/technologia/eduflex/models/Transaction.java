package technologia.eduflex.models;

import jakarta.persistence.*;
import lombok.*;
import technologia.eduflex.enums.ModeTransaction;
import technologia.eduflex.enums.StatutTransaction;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double montant;

    @Enumerated(EnumType.STRING)
    private StatutTransaction statut;

    @Enumerated(EnumType.STRING)
    private ModeTransaction mode;

    private LocalDateTime paiement = LocalDateTime.now();
    @ManyToOne
    private Users users;

}



