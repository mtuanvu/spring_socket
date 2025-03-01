package com.fai.study.demoappchat.service;

import com.fai.study.demoappchat.dto.request.PermissionRequest;
import com.fai.study.demoappchat.dto.response.PermissionResponse;
import com.fai.study.demoappchat.entities.Permission;
import com.fai.study.demoappchat.mapper.PermissionMapper;
import com.fai.study.demoappchat.repositories.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    public PermissionServiceImpl(PermissionRepository permissionRepository, PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public PermissionResponse createPermission(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    @Override
    public PermissionResponse updatePermission(Long id, PermissionRequest request) {
        Permission permission = permissionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Permission not found")
        );

        permissionMapper.updatePermission(permission, request);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    @Override
    public PermissionResponse getPermission(Long id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Permission not found")
        );

        return permissionMapper.toPermissionResponse(permission);
    }

    @Override
    public List<PermissionResponse> getAllPermissions() {
        return permissionRepository.findAll().stream()
                .map(permissionMapper::toPermissionResponse)
                .toList();
    }

    @Override
    public void deletePermission(Long id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Permission not found")
        );

        permissionRepository.delete(permission);
    }
}
