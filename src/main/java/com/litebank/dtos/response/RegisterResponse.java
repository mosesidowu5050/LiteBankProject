package com.litebank.dtos.response;

import lombok.Data;

@Data
public class RegisterResponse {

    private AccountStatus accountStatus;
    private String accountNumber;
    private String message;

}
