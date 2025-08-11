package com.litebank.service;

import com.litebank.dtos.request.DepositRequest;
import com.litebank.dtos.response.DepositResponse;
import com.litebank.exception.AccountNotFoundException;
import com.litebank.model.Account;
import com.litebank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public DepositResponse deposit(DepositRequest depositRequest) {
        Account foundAccount = accountRepository
                .findByAccountNumber(depositRequest.getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        //TODO: create transaction records
        return null;
    }
}
