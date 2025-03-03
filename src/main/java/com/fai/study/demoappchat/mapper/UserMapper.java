package com.fai.study.demoappchat.mapper;

import com.fai.study.demoappchat.dto.request.UserRequest;
import com.fai.study.demoappchat.dto.request.UserUpdateRequest;
import com.fai.study.demoappchat.dto.response.UserResponse;
import com.fai.study.demoappchat.entities.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registrationDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "dob", source = "birthday")
    User toUser(UserRequest request);

    @Mapping(target = "roles", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "dob", source = "birthday")
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    @Mapping(target = "account", source = "account", qualifiedByName = "toAccountResponse")
    @Mapping(target = "birthDate", source = "dob")
    UserResponse toUserResponse(User user);

}
