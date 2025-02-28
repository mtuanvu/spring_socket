package com.fai.study.demoappchat.controllers;

import com.fai.study.demoappchat.dto.request.UserRequest;
import com.fai.study.demoappchat.dto.response.UserResponse;
import com.fai.study.demoappchat.service.UserService;
import com.fai.study.demoappchat.utils.ApiResponse;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .data(userService.getAllUsers())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable String id) {
        return ApiResponse.<UserResponse>builder()
                .data(userService.getUser(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .data(userService.updateUser(id, request))
                .build();
    }
}
