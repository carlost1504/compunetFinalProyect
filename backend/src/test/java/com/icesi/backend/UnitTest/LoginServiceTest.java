package com.icesi.backend.UnitTest;

import com.icesi.backend.DTO.LoginDTO;
import com.icesi.backend.DTO.TokenDTO;
import com.icesi.backend.error.exception.EShopException;
import com.icesi.backend.models.PermissionUser;
import com.icesi.backend.models.Role;
import com.icesi.backend.models.ShopUser;
import com.icesi.backend.respositories.RoleRepository;
import com.icesi.backend.respositories.UserRepository;
import com.icesi.backend.service.impl.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.*;

public class LoginServiceTest {

    @InjectMocks
    private LoginService loginService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginByEmail_Success() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("test@example.com");
        loginDTO.setPassword("password");

        ShopUser user = new ShopUser();
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userRepository.findByEmail(loginDTO.getUsername())).thenReturn(Optional.of(user));

        // Act
        TokenDTO tokenDTO = loginService.loginByEmail(loginDTO);

        // Assert
        Assertions.assertNotNull(tokenDTO);
        // Add more assertions if needed
    }

    @Test
    public void testLoginByEmail_UserNotFound() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("test@example.com");
        loginDTO.setPassword("password");

        when(userRepository.findByEmail(loginDTO.getUsername())).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(EShopException.class, () -> loginService.loginByEmail(loginDTO));
    }

    @Test
    public void testLoginByPhoneNumber_Success() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("1234567890");
        loginDTO.setPassword("password");

        ShopUser user = new ShopUser();
        user.setPhoneNumber("1234567890");
        user.setPassword("password");

        when(userRepository.findByPhoneNumber(loginDTO.getUsername())).thenReturn(Optional.of(user));

        // Act
        TokenDTO tokenDTO = loginService.loginByPhoneNumber(loginDTO);

        // Assert
        Assertions.assertNotNull(tokenDTO);
        // Add more assertions if needed
    }

    @Test
    public void testLoginByPhoneNumber_UserNotFound() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("1234567890");
        loginDTO.setPassword("password");

        when(userRepository.findByPhoneNumber(loginDTO.getUsername())).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(EShopException.class, () -> loginService.loginByPhoneNumber(loginDTO));
    }

    @Test
    public void testGetPermissionsByRoleId_Success() {
        // Arrange
        UUID roleId = UUID.randomUUID();

        Role role = new Role();
        role.setRolePermissions(Arrays.asList(new PermissionUser(), new PermissionUser()));

        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

        // Act
        List<PermissionUser> permissions = loginService.getPermissionsByRoleId(roleId);

        // Assert
        Assertions.assertEquals(2, permissions.size());
        // Add more assertions if needed
    }

    @Test
    public void testGetPermissionsByRoleId_RoleNotFound() {
        // Arrange
        UUID roleId = UUID.randomUUID();

        when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(EShopException.class, () -> loginService.getPermissionsByRoleId(roleId));
    }

    // Add more test cases for other methods if needed
}

