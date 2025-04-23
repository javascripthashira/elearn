package com.codele.demo.SERVICE.IMPL;

import com.codele.demo.CONTROLLER.RefreshToken;
import com.codele.demo.DTO.JWTAuthenticationResponse;
import com.codele.demo.DTO.SignInRequest;
import com.codele.demo.DTO.SignUpRequest;
import com.codele.demo.ENTITY.Role;
import com.codele.demo.ENTITY.User;
import com.codele.demo.REPOSITORY.UserRepository;
import com.codele.demo.SERVICE.AuthenticationService;
import com.codele.demo.SERVICE.JWTservice;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class Authenticationimpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTservice jwTservice;
    public User  signup(SignUpRequest signUpRequest){
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstname(signUpRequest.getFirstname());
        user.setSecondname(signUpRequest.getSecondname());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setDepartment(signUpRequest.getDepartment());
        user.setRole(signUpRequest.getRole());
        return userRepository.save(user);



    }
    public JWTAuthenticationResponse signin(SignInRequest signInRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword()));


        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(()->new IllegalArgumentException("INVALID EMAIL OR PASSWORD"));
        var token = jwTservice.generatetoken(user);
        var refreshToken = jwTservice.generateRefreshtoken(new HashMap<>(), user);
        JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();
        jwtAuthenticationResponse.setToken(token);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;


    }
    public JWTAuthenticationResponse refreshToken(RefreshToken refreshToken){

        String userEmail = jwTservice.extractUserName(refreshToken.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if(jwTservice.isTokenValid(refreshToken.getToken(),user)){
            var jwt = jwTservice.generatetoken(user);
            JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshToken.getToken());
            return jwtAuthenticationResponse;

        }
        return null;
    }


}
