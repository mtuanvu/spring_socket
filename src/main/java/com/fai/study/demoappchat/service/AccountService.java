package com.fai.study.demoappchat.service;

import com.fai.study.demoappchat.dto.request.AccountRequest;
import com.fai.study.demoappchat.dto.request.UserRequest;
import com.fai.study.demoappchat.dto.response.AccountResponse;
import com.fai.study.demoappchat.dto.response.UserResponse;

import java.util.List;

public interface AccountService {
    UserResponse createAccount(AccountRequest request, UserRequest userRequest);
    AccountResponse updateAccount(String id, AccountRequest request);
    AccountResponse getAccount(String id);
    List<AccountResponse> getAllAccounts();
    void deleteAccount(String id);
}
