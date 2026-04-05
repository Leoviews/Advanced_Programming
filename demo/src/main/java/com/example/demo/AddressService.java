package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepo;
    private final AccountRepository accountRepo;

    public AddressService(AddressRepository addressRepo, AccountRepository accountRepo) {
        this.addressRepo = addressRepo;
        this.accountRepo = accountRepo;
    }

    public Address saveOrUpdateAddress(Long accountId, AddressRequest request) {
        if (request.getStreet() == null || request.getStreet().trim().isEmpty()) {
            throw new BadRequestException("Street is required");
        }
        if (request.getCity() == null || request.getCity().trim().isEmpty()) {
            throw new BadRequestException("City is required");
        }
        if (request.getCountry() == null || request.getCountry().trim().isEmpty()) {
            throw new BadRequestException("Country is required");
        }
        if (request.getPincode() == null || request.getPincode().trim().isEmpty()) {
            throw new BadRequestException("Pincode is required");
        }

        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        Address address = addressRepo.findByAccount_Id(accountId).orElse(new Address());

        address.setStreet(request.getStreet().trim());
        address.setCity(request.getCity().trim());
        address.setCountry(request.getCountry().trim());
        address.setPincode(request.getPincode().trim());
        address.setAccount(account);

        return addressRepo.save(address);
    }

    public Address getAddressByAccountId(Long accountId) {
        return addressRepo.findByAccount_Id(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found for account"));
    }

    public void deleteAddressByAccountId(Long accountId) {
        Address address = addressRepo.findByAccount_Id(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found for account"));

        addressRepo.delete(address);
    }
}