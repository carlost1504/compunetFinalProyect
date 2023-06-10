package com.icesi.backend.service;

import com.icesi.backend.models.Role;

import java.awt.*;
import java.util.List;
import java.util.UUID;

public interface RoleServiceInterface {
    Role getRoleById(UUID roleId);
    Role getRoleByName(String roleName);
    Role createRole(Role role);
    Role updateRole(UUID roleId, Role role);
    void deleteRole(UUID roleId);
    List<Role> getAllRoles();
}
