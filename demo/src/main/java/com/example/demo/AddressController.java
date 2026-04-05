package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping("/{accountId}")
    public Address addAddress(@PathVariable Long accountId, @RequestBody AddressRequest request) {
        return service.saveOrUpdateAddress(accountId, request);
    }

    @PutMapping("/{accountId}")
    public Address updateAddress(@PathVariable Long accountId, @RequestBody AddressRequest request) {
        return service.saveOrUpdateAddress(accountId, request);
    }

    @GetMapping("/{accountId}")
    public Address getAddress(@PathVariable Long accountId) {
        return service.getAddressByAccountId(accountId);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long accountId) {
        service.deleteAddressByAccountId(accountId);
        return ResponseEntity.ok("Address deleted successfully");
    }
}