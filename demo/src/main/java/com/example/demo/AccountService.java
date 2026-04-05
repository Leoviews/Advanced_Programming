package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {

    private final AccountRepository repository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository repository, TransactionRepository transactionRepository) {
        this.repository = repository;
        this.transactionRepository = transactionRepository;
    }

    public Account createAccount(Account account) {
        validateAccount(account);

        if (repository.existsByAccountNumber(account.getAccountNumber())) {
            throw new BadRequestException("Account number already exists");
        }

        return repository.save(account);
    }

    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    public Account getAccountById(Long accountId) {
        return repository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
    }

    public Map<String, String> getAccountTypeAndNumber(Long accountId) {
        Account account = getAccountById(accountId);

        Map<String, String> details = new HashMap<>();
        details.put("accountNumber", account.getAccountNumber());
        details.put("accountType", account.getAccountType());

        return details;
    }

    public Account updateAccount(Long id, AccountUpdateRequest request) {
        Account account = getAccountById(id);

        if (request.getName() != null && !request.getName().trim().isEmpty()) {
            account.setName(request.getName().trim());
        }

        if (request.getAccountType() != null && !request.getAccountType().trim().isEmpty()) {
            account.setAccountType(request.getAccountType().trim());
        }

        if (request.getAccountNumber() != null && !request.getAccountNumber().trim().isEmpty()) {
            String newAccountNumber = request.getAccountNumber().trim();

            repository.findByAccountNumber(newAccountNumber).ifPresent(existing -> {
                if (!existing.getId().equals(id)) {
                    throw new BadRequestException("Account number already exists");
                }
            });

            account.setAccountNumber(newAccountNumber);
        }

        return repository.save(account);
    }

    public Account deposit(Long id, double amount) {
        if (amount <= 0) {
            throw new BadRequestException("Deposit amount must be greater than 0");
        }

        Account account = getAccountById(id);
        account.setBalance(account.getBalance() + amount);
        return repository.save(account);
    }

    public Account withdraw(Long id, double amount) {
        if (amount <= 0) {
            throw new BadRequestException("Withdraw amount must be greater than 0");
        }

        Account account = getAccountById(id);

        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        return repository.save(account);
    }

    public void deleteAccount(Long accountId) {
        if (!repository.existsById(accountId)) {
            throw new ResourceNotFoundException("Account not found");
        }
        repository.deleteById(accountId);
    }

    public Map<String, Object> transferMoney(TransferRequest request) {
        if (request.getFromAccountId() == null || request.getToAccountId() == null) {
            throw new BadRequestException("Both account IDs are required");
        }

        if (request.getAmount() <= 0) {
            throw new BadRequestException("Transfer amount must be greater than 0");
        }

        if (request.getFromAccountId().equals(request.getToAccountId())) {
            throw new BadRequestException("Source and destination accounts cannot be the same");
        }

        Account fromAccount = getAccountById(request.getFromAccountId());
        Account toAccount = getAccountById(request.getToAccountId());

        if (fromAccount.getBalance() < request.getAmount()) {
            throw new InsufficientBalanceException("Insufficient balance in source account");
        }

        fromAccount.setBalance(fromAccount.getBalance() - request.getAmount());
        toAccount.setBalance(toAccount.getBalance() + request.getAmount());

        repository.save(fromAccount);
        repository.save(toAccount);

        Transaction debitTransaction = new Transaction();
        debitTransaction.setAmount(request.getAmount());
        debitTransaction.setTransactionType("Debit");
        debitTransaction.setTransactionDate(new Date());
        debitTransaction.setAccount(fromAccount);
        transactionRepository.save(debitTransaction);

        Transaction creditTransaction = new Transaction();
        creditTransaction.setAmount(request.getAmount());
        creditTransaction.setTransactionType("Credit");
        creditTransaction.setTransactionDate(new Date());
        creditTransaction.setAccount(toAccount);
        transactionRepository.save(creditTransaction);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Transfer successful");
        response.put("fromAccountId", fromAccount.getId());
        response.put("toAccountId", toAccount.getId());
        response.put("amount", request.getAmount());
        response.put("fromAccountNewBalance", fromAccount.getBalance());
        response.put("toAccountNewBalance", toAccount.getBalance());

        return response;
    }

    public Map<String, Object> getAccountSummary(Long accountId) {
        Account account = getAccountById(accountId);
        List<Transaction> transactions = transactionRepository.findByAccount_Id(accountId);

        double totalCredits = 0;
        double totalDebits = 0;

        for (Transaction transaction : transactions) {
            if ("Credit".equalsIgnoreCase(transaction.getTransactionType())) {
                totalCredits += transaction.getAmount();
            } else if ("Debit".equalsIgnoreCase(transaction.getTransactionType())) {
                totalDebits += transaction.getAmount();
            }
        }

        Map<String, Object> summary = new HashMap<>();
        summary.put("accountId", account.getId());
        summary.put("name", account.getName());
        summary.put("accountNumber", account.getAccountNumber());
        summary.put("accountType", account.getAccountType());
        summary.put("currentBalance", account.getBalance());
        summary.put("numberOfTransactions", transactions.size());
        summary.put("totalCredits", totalCredits);
        summary.put("totalDebits", totalDebits);

        return summary;
    }

    private void validateAccount(Account account) {
        if (account.getName() == null || account.getName().trim().isEmpty()) {
            throw new BadRequestException("Name is required");
        }

        if (account.getAccountType() == null || account.getAccountType().trim().isEmpty()) {
            throw new BadRequestException("Account type is required");
        }

        if (account.getAccountNumber() == null || account.getAccountNumber().trim().isEmpty()) {
            throw new BadRequestException("Account number is required");
        }

        if (account.getBalance() < 0) {
            throw new BadRequestException("Initial balance cannot be negative");
        }
    }
}