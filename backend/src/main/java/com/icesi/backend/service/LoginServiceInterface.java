package com.icesi.backend.service;


import com.icesi.backend.DTO.LoginDTO;
import com.icesi.backend.DTO.TokenDTO;
import com.icesi.backend.models.PermissionUser;

import java.util.List;
import java.util.UUID;

public interface LoginServiceInterface {

    TokenDTO loginByEmail(LoginDTO loginDTO);

    TokenDTO loginByPhoneNumber(LoginDTO loginDTO);

    List<PermissionUser> getPermissionsByRoleId(UUID role);

}
