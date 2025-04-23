package com.codele.demo.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class JWTAuthenticationResponse {
    private String token;
    private String RefreshToken;
}
