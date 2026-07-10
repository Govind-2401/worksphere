package com.govind.worksphere.controller;

import com.govind.worksphere.dto.LoginRequestDTO;
import com.govind.worksphere.dto.LoginResponseDTO;
import com.govind.worksphere.dto.RegisterRequestDTO;
import com.govind.worksphere.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Authentication",
        description = "Register & Login APIs"
)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Register API
    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@Valid @RequestBody RegisterRequestDTO request) {

        return authService.register(request);
    }

    // Login API
    @Operation(summary = "Login and generate JWT token")
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDTO login(
            @Valid @RequestBody LoginRequestDTO request) {

        return authService.login(request);
    }
}