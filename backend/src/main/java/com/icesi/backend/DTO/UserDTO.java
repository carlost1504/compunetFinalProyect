package com.icesi.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private UUID userId;

    private String email;

    private String phoneNumber;

    private String address;

    private RoleDTO role;

}
