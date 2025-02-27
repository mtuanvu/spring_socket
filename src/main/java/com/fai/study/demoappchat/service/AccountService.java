package com.fai.study.demoappchat.service;

import com.fai.study.demoappchat.dto.request.AccountRequest;
import com.fai.study.demoappchat.dto.response.AccountResponse;

import java.util.List;

public interface AccountService {
    AccountResponse createAccount(AccountRequest request);
    AccountResponse updateAccount(String id, AccountRequest request);
    AccountResponse getAccount(String id);
    List<AccountResponse> getAllAccounts();
    void deleteAccount(String id);
}
