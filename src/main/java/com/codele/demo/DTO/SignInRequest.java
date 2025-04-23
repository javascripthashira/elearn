package com.codele.demo.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
