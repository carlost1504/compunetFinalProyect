package com.icesi.backend.errorConstants;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum OrderStatus {
    STATUS_01("CREATED"), STATUS_02("COMPLETED"), STATUS_03("SENT");

    private final String message;

}
