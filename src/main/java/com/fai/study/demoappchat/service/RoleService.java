package com.fai.study.demoappchat.service;

import com.fai.study.demoappchat.dto.request.RoleRequest;
import com.fai.study.demoappchat.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse createRole(RoleRequest request);
    RoleResponse updateRole(Long id, RoleRequest request);
    RoleResponse getRole(Long id);
    List<RoleResponse> getAllRoles();
    void deleteRole(Long id);
}
