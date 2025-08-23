package com.litebank.service;

import com.litebank.dtos.request.DepositRequest;
import com.litebank.dtos.request.RegisterRequest;
import com.litebank.dtos.response.DepositResponse;
import com.litebank.dtos.response.RegisterResponse;
import com.litebank.dtos.response.ViewAccountResponse;


public interface AccountService  {

    DepositResponse deposit(DepositRequest depositRequest);

    ViewAccountResponse viewDetailsFor(String number);

    RegisterResponse createAccount(RegisterRequest registerAccount);
}
