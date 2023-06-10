package com.icesi.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class UserCreateDTO {
    private String email;
    private String phoneNumber;
    private String password;
}
