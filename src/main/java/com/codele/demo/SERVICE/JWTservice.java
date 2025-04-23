package com.codele.demo.SERVICE;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JWTservice {
    String generatetoken(UserDetails userDetails);
    <T> T extractClaims(String token, Function<Claims, T> ClaimsResolvers);
    String extractUserName(String token);
    boolean isTokenExpired(String token);
    boolean isTokenValid(String token, UserDetails userDetails );

    String generateRefreshtoken(Map<String, Object> extraClaims, UserDetails userDetails);
}
