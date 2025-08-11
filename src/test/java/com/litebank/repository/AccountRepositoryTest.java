package com.litebank.repository;

import com.litebank.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Sql(scripts = {"/db/data.sql"})
    void findByAccountNumber(){
        Optional<Account> accountNumber = accountRepository.findByAccountNumber("123456789");
        Account customerAccount = accountNumber.orElseThrow(RuntimeException::new);
        assertNotNull(customerAccount);
    }

}