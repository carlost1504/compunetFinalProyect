package com.icesi.backend.api;


import com.icesi.backend.DTO.LoginDTO;
import com.icesi.backend.DTO.TokenDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
public interface LoginAPI {

    @PostMapping
    TokenDTO login(@RequestBody LoginDTO loginDTO);

}
