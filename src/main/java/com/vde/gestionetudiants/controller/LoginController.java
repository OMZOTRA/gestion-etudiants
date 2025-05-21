package com.vde.gestionetudiants.controller;

import com.vde.gestionetudiants.security.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/check-roles")
    public ResponseEntity<?> checkUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("Utilisateur : " + authentication.getName());
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            System.out.println("Rôle : " + authority.getAuthority());
        }

        return ResponseEntity.ok("Check dans les logs");
    }
}
