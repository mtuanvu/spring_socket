package com.fai.study.demoappchat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RegisterRequest {
    private AccountRequest account;
    private UserRequest user;
}
