package technologia.eduflex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technologia.eduflex.exceptions.NotFoundException;
import technologia.eduflex.models.Transaction;
import technologia.eduflex.services.transaction.TransactionService;


import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> creerTransaction(@RequestBody Transaction transaction) {
        try {
            Transaction createdTransaction = transactionService.creerTransaction(transaction);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{utilisateurId}")
    public ResponseEntity<List<Transaction>> visualiserTransactions(@PathVariable Long utilisateurId) {
        if (utilisateurId == null) {
            return ResponseEntity.badRequest().build();
        }
        List<Transaction> transactions = transactionService.visualiserTransactions(utilisateurId);
        return ResponseEntity.ok(transactions);
    }

    @PutMapping("/valider/{transactionId}")
    public ResponseEntity<Void> validerTransaction(@PathVariable Long transactionId) {
        try {
            transactionService.validerTransaction(transactionId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/echouer/{transactionId}")
    public ResponseEntity<Void> echouerTransaction(@PathVariable Long transactionId) {
        try {
            transactionService.echouerTransaction(transactionId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
