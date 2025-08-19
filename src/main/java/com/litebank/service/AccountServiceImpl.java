package com.litebank.service;

import com.litebank.dtos.request.CreateTransactionRequest;
import com.litebank.dtos.request.DepositRequest;
import com.litebank.dtos.request.TransactionType;
import com.litebank.dtos.response.CreateTransactionResponse;
import com.litebank.dtos.response.DepositResponse;
import com.litebank.dtos.response.TransactionStatus;
import com.litebank.dtos.response.ViewAccountResponse;
import com.litebank.exception.AccountNotFoundException;
import com.litebank.model.Account;
import com.litebank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    @Override
    public DepositResponse deposit(DepositRequest depositRequest) {
        accountRepository.findByAccountNumber(depositRequest.getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException("account not found"));

        //TODO: create transaction record
        CreateTransactionRequest createTransactionRequest = getCreateTransactionRequest(depositRequest);

        var transactionResponse = transactionService.createTransaction(createTransactionRequest);
        return getDepositResponse(transactionResponse);
    }

    @Override
    public ViewAccountResponse viewDetailsFor(String accountNumber) {
//        List<TransactionResponse> transactions = transactionService.getTransactionsFor()
        return null;

    }

    private static DepositResponse getDepositResponse(CreateTransactionResponse transactionResponse) {
        DepositResponse depositResponse = new DepositResponse();
        depositResponse.setAmount(new BigDecimal(transactionResponse.getAmount()));
        depositResponse.setTransactionId(transactionResponse.getId());
        depositResponse.setTransactionStatus(TransactionStatus.SUCCESS);
        return depositResponse;
    }

    private static CreateTransactionRequest getCreateTransactionRequest(DepositRequest depositRequest) {
        CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest();
        createTransactionRequest.setAmount(depositRequest.getAmount());
        createTransactionRequest.setAccountNumber(depositRequest.getAccountNumber());
        createTransactionRequest.setTransactionType(TransactionType.CREDIT);
        return createTransactionRequest;
    }
}
