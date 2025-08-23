package com.litebank.service;

import com.litebank.dtos.request.DepositRequest;
import com.litebank.dtos.request.PaymentMethod;
import com.litebank.dtos.request.RegisterRequest;
import com.litebank.dtos.response.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    void testCanDeposit(){
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAccountNumber("123456789");
        depositRequest.setAmount(new BigDecimal("1000.00"));
        depositRequest.setPaymentMethod(PaymentMethod.CARD);

        DepositResponse depositResponse = accountService.deposit(depositRequest);
        assertNotNull(depositResponse);
        assertEquals(TransactionStatus.SUCCESS, depositResponse.getTransactionStatus());
    }

    @Test
    void testCanViewAccount() {
        ViewAccountResponse response = accountService.viewDetailsFor("123456789");

        assertThat(response).isNotNull();
        assertThat(response.getBalance()).isEqualTo(new BigDecimal("370000.00").toString());
    }

    @Test
    void testCanCreateAccount(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("Suliyat Olanrewaju");
        registerRequest.setUsername("sulisu");
        registerRequest.setPassword("password123");

        RegisterResponse response = accountService.createAccount(registerRequest);
        assertThat(response).isNotNull();
        assertEquals(AccountStatus.SUCCESS, response.getAccountStatus());
        assertNotNull(response.getAccountNumber());
    }
}
