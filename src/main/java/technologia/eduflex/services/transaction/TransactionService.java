package technologia.eduflex.services.transaction;

import technologia.eduflex.models.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction creerTransaction(Transaction transaction);

    List<Transaction> visualiserTransactions(Long utilisateurId);

    void validerTransaction(Long transactionId);

    void echouerTransaction(Long transactionId);
}
