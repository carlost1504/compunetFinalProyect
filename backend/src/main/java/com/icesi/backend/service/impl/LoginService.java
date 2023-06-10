package com.icesi.backend.service.impl;



import com.icesi.backend.DTO.LoginDTO;
import com.icesi.backend.DTO.TokenDTO;
import com.icesi.backend.error.exception.EShopError;
import com.icesi.backend.error.exception.EShopException;
import com.icesi.backend.errorConstants.BackendApplicationErrors;
import com.icesi.backend.models.PermissionUser;
import com.icesi.backend.models.Role;
import com.icesi.backend.models.ShopUser;
import com.icesi.backend.respositories.RoleRepository;
import com.icesi.backend.respositories.UserRepository;
import com.icesi.backend.service.LoginServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Service
public class LoginService implements LoginServiceInterface {

    public final UserRepository userRepository;

    public final RoleRepository roleRepository;

    @Override
    public TokenDTO loginByEmail(LoginDTO loginDTO) {
        ShopUser user = userRepository.findByEmail(loginDTO.getUsername()).orElseThrow(()->new EShopException(HttpStatus.NOT_FOUND, new EShopError(BackendApplicationErrors.CODE_U_01, BackendApplicationErrors.CODE_U_01.getMessage())));
        validatePassword(user.getPassword(), loginDTO.getPassword());
        return createTokenDTO(user);
    }

    @Override
    public TokenDTO loginByPhoneNumber(LoginDTO loginDTO) {
        ShopUser user = userRepository.findByPhoneNumber(loginDTO.getUsername()).orElseThrow(()->new EShopException(HttpStatus.NOT_FOUND, new EShopError(BackendApplicationErrors.CODE_U_01, BackendApplicationErrors.CODE_U_01.getMessage())));
        validatePassword(user.getPassword(), loginDTO.getPassword());
        return createTokenDTO(user);
    }

    @Override
    public List<PermissionUser> getPermissionsByRoleId(UUID roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(()->new EShopException(HttpStatus.NOT_FOUND, new EShopError(BackendApplicationErrors.CODE_L_04, BackendApplicationErrors.CODE_L_04.getMessage())));
        return role.getRolePermissions();
    }

    private TokenDTO createTokenDTO(ShopUser user) {
        Map<String, String> claims = new HashMap<>();
        claims.put("userId", user.getUserId().toString());
        claims.put("roleId", user.getRole().getRoleId().toString());
        return new TokenDTO(Token_Parser.createJWT(user.getUserId().toString(), user.getEmail(), user.getEmail(), claims, 1000L*60*20), user.getRole().getRoleName(), user.getUserId().toString());
    }

    private void validatePassword(String userPassword, String loginDTOPassword) {
        if (!userPassword.equals(loginDTOPassword))
            throw new EShopException(HttpStatus.BAD_REQUEST, new EShopError(BackendApplicationErrors.CODE_L_02, BackendApplicationErrors.CODE_L_02.getMessage()));
    }

}
