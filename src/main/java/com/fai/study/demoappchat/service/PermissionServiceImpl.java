package com.fai.study.demoappchat.service;

import com.fai.study.demoappchat.dto.request.PermissionRequest;
import com.fai.study.demoappchat.dto.response.PermissionResponse;
import com.fai.study.demoappchat.entities.Permission;
import com.fai.study.demoappchat.mapper.PermissionMapper;
import com.fai.study.demoappchat.repositories.PermissionRepository;
import com.fai.study.demoappchat.utils.PageableData;
import com.fai.study.demoappchat.utils.PagingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public PagingResponse<PermissionResponse> getAllPermissions(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Permission> permissions = permissionRepository.findAll(pageable);

        List<PermissionResponse> permissionResponses = permissions.getContent()
                .stream().map(permissionMapper::toPermissionResponse).toList();

        PageableData pageableData = new PageableData()
                .setPageNumber(permissions.getNumber())
                .setPageSize(permissions.getSize())
                .setTotalPages(permissions.getTotalPages())
                .setTotalRecords(permissions.getTotalElements());

        return new PagingResponse<PermissionResponse>()
                .setContents(permissionResponses)
                .setPaging(pageableData);
    }

    @Override
    public void deletePermission(Long id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Permission not found")
        );

        permissionRepository.delete(permission);
    }
}
