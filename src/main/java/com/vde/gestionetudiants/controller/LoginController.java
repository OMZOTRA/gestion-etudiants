package com.vde.gestionetudiants.controller;

import com.vde.gestionetudiants.security.services.JwtService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    private final JwtService jwtService;

    public LoginController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("login")
    public String getToken(Authentication authentication) {
       String token = jwtService.generateToken(authentication);
       return token;
    }
}
