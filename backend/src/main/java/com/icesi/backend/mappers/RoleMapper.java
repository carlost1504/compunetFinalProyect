package com.icesi.backend.mappers;


import com.icesi.backend.DTO.RoleCreateDTO;
import com.icesi.backend.DTO.RoleDTO;
import com.icesi.backend.models.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role fromRoleCreateDTO(RoleCreateDTO roleCreateDTO);

    Role fromDTO(RoleDTO roleDTO);

    RoleDTO fromRole(Role role);

}
