package com.fai.study.demoappchat.service;

import com.fai.study.demoappchat.dto.request.UserUpdateRequest;
import com.fai.study.demoappchat.dto.response.UserResponse;
import com.fai.study.demoappchat.utils.PagingResponse;

public interface UserService {
    UserResponse updateUser(String id, UserUpdateRequest request);
    UserResponse getUser(String id);
    PagingResponse<UserResponse> getAllUsers(int pageNumber, int pageSize);
}
