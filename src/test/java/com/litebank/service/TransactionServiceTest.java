package com.litebank.service;

import com.litebank.dtos.request.CreateTransactionRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransactionServiceTest {



    @Test
    void testCanCreateTransaction() {
        CreateTransactionRequest transactionRequest = new CreateTransactionRequest();

    }
}
