package com.icesi.backend.error.exception;

import com.icesi.backend.error.EshopErrorCode;
import lombok.*;

@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ErrorDetail {
    private EshopErrorCode errorCode;
    private String errorMessage;

}
