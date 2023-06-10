package com.icesi.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDTO {

    private String token;

    private String role;

    private String userId;

}
