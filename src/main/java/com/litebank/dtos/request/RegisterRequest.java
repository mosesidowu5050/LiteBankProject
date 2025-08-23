package com.litebank.dtos.request;


import lombok.*;


@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RegisterRequest {

        private String name;
        private String username;
        private String password;

}
