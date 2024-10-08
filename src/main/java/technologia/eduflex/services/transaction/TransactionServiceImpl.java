package technologia.eduflex.services.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technologia.eduflex.enums.StatutTransaction;
import technologia.eduflex.exceptions.NotFoundException;
import technologia.eduflex.models.Transaction;
import technologia.eduflex.repositories.TransactionRepository;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction creerTransaction(Transaction transaction) {
        // Validation de l'objet Transaction
        if (transaction == null || transaction.getMontant() == null || transaction.getMontant() <= 0) {
            throw new IllegalArgumentException("Montant de la transaction invalide.");
        }

        transaction.setDateTransaction(LocalDateTime.now());
        transaction.setStatut(StatutTransaction.EN_ATTENTE); // Statut par défaut
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> visualiserTransactions(Long utilisateurId) {
        // Vérifiez que l'utilisateur existe
        if (utilisateurId == null) {
            throw new IllegalArgumentException("L'ID de l'utilisateur ne peut pas être nul.");
        }
        // Récupérer les transactions de l'utilisateur
        return transactionRepository.findByUtilisateurId(utilisateurId);
    }

    @Override
    public void validerTransaction(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new NotFoundException("Transaction non trouvée"));

        if (!transaction.getStatut().equals(StatutTransaction.EN_ATTENTE)) {
            throw new IllegalStateException("La transaction ne peut pas être validée car son statut est " + transaction.getStatut());
        }

        transaction.setStatut(StatutTransaction.VALIDE);
        transactionRepository.save(transaction);
    }

    @Override
    public void echouerTransaction(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new NotFoundException("Transaction non trouvée"));

        if (!transaction.getStatut().equals(StatutTransaction.EN_ATTENTE)) {
            throw new IllegalStateException("La transaction ne peut pas échouer car son statut est " + transaction.getStatut());
        }

        transaction.setStatut(StatutTransaction.ECHEC);
        transactionRepository.save(transaction);
    }
}
