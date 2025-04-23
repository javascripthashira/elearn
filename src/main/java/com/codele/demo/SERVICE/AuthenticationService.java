package com.codele.demo.SERVICE;

import com.codele.demo.CONTROLLER.RefreshToken;
import com.codele.demo.DTO.JWTAuthenticationResponse;
import com.codele.demo.DTO.SignInRequest;
import com.codele.demo.DTO.SignUpRequest;
import com.codele.demo.ENTITY.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);
    JWTAuthenticationResponse signin(SignInRequest signInRequest);
    JWTAuthenticationResponse refreshToken(RefreshToken refreshToken);
}
