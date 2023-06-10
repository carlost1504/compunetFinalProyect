package com.icesi.backend.controllers;

import com.icesi.backend.DTO.RoleDTO;
import com.icesi.backend.api.RoleAPI;
import com.icesi.backend.mappers.RoleMapper;
import com.icesi.backend.service.RoleServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class RoleController implements RoleAPI {

    private final RoleServiceInterface roleService;

    private final  RoleMapper roleMapper;


    @Override
    public RoleDTO getRoleById(UUID roleId) {
        return roleMapper.fromRole(roleService.getRoleById(roleId));
    }

    @Override
    public RoleDTO getRoleByName(String roleName) {
        return roleMapper.fromRole(roleService.getRoleByName(roleName));
    }

    @Override
    public RoleDTO createRole(RoleDTO role) {
        return roleMapper.fromRole(roleService.createRole(roleMapper.fromDTO(role)));
    }

    @Override
    public RoleDTO updateRole(UUID roleId, RoleDTO role) {
        return roleMapper.fromRole(roleService.updateRole(roleId, roleMapper.fromDTO(role)));
    }

    @Override
    public void deleteRole(UUID roleId) {
        roleService.deleteRole(roleId);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleService.getAllRoles().stream().map(roleMapper::fromRole).toList();
    }
}
