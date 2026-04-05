package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActivityLogService {

    private final ActivityLogRepository repository;

    public ActivityLogService(ActivityLogRepository repository) {
        this.repository = repository;
    }

    public ActivityLog create(ActivityLog log) {
        return repository.save(log);
    }

    public List<ActivityLog> getAll() {
        return repository.findAll();
    }

    public List<ActivityLog> getByAccountId(Long accountId) {
        return repository.findByAccountId(accountId);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}