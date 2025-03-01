package com.fai.study.demoappchat.service;

import com.fai.study.demoappchat.dto.request.RoleRequest;
import com.fai.study.demoappchat.dto.response.RoleResponse;
import com.fai.study.demoappchat.entities.Role;
import com.fai.study.demoappchat.mapper.RoleMapper;
import com.fai.study.demoappchat.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleResponse createRole(RoleRequest request) {
        Role role = roleMapper.toRole(request);
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    @Override
    public RoleResponse updateRole(Long id, RoleRequest request) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Role not found")
        );

        roleMapper.updateRole(role, request);
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    @Override
    public RoleResponse getRole(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Role not found")
        );

        return roleMapper.toRoleResponse(role);
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    @Override
    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Role not found")
        );

        roleRepository.delete(role);
    }
}
