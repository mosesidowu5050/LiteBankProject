package com.litebank.service;

import com.litebank.dtos.request.CreateTransactionRequest;
import com.litebank.dtos.request.DepositRequest;
import com.litebank.dtos.request.RegisterRequest;
import com.litebank.dtos.request.TransactionType;
import com.litebank.dtos.response.*;
import com.litebank.exception.AccountNotFoundException;
import com.litebank.model.Account;
import com.litebank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.litebank.util.ProjectUtil.*;
import static java.math.BigDecimal.ZERO;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;


    @Override
    public RegisterResponse createAccount(RegisterRequest registerAccount) {
        Account account = getRegisteredAccount(registerAccount);

        String accountNumber = generateAccountNumber();
        account.setAccountNumber(accountNumber);
        accountRepository.save(account);

        return getRegisterResponse(accountNumber);
    }

    private static RegisterResponse getRegisterResponse(String accountNumber) {
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setAccountStatus(AccountStatus.SUCCESS);
        registerResponse.setMessage(ACCOUNT_CREATED_SUCCESSFULLY);
        registerResponse.setAccountNumber(accountNumber);

        return registerResponse;
    }

    private static Account getRegisteredAccount(RegisterRequest registerAccount) {
        Account account = new Account();
        account.setName(registerAccount.getName());
        account.setUsername(registerAccount.getUsername());
        account.setPassword(registerAccount.getPassword());
        return account;
    }

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

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setAmount(ZERO.toString());

        TransactionResponse response = transactionService.getTransactionsFor(accountNumber).stream()
                .reduce(transactionResponse, (a,b)->
                    calculateAccountBalanceFrom(a, b, transactionResponse));

        ViewAccountResponse viewAccountResponse = new ViewAccountResponse();
        viewAccountResponse.setBalance(transactionResponse.getAmount());

        return viewAccountResponse;
    }



    private static TransactionResponse calculateAccountBalanceFrom(TransactionResponse a, TransactionResponse b, TransactionResponse transactionResponse) {
        BigDecimal total = ZERO;
        if(b.getTransactionType() == TransactionType.DEPOSIT)
            total = total.add(new  BigDecimal(b.getAmount()));
        else
            total = total.subtract(new BigDecimal(b.getAmount()));

        transactionResponse.setAmount(
                new BigDecimal(a.getAmount())
                        .add(total).toString());
        return transactionResponse;
    }

    public ViewAccountResponse viewDetailsFullImplementationFor(String accountNumber) {
        List<TransactionResponse> transactions =transactionService.getTransactionsFor(accountNumber);
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setAmount(ZERO.toString());

        TransactionResponse response = transactions.stream()
                .reduce(transactionResponse, (a,b)->{
                    BigDecimal total = ZERO;
                    if(b.getTransactionType() == TransactionType.DEPOSIT)
                        total = total.add(new  BigDecimal(b.getAmount()));
                    else
                        total = total.subtract(new BigDecimal(b.getAmount()));

                    transactionResponse.setAmount(
                            new BigDecimal(a.getAmount())
                                    .add(total).toString());
                    return transactionResponse;
                });

        ViewAccountResponse viewAccountResponse = new ViewAccountResponse();
        viewAccountResponse.setBalance(transactionResponse.getAmount());

        return viewAccountResponse;
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

    private String generateAccountNumber() {
        String accountNumber;
        do {
            StringBuilder randomAccountNumber = new StringBuilder(10);
            randomAccountNumber.append((int) (Math.random() * 9) + 1);
            for (int count = 1; count < 10; count++) {
                randomAccountNumber.append((int) (Math.random() * 10));
            }
            accountNumber = randomAccountNumber.toString();
        } while (accountRepository.findByAccountNumber(accountNumber).isPresent());
        return accountNumber;
    }
}
