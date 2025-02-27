package com.fai.study.demoappchat.controllers;

import com.fai.study.demoappchat.dto.request.AccountRequest;
import com.fai.study.demoappchat.dto.response.AccountResponse;
import com.fai.study.demoappchat.service.AccountService;
import com.fai.study.demoappchat.utils.ApiResponse;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ApiResponse<AccountResponse> createAccount(@RequestBody AccountRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .data(accountService.createAccount(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<AccountResponse>> getAccounts() {
        return ApiResponse.<List<AccountResponse>>builder()
                .data(accountService.getAllAccounts())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<AccountResponse> getAccount(@PathVariable String id) {
        return ApiResponse.<AccountResponse>builder()
                .data(accountService.getAccount(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<AccountResponse> updateAccount(@PathVariable String id, @RequestBody AccountRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .data(accountService.updateAccount(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

}
