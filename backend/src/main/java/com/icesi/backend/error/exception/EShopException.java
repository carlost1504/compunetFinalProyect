package com.icesi.backend.error.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class EShopException extends RuntimeException{

    private HttpStatus status;
    private EShopError error;

}
