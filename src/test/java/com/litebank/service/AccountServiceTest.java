package com.litebank.service;

import com.litebank.dtos.request.DepositRequest;
import com.litebank.dtos.request.PaymentMethod;
import com.litebank.dtos.response.DepositResponse;
import com.litebank.dtos.response.TransactionStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    @Sql(scripts = {"/db/data.sql"})
    void testCanDeposit(){
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAccountNumber("987654321");
        depositRequest.setAmount(new BigDecimal("1000.00"));
        depositRequest.setPaymentMethod(PaymentMethod.CARD);

        DepositResponse depositResponse = accountService.deposit(depositRequest);
        assertNotNull(depositResponse);
        assertEquals(depositResponse.getTransactionStatus(), TransactionStatus.SUCCESS);

    }
}
