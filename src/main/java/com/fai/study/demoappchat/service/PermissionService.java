package com.fai.study.demoappchat.service;

import com.fai.study.demoappchat.dto.request.PermissionRequest;
import com.fai.study.demoappchat.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    PermissionResponse createPermission(PermissionRequest request);
    PermissionResponse updatePermission(Long id, PermissionRequest request);
    PermissionResponse getPermission(Long id);
    List<PermissionResponse> getAllPermissions();
    void deletePermission(Long id);
}
