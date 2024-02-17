package com.project.SoutienScolaire.controller;

import com.project.SoutienScolaire.dto.AuthenticationRequest;
import com.project.SoutienScolaire.dto.AuthenticationResponse;
import com.project.SoutienScolaire.dto.RegisterRequest;
import com.project.SoutienScolaire.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/register/etudiant")
    public ResponseEntity<AuthenticationResponse> registerEtudiant(@RequestBody RegisterRequest request) {
        // Implement registration logic for etudiant
        return ResponseEntity.ok(service.registerEtudiant(request));
    }

    @PostMapping("/register/professeur")
    public ResponseEntity<AuthenticationResponse> registerProfesseur(@RequestBody RegisterRequest request) {
        // Implement registration logic for professeur
        return ResponseEntity.ok(service.registerProfesseur(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));

    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }

}