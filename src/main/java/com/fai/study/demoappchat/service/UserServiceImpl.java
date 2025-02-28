package com.fai.study.demoappchat.service;

import com.fai.study.demoappchat.dto.request.UserRequest;
import com.fai.study.demoappchat.dto.response.UserResponse;
import com.fai.study.demoappchat.entities.Account;
import com.fai.study.demoappchat.entities.User;
import com.fai.study.demoappchat.mapper.UserMapper;
import com.fai.study.demoappchat.repositories.AccountRepository;
import com.fai.study.demoappchat.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserServiceImpl implements UserService{
    UserRepository userRepository;
    AccountRepository accountRepository;
    UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = userMapper.toUser(request);

        if (request.getAccountId() != null) {
            Account account = accountRepository.findByAccountId(request.getAccountId()).orElseThrow(() -> new RuntimeException("Account not found"));
            user.setAccount(account);
        }
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUser(String id, UserRequest request) {
        User user = userRepository.findByUserId(id).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse getUser(String id) {
        User user = userRepository.findByUserId(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAllUsers().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public void deleteUser(String id) {
        User userNotFound = userRepository.findByUserId(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(userNotFound);
    }
}
