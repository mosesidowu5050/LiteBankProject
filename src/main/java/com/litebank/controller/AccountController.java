package com.litebank.controller;

import com.litebank.dtos.request.DepositRequest;
import com.litebank.dtos.response.DepositResponse;
import com.litebank.dtos.response.ErrorResponse;
import com.litebank.exception.AccountNotFoundException;
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
    public ResponseEntity<?> deposit(DepositRequest depositRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(accountService.deposit(depositRequest));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }

}
