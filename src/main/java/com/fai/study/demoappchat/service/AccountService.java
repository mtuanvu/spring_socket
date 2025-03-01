package com.fai.study.demoappchat.service;

import com.fai.study.demoappchat.dto.request.AccountRequest;
import com.fai.study.demoappchat.dto.request.UserRequest;
import com.fai.study.demoappchat.dto.response.AccountResponse;
import com.fai.study.demoappchat.dto.response.UserResponse;
import com.fai.study.demoappchat.utils.PagingResponse;


public interface AccountService {
    UserResponse createAccount(AccountRequest request, UserRequest userRequest);
    AccountResponse updateAccount(String id, AccountRequest request);
    AccountResponse getAccount(String id);
    PagingResponse<AccountResponse> getAllAccounts(int pageNumber, int pageSize);
    void deleteAccount(String id);
}
