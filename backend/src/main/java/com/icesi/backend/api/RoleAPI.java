package com.icesi.backend.api;

import com.icesi.backend.DTO.RoleDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/roles")
public interface RoleAPI {
    @GetMapping("/id/{roleId}")
    RoleDTO getRoleById( @PathVariable UUID roleId);

    @GetMapping("/name/{roleName}")
    RoleDTO getRoleByName(@PathVariable String roleName);

    @PostMapping
    RoleDTO createRole(@RequestBody RoleDTO role);

    @PutMapping("/{roleId}")
    RoleDTO updateRole(@PathVariable UUID roleId, @RequestBody RoleDTO role);

    @DeleteMapping("/{roleId}")
    void deleteRole( @PathVariable UUID roleId);

    @GetMapping
    List<RoleDTO> getAllRoles();
}
