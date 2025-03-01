package com.fai.study.demoappchat.service;

import com.fai.study.demoappchat.dto.request.RoleRequest;
import com.fai.study.demoappchat.dto.response.RoleResponse;
import com.fai.study.demoappchat.utils.PagingResponse;


public interface RoleService {
    RoleResponse createRole(RoleRequest request);
    RoleResponse updateRole(Long id, RoleRequest request);
    RoleResponse getRole(Long id);
    PagingResponse<RoleResponse> getAllRoles(int pageNumber, int pageSize);
    void deleteRole(Long id);
}
