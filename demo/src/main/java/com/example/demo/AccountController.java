package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping("/addCustomer")
    public Account createAccount(@RequestBody Account account) {
        return service.createAccount(account);
    }

    @GetMapping("/getCustomers")
    public List<Account> getAllAccounts() {
        return service.getAllAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAccountById(id));
    }

    @GetMapping("/{id}/simple")
    public ResponseEntity<Map<String, String>> getSimpleDetails(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAccountTypeAndNumber(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody AccountUpdateRequest request) {
        return ResponseEntity.ok(service.updateAccount(id, request));
    }

    @PutMapping("/{id}/deposit/{amount}")
    public Account deposit(@PathVariable Long id, @PathVariable double amount) {
        return service.deposit(id, amount);
    }

    @PutMapping("/{id}/withdraw/{amount}")
    public Account withdraw(@PathVariable Long id, @PathVariable double amount) {
        return service.withdraw(id, amount);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Map<String, Object>> transfer(@RequestBody TransferRequest request) {
        return ResponseEntity.ok(service.transferMoney(request));
    }

    @GetMapping("/{id}/summary")
    public ResponseEntity<Map<String, Object>> getSummary(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAccountSummary(id));
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountId) {
        service.deleteAccount(accountId);
        return ResponseEntity.ok("Account deleted successfully");
    }
}