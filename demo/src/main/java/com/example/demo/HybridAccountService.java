package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HybridAccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AddressRepository addressRepository;
    private final ActivityLogRepository activityLogRepository;
    private final SupportTicketRepository supportTicketRepository;

    public HybridAccountService(AccountRepository accountRepository,
                                TransactionRepository transactionRepository,
                                AddressRepository addressRepository,
                                ActivityLogRepository activityLogRepository,
                                SupportTicketRepository supportTicketRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.addressRepository = addressRepository;
        this.activityLogRepository = activityLogRepository;
        this.supportTicketRepository = supportTicketRepository;
    }

    public Map<String, Object> getFullProfile(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        List<Transaction> transactions = transactionRepository.findByAccount_Id(accountId);
        Address address = addressRepository.findByAccount_Id(accountId).orElse(null);
        List<ActivityLog> activityLogs = activityLogRepository.findByAccountId(accountId);
        List<SupportTicket> supportTickets = supportTicketRepository.findByAccountId(accountId);

        Map<String, Object> response = new HashMap<>();
        response.put("account", account);
        response.put("address", address);
        response.put("transactions", transactions);
        response.put("activityLogs", activityLogs);
        response.put("supportTickets", supportTickets);

        return response;
    }
}