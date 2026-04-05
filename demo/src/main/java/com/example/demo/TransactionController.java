package com.example.demo;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/{accountId}")
    public Transaction addTransaction(@PathVariable Long accountId, @RequestBody TransactionRequest request) {
        return service.createTransaction(accountId, request);
    }

    @GetMapping("/{accountId}")
    public List<Transaction> getTransactions(
            @PathVariable Long accountId,
            @RequestParam(required = false) String type) {
        return service.getTransactionsByAccountId(accountId, type);
    }

    @GetMapping("/{accountId}/between")
    public List<Transaction> getTransactionsBetween(
            @PathVariable Long accountId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return service.getTransactionsByAccountIdAndDateRange(accountId, startDate, endDate);
    }

    @DeleteMapping("/{transactionId}/delete")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long transactionId) {
        service.deleteTransaction(transactionId);
        return ResponseEntity.ok("Transaction deleted successfully");
    }
}