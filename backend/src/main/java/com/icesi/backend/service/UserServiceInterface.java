package com.icesi.backend.service;

import com.icesi.backend.DTO.UserCreateDTO;
import com.icesi.backend.DTO.UserUpdateDTO;
import com.icesi.backend.models.ShopUser;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface UserServiceInterface {


    ShopUser getUser();

    Optional<ShopUser> createUser(@RequestBody UserCreateDTO user);

    Optional<ShopUser> updateUser(UserUpdateDTO userUpdateDTO);
}
