package technologia.eduflex.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TransactionResponse {
    private Long id;
    private Long utilisateurId;
    private Double montant;
    private LocalDateTime dateTransaction;
    private String statut;

}
