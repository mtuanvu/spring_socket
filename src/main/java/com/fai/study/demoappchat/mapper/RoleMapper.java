package com.fai.study.demoappchat.mapper;

import com.fai.study.demoappchat.dto.request.RoleRequest;
import com.fai.study.demoappchat.dto.response.RoleResponse;
import com.fai.study.demoappchat.entities.Role;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    @Mapping(target = "permissions", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRole(@MappingTarget Role role, RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
