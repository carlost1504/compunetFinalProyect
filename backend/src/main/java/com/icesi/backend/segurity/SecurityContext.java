

package com.icesi.backend.segurity;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@Setter
@EqualsAndHashCode
@ToString
public class SecurityContext implements Serializable {

    private static final long serialVersionUID = 4659821160803661194L;

    private UUID userId;
    private UUID roleId;
    private String token;

    public UUID getUserId() {
        return Optional.ofNullable(userId).orElseThrow();
    }
    
    public UUID getRoleId() {
        return Optional.ofNullable(roleId).orElseThrow();
    }
    public String getToken() {
        return token;
    }

}
