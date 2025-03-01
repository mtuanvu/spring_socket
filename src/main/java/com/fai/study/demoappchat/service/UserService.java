package com.fai.study.demoappchat.service;

import com.fai.study.demoappchat.dto.request.UserRequest;
import com.fai.study.demoappchat.dto.request.UserUpdateRequest;
import com.fai.study.demoappchat.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse updateUser(String id, UserUpdateRequest request);
    UserResponse getUser(String id);
    List<UserResponse> getAllUsers();
}
