package com.fai.study.demoappchat.mapper;

import com.fai.study.demoappchat.dto.request.PermissionRequest;
import com.fai.study.demoappchat.dto.response.PermissionResponse;
import com.fai.study.demoappchat.entities.Permission;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePermission(@MappingTarget Permission permission, PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
