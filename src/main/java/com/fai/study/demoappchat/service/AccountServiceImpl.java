package com.fai.study.demoappchat.service;

import com.fai.study.demoappchat.dto.request.AccountRequest;
import com.fai.study.demoappchat.dto.request.UserRequest;
import com.fai.study.demoappchat.dto.response.AccountResponse;
import com.fai.study.demoappchat.dto.response.UserResponse;
import com.fai.study.demoappchat.entities.Account;
import com.fai.study.demoappchat.entities.User;
import com.fai.study.demoappchat.mapper.AccountMapper;
import com.fai.study.demoappchat.mapper.UserMapper;
import com.fai.study.demoappchat.repositories.AccountRepository;
import com.fai.study.demoappchat.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    UserMapper userMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, PasswordEncoder passwordEncoder, UserRepository userRepository, UserMapper userMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse createAccount(AccountRequest request, UserRequest userRequest) {
        if (accountRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        Account account = accountMapper.toAccount(request);
        account.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        account = accountRepository.save(account);

        User user = userMapper.toUser(userRequest);
        user.setAccount(account);

        userRepository.save(user);
        //chua them hash password
        return userMapper.toUserResponse(user);
    }

    @Override
    public AccountResponse updateAccount(String id, AccountRequest request) {
        Account account = accountRepository.findByAccountId(id).orElseThrow(
                () -> new RuntimeException("Account not found")
        );

        accountMapper.updateAccount(account, request);

        return accountMapper.toAccountResponse(accountRepository.save(account));
    }

    @Override
    public AccountResponse getAccount(String id) {
        Account account = accountRepository.findByAccountId(id).orElseThrow(
                () -> new RuntimeException("Account not found")
        );

        return accountMapper.toAccountResponse(account);
    }

    @Override
    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAllAccount().stream()
                .map(accountMapper::toAccountResponse)
                .toList();
    }

    @Override
    public void deleteAccount(String id) {
        Account account = accountRepository.findByAccountId(id).orElseThrow(
                () -> new RuntimeException("Account not found")
        );

        accountRepository.delete(account);
    }
}
