package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/hybrid/accounts")
public class HybridAccountController {

    private final HybridAccountService hybridAccountService;

    public HybridAccountController(HybridAccountService hybridAccountService) {
        this.hybridAccountService = hybridAccountService;
    }

    @GetMapping("/{accountId}/full-profile")
    public ResponseEntity<Map<String, Object>> getFullProfile(@PathVariable Long accountId) {
        return ResponseEntity.ok(hybridAccountService.getFullProfile(accountId));
    }
}