package com.fai.study.demoappchat.controllers;

import com.fai.study.demoappchat.dto.request.RoleRequest;
import com.fai.study.demoappchat.dto.response.RoleResponse;
import com.fai.study.demoappchat.service.RoleService;
import com.fai.study.demoappchat.utils.ApiResponse;
import com.fai.study.demoappchat.utils.PagingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder().data(roleService.createRole(request)).build();
    }


    @GetMapping
    public ApiResponse<PagingResponse<RoleResponse>> getRoles(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return ApiResponse.<PagingResponse<RoleResponse>>builder()
                .data(roleService.getAllRoles(pageNumber, pageSize))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<RoleResponse> getRole(@PathVariable Long id) {
        return ApiResponse.<RoleResponse>builder().data(roleService.getRole(id)).build();
    }


    @PutMapping("/{id}")
    public ApiResponse<RoleResponse> updateRole(@PathVariable Long id, @RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder().data(roleService.updateRole(id, request)).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok().build();
    }
}
