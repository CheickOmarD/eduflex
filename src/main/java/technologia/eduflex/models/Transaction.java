package technologia.eduflex.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technologia.eduflex.enums.ModeTransaction;
import technologia.eduflex.enums.StatutTransaction;

import java.time.LocalDateTime;

@Getter
@Setter
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

    private LocalDateTime CreatedAt = LocalDateTime.now();
    private LocalDateTime endDate = LocalDateTime.now();
    @ManyToOne
    private Users users;

}



