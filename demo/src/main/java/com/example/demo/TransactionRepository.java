package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount_Id(Long accountId);
    List<Transaction> findByAccount_IdAndTransactionTypeIgnoreCase(Long accountId, String transactionType);
    List<Transaction> findByAccount_IdAndTransactionDateBetween(Long accountId, Date startDate, Date endDate);
}