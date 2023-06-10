package com.icesi.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class RoleCreateDTO {
    private String roleName;
    private String description;
}
