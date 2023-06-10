package com.icesi.backend.errorConstants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BackendApplicationErrors {

    CODE_I_01("Item type not found"),
    CODE_O_01("Order not found"),
    CODE_U_01("User not found"),
    CODE_01("Invalid argument"),
    CODE_L_01("Login failed"),
    CODE_L_02("Incorrect password"),
    CODE_L_03("Unauthorized access"),
    CODE_L_04("Role not specified"),
    CODE_I_02("Insufficient items available"),
    CODE_U_02("Either email or phone number must be provided"),
    CODE_U_03("Invalid email format"),
    CODE_U_04("Invalid phone number format");

    private final String message;
}
