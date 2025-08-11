package com.litebank.service;

import com.litebank.dtos.request.CreateTransactionRequest;
import com.litebank.dtos.response.TransactionResponse;
import org.springframework.stereotype.Service;


public interface TransactionService {

    CreateTransactionResponse createTransaction(CreateTransactionRequest transactionRequest);

    TransactionResponse getTransactionBy(String id);
}
