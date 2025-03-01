package com.fai.study.demoappchat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RoleRequest {
    private String name;
    private Set<Long> permissions;
}
