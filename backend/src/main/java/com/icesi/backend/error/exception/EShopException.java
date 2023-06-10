package com.icesi.backend.error.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;



@Getter
public class EShopException extends RuntimeException{
    private HttpStatus status;
    private final EShopError eShopError;

    public  EShopException(String message, EShopError eShopError){
        super(message);
        this.eShopError = eShopError;
    }

}
