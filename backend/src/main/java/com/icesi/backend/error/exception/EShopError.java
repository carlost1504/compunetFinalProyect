package com.icesi.backend.error.exception;

import com.icesi.backend.errorConstants.BackendApplicationErrors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EShopError {

    private BackendApplicationErrors code;
    private String message;
}
