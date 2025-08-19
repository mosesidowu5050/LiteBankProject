package com.litebank.service;

import com.litebank.dtos.request.DepositRequest;
import com.litebank.dtos.response.DepositResponse;
import com.litebank.dtos.response.TransactionResponse;
import com.litebank.dtos.response.ViewAccountResponse;

import java.util.List;

public interface AccountService  {

    DepositResponse deposit(DepositRequest depositRequest);

    ViewAccountResponse viewDetailsFor(String number);

}
