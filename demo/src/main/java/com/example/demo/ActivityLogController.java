package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/activity-logs")
public class ActivityLogController {

    private final ActivityLogService service;

    public ActivityLogController(ActivityLogService service) {
        this.service = service;
    }

    @PostMapping
    public ActivityLog create(@RequestBody ActivityLog log) {
        return service.create(log);
    }

    @GetMapping
    public List<ActivityLog> getAll() {
        return service.getAll();
    }

    @GetMapping("/account/{accountId}")
    public List<ActivityLog> getByAccount(@PathVariable Long accountId) {
        return service.getByAccountId(accountId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}