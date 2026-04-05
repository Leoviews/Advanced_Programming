package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;

    public TransactionService(TransactionRepository transactionRepo, AccountRepository accountRepo) {
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
    }

    public Transaction createTransaction(Long accountId, TransactionRequest request) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        if (request.getAmount() == null || request.getAmount() <= 0) {
            throw new BadRequestException("Amount must be greater than 0");
        }

        if (request.getTransactionType() == null || request.getTransactionType().trim().isEmpty()) {
            throw new BadRequestException("Transaction type is required");
        }

        String type = request.getTransactionType().trim();

        if (!type.equalsIgnoreCase("Credit") && !type.equalsIgnoreCase("Debit")) {
            throw new BadRequestException("Transaction type must be Credit or Debit");
        }

        if (type.equalsIgnoreCase("Debit") && account.getBalance() < request.getAmount()) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setTransactionType(type);
        transaction.setTransactionDate(request.getTransactionDate() != null ? request.getTransactionDate() : new Date());
        transaction.setAccount(account);

        if (type.equalsIgnoreCase("Credit")) {
            account.setBalance(account.getBalance() + request.getAmount());
        } else {
            account.setBalance(account.getBalance() - request.getAmount());
        }

        accountRepo.save(account);
        return transactionRepo.save(transaction);
    }

    public List<Transaction> getTransactionsByAccountId(Long accountId, String type) {
        accountRepo.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        if (type != null && !type.trim().isEmpty()) {
            return transactionRepo.findByAccount_IdAndTransactionTypeIgnoreCase(accountId, type.trim());
        }

        return transactionRepo.findByAccount_Id(accountId);
    }

    public List<Transaction> getTransactionsByAccountIdAndDateRange(Long accountId, Date startDate, Date endDate) {
        accountRepo.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        if (startDate == null || endDate == null) {
            throw new BadRequestException("Both startDate and endDate are required");
        }

        return transactionRepo.findByAccount_IdAndTransactionDateBetween(accountId, startDate, endDate);
    }

    public void deleteTransaction(Long transactionId) {
        Transaction transaction = transactionRepo.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        transactionRepo.delete(transaction);
    }
}