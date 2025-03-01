package com.fai.study.demoappchat.controllers;

import com.fai.study.demoappchat.dto.request.PermissionRequest;
import com.fai.study.demoappchat.dto.response.PermissionResponse;
import com.fai.study.demoappchat.service.PermissionService;
import com.fai.study.demoappchat.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .data(permissionService.createPermission(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<PermissionResponse>> getAllPermissions() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .data(permissionService.getAllPermissions())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<PermissionResponse> getPermissionById(@PathVariable("id") Long id) {
        return ApiResponse.<PermissionResponse>builder()
                .data(permissionService.getPermission(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<PermissionResponse> updatePermission(@PathVariable("id") Long id, @RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .data(permissionService.updatePermission(id, request))
                .build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePermission(@PathVariable("id") Long id) {
        permissionService.deletePermission(id);
        return ResponseEntity.noContent().build();
    }
}
