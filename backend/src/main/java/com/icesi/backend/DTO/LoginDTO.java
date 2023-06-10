package com.icesi.backend.DTO;

import com.icesi.backend.validation.CustomAnnotations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotNull(message = "The username cannot be null")
    @CustomAnnotations.ValidUsername
    private String username;

    @NotNull(message = "The password cannot be null")
    @CustomAnnotations.ValidPassword
    private String password;

}
