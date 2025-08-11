package com.litebank.service;

import com.litebank.dtos.request.DepositRequest;
import com.litebank.dtos.response.DepositResponse;

public interface AccountService  {

    DepositResponse deposit(DepositRequest depositRequest);
}
