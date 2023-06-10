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
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
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

        throw new EShopException(HttpStatus.BAD_REQUEST, new EShopError(BackendApplicationErrors.CODE_L_01, BackendApplicationErrors.CODE_L_01.getMessage()));
    }

}