package com.litebank.repository;

import com.litebank.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepository extends JpaRepository<Transaction, String> {

    Page<Transaction> readByAccountNumber(String accountNumber, Pageable pageable);

}
