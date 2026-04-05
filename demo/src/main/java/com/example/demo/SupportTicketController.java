package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/support-tickets")
public class SupportTicketController {

    private final SupportTicketService service;

    public SupportTicketController(SupportTicketService service) {
        this.service = service;
    }

    @PostMapping
    public SupportTicket create(@RequestBody SupportTicket ticket) {
        return service.create(ticket);
    }

    @GetMapping
    public List<SupportTicket> getAll() {
        return service.getAll();
    }

    @GetMapping("/account/{accountId}")
    public List<SupportTicket> getByAccount(@PathVariable Long accountId) {
        return service.getByAccountId(accountId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}