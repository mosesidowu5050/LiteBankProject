package com.litebank.service;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CreateTransactionResponse {

    private String id;
    private String transactionType;
    private String amount;

}
