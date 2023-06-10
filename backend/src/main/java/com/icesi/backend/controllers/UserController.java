package com.icesi.backend.controllers;

import com.icesi.backend.DTO.UserCreateDTO;
import com.icesi.backend.DTO.UserUpdateDTO;
import com.icesi.backend.models.ShopUser;
import com.icesi.backend.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")

public class UserController {


    private final UserService userService;

    @GetMapping()
    public List<ShopUser> getUser(){
        return (List<ShopUser>) userService.getUser();
    }
    @PostMapping("/create")
    public ShopUser createUser(@RequestBody UserCreateDTO userCreateDTO){
        return userService.createUser(userCreateDTO).get();
    }

    @PostMapping("/update")
    public ShopUser updateUser(@RequestBody UserUpdateDTO userUpdateDTO){
        return userService.updateUser(userUpdateDTO).get();
    }
}
