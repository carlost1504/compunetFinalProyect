package com.icesi.backend.mappers;


import com.icesi.backend.DTO.PermissionUserDTO;
import com.icesi.backend.models.PermissionUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionUserMapper {

    PermissionUser fromDTO(PermissionUserDTO permissionDTO);

    PermissionUserDTO fromPermission(PermissionUser permission);

    List<PermissionUser> fromListDTO(List<PermissionUserDTO> permissionDTOList);

    List<PermissionUserDTO> fromListPermission(List<PermissionUser> permissionList);

}
