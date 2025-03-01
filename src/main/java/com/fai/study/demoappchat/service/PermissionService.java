package com.fai.study.demoappchat.service;

import com.fai.study.demoappchat.dto.request.PermissionRequest;
import com.fai.study.demoappchat.dto.response.PermissionResponse;
import com.fai.study.demoappchat.utils.PagingResponse;


public interface PermissionService {
    PermissionResponse createPermission(PermissionRequest request);
    PermissionResponse updatePermission(Long id, PermissionRequest request);
    PermissionResponse getPermission(Long id);
    PagingResponse<PermissionResponse> getAllPermissions(int pageNumber, int pageSize);
    void deletePermission(Long id);
}
