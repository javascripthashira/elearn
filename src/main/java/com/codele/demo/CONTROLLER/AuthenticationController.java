package com.codele.demo.CONTROLLER;

import com.codele.demo.DTO.JWTAuthenticationResponse;
import com.codele.demo.DTO.SignInRequest;
import com.codele.demo.DTO.SignUpRequest;
import com.codele.demo.DTO.UserDTO;
import com.codele.demo.ENTITY.User;
import com.codele.demo.REPOSITORY.UserRepository;
import com.codele.demo.SERVICE.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<User> SignUp(@RequestBody SignUpRequest signUpRequest ){
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthenticationResponse> SignIn(@RequestBody SignInRequest signInRequest ){
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<JWTAuthenticationResponse> RefreshToken(@RequestBody RefreshToken refreshToken){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(new UserDTO(user));
    }

}
