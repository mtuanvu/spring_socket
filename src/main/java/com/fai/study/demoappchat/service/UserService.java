package com.fai.study.demoappchat.service;

import com.fai.study.demoappchat.dto.request.UserRequest;
import com.fai.study.demoappchat.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(String id, UserRequest request);
    UserResponse getUser(String id);
    List<UserResponse> getAllUsers();
    void deleteUser(String id);
}
