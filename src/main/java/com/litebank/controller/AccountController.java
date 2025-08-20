package com.litebank.controller;

import com.litebank.dtos.request.DepositRequest;
import com.litebank.dtos.response.DepositResponse;
import com.litebank.dtos.response.TransactionResponse;
import com.litebank.model.Transaction;
import com.litebank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<?> createAccount(DepositRequest depositRequest) {
        try {
            DepositResponse depositResponse = accountService.deposit(depositRequest);
            return new ResponseEntity<>(depositResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
