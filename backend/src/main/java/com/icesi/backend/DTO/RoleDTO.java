package com.icesi.backend.DTO;

import lombok.Data;

import java.util.List;

@Data
public class RoleDTO {

    private String name;

    private String description;

    private List<PermissionUserDTO> rolePermissions;
}
