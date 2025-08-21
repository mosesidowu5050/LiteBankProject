package com.litebank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.litebank.dtos.request.DepositRequest;
import com.litebank.dtos.request.PaymentMethod;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/db/data.sql"})
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCanDeposit() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAccountNumber("123456789");
        depositRequest.setAmount(new BigDecimal("200000.00"));
        depositRequest.setPaymentMethod(PaymentMethod.CARD);

        String json = mapper.writeValueAsString(depositRequest);

        String depositEndpoint = "/api/v1/account";
        mockMvc.perform(MockMvcRequestBuilders.post(depositEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }


}
