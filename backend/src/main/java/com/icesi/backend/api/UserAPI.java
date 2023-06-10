package com.icesi.backend.api;

import com.icesi.backend.DTO.UserCreateDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/users")
public interface UserAPI {

    @GetMapping("/{userId}")
    UserCreateDTO getUser(@PathVariable UUID userId);

    @PostMapping()
    UserCreateDTO createUser(@RequestBody UserCreateDTO userDTO);

    @GetMapping
    List<UserCreateDTO> getUsers();

}
