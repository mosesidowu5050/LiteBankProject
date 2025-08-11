package com.litebank.service;

import com.litebank.dtos.request.CreateTransactionRequest;
import com.litebank.dtos.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionService transactionService;

    @Override
    public CreateTransactionResponse createTransaction(CreateTransactionRequest transactionRequest) {
        return null;
    }

    @Override
    public TransactionResponse getTransactionBy(String id) {
        return null;
    }
}
