package com.icesi.backend.service.impl;

import com.icesi.backend.models.Role;
import com.icesi.backend.respositories.RoleRepository;
import com.icesi.backend.service.RoleServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleService implements RoleServiceInterface {
    private final RoleRepository roleRepository;


    @Override
    public Role getRoleById(UUID roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.findByName(roleName).orElse(null);
    }

    @Override
    public Role createRole(Role role) {
        if (roleRepository.findByName(role.getRoleName()).isPresent()) {
            return null;
        }
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(UUID roleId, Role role) {
        Role toUpdateRole = roleRepository.findById(roleId).orElse(null);
        if (toUpdateRole == null) {
            return null;
        }else{
            toUpdateRole.setRoleName(role.getRoleName());
            toUpdateRole.setDescription(role.getDescription());
            toUpdateRole.setRolePermissions(role.getRolePermissions());
            return roleRepository.save(toUpdateRole);
        }
    }

    @Override
    public void deleteRole(UUID roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
