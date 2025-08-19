package com.litebank.service;

import com.litebank.dtos.request.CreateTransactionRequest;
import com.litebank.dtos.response.CreateTransactionResponse;
import com.litebank.dtos.response.TransactionResponse;
import com.litebank.exception.TransactionIdNotFoundException;
import com.litebank.model.Transaction;
import com.litebank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;


    @Override
    public CreateTransactionResponse createTransaction(CreateTransactionRequest transactionRequest) {
        Transaction transaction = createTransactionFrom(transactionRequest);
        transaction = transactionRepository.save(transaction);
        return buildTransactionResponseFrom(transaction);
    }

    private CreateTransactionResponse buildTransactionResponseFrom(Transaction transaction) {
//        CreateTransactionResponse createTransactionResponse = new CreateTransactionResponse();
//        createTransactionResponse.setId(transaction.getId());
//        createTransactionResponse.setAmount(transaction.getAmount().toString());
//        createTransactionResponse.setTransactionType(transaction.getTransactionType().toString());
//        return createTransactionResponse;


        return modelMapper.map(transaction, CreateTransactionResponse.class);

    }

    private Transaction createTransactionFrom(CreateTransactionRequest transactionRequest) {
//        Transaction transaction = new Transaction();
//        transaction.setAmount(transactionRequest.getAmount());
//        transaction.setTransactionType(transactionRequest.getTransactionType());
//        transaction.setAccountNumber(transactionRequest.getAccountNumber());
//        return transaction;


        return modelMapper.map(transactionRequest, Transaction.class);

    }

    @Override
    public TransactionResponse getTransactionBy(String id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(()-> new RuntimeException("transaction not found"));
//        TransactionResponse transactionResponse = new TransactionResponse();
//        transactionResponse.setAmount(transaction.getAmount().toString());
//        return transactionResponse;

        return modelMapper.map(transaction, TransactionResponse.class);
    }

    @Override
    public List<TransactionResponse> getTransactionsFor() {
        return List.of();
    }
}
