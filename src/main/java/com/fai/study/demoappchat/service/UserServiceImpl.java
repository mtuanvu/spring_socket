package com.fai.study.demoappchat.service;

import com.fai.study.demoappchat.dto.request.UserUpdateRequest;
import com.fai.study.demoappchat.dto.response.UserResponse;
import com.fai.study.demoappchat.entities.User;
import com.fai.study.demoappchat.mapper.UserMapper;
import com.fai.study.demoappchat.repositories.UserRepository;
import com.fai.study.demoappchat.utils.PageableData;
import com.fai.study.demoappchat.utils.PagingResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserServiceImpl implements UserService{
    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public UserResponse updateUser(String id, UserUpdateRequest request) {
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
    public PagingResponse<UserResponse> getAllUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> users = userRepository.findAll(pageable);

        List<UserResponse> userResponses = users.getContent()
                .stream()
                .map(userMapper::toUserResponse)
                .toList();

        PageableData pageableData = new PageableData()
                .setPageNumber(users.getNumber())
                .setPageSize(users.getSize())
                .setTotalPages(users.getTotalPages())
                .setTotalRecords(users.getTotalElements());

        return new PagingResponse<UserResponse>()
                .setContents(userResponses)
                .setPaging(pageableData);
    }
}
