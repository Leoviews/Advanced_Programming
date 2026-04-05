package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupportTicketService {

    private final SupportTicketRepository repository;

    public SupportTicketService(SupportTicketRepository repository) {
        this.repository = repository;
    }

    public SupportTicket create(SupportTicket ticket) {
        return repository.save(ticket);
    }

    public List<SupportTicket> getAll() {
        return repository.findAll();
    }

    public List<SupportTicket> getByAccountId(Long accountId) {
        return repository.findByAccountId(accountId);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}