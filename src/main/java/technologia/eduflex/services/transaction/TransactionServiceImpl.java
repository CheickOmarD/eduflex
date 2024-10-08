package technologia.eduflex.services.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technologia.eduflex.enums.ModeTransaction;
import technologia.eduflex.enums.StatutTransaction;
import technologia.eduflex.exceptions.NotFoundException;
import technologia.eduflex.exceptions.TransactionException;
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
        validateTransaction(transaction);

        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setEndDate(LocalDateTime.now()); // Initialisation de endDate
        transaction.setStatut(StatutTransaction.EN_ATTENTE);
        return transactionRepository.save(transaction);
    }

    private void validateTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new TransactionException("La transaction ne peut pas être nulle.");
        }
        if (transaction.getMontant() == null || transaction.getMontant() <= 0) {
            throw new TransactionException("Montant de la transaction invalide.");
        }
        if (transaction.getMode() == null) {
            throw new TransactionException("Le mode de transaction ne peut pas être nul.");
        }
        if (!isValidMode(transaction.getMode())) {
            throw new TransactionException("Mode de transaction non valide. Seuls Orange Money et Moov Money sont acceptés.");
        }
    }

    private boolean isValidMode(ModeTransaction mode) {
        return mode.equals(ModeTransaction.ORANGE_MONEY) || mode.equals(ModeTransaction.MOOV_MONEY);
    }

    @Override
    public List<Transaction> visualiserTransactions(Long utilisateurId) {
        if (utilisateurId == null) {
            throw new TransactionException("L'ID de l'utilisateur ne peut pas être nul.");
        }
        return transactionRepository.findByUsersId(utilisateurId);
    }

    @Override
    public void validerTransaction(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new NotFoundException("Transaction non trouvée"));

        if (!transaction.getStatut().equals(StatutTransaction.EN_ATTENTE)) {
            throw new TransactionException("La transaction ne peut pas être validée car son statut est " + transaction.getStatut());
        }

        transaction.setStatut(StatutTransaction.VALIDE);
        transactionRepository.save(transaction);
    }

    @Override
    public void echouerTransaction(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new NotFoundException("Transaction non trouvée"));

        if (!transaction.getStatut().equals(StatutTransaction.EN_ATTENTE)) {
            throw new TransactionException("La transaction ne peut pas échouer car son statut est " + transaction.getStatut());
        }

        transaction.setStatut(StatutTransaction.ECHEC);
        transactionRepository.save(transaction);
    }
}
