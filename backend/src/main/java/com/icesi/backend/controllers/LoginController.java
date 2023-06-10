package com.icesi.backend.controllers;

import com.icesi.backend.DTO.LoginDTO;
import com.icesi.backend.validation.EmailValidator;
import com.icesi.backend.validation.PhoneNumberValidator;
import com.icesi.backend.DTO.TokenDTO;
import com.icesi.backend.api.LoginAPI;
import com.icesi.backend.error.exception.EShopError;
import com.icesi.backend.error.exception.EShopException;
import com.icesi.backend.errorConstants.BackendApplicationErrors;
import com.icesi.backend.service.LoginServiceInterface;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class LoginController implements LoginAPI {

    private LoginServiceInterface loginService;


    @CrossOrigin(origins = "*")
    @Override
    public TokenDTO login(LoginDTO loginDTO) {

        String s = loginDTO.getUsername();
        EmailValidator emailValidator = new EmailValidator();
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
        if (emailValidator.isValid(s, null))
            return loginService.loginByEmail(loginDTO);
        if (phoneNumberValidator.isValid(s, null))
            return loginService.loginByPhoneNumber(loginDTO);

        throw new RuntimeException(BackendApplicationErrors.CODE_U_02.getMessage());

    }

}