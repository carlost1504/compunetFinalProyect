package com.icesi.backend.UnitTest;

import com.icesi.backend.service.impl.Token_Parser;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TokenParserTest {

    private static final String SECRET_KEY = "longenoguhkeytotestthisimplementationsomebytesmore";

    @Test
    public void testCreateJWT_Success() {
        // Arrange
        String id = "1";
        String issuer = "example.com";
        String subject = "user@example.com";
        long ttlMillis = 3600000; // 1 hour

        Map<String, String> claims = new HashMap<>();
        claims.put("name", "John Doe");

        // Act
        String jwt = Token_Parser.createJWT(id, issuer, subject, claims, ttlMillis);

        // Assert
        Assertions.assertNotNull(jwt);

        Claims decodedClaims = Token_Parser.decodeJWT(jwt);
        Assertions.assertEquals(id, decodedClaims.getId());
        Assertions.assertEquals(issuer, decodedClaims.getIssuer());
        Assertions.assertEquals(subject, decodedClaims.getSubject());
        Assertions.assertEquals(claims.get("name"), decodedClaims.get("name"));
    }

    @Test
    public void testDecodeJWT_Success() {
        // Arrange
        String id = "1";
        String issuer = "example.com";
        String subject = "user@example.com";
        long ttlMillis = 3600000; // 1 hour

        Map<String, String> claims = new HashMap<>();
        claims.put("name", "John Doe");

        String jwt = Token_Parser.createJWT(id, issuer, subject, claims, ttlMillis);

        // Act
        Claims decodedClaims = Token_Parser.decodeJWT(jwt);

        // Assert
        Assertions.assertEquals(id, decodedClaims.getId());
        Assertions.assertEquals(issuer, decodedClaims.getIssuer());
        Assertions.assertEquals(subject, decodedClaims.getSubject());
        Assertions.assertEquals(claims.get("name"), decodedClaims.get("name"));
    }
}
