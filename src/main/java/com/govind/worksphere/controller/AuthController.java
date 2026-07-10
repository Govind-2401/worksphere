package com.govind.worksphere.controller;

import com.govind.worksphere.dto.LoginRequestDTO;
import com.govind.worksphere.dto.LoginResponseDTO;
import com.govind.worksphere.dto.RegisterRequestDTO;
import com.govind.worksphere.dto.common.ApiResponse;
import com.govind.worksphere.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
    public ResponseEntity<ApiResponse<Void>> register(
            @Valid @RequestBody RegisterRequestDTO request) {

        authService.register(request);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .message("User registered successfully.")
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Login API
    @Operation(summary = "Login and generate JWT token")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(
            @Valid @RequestBody LoginRequestDTO request) {

        LoginResponseDTO loginResponse = authService.login(request);

        ApiResponse<LoginResponseDTO> response =
                ApiResponse.<LoginResponseDTO>builder()
                        .success(true)
                        .message("Login successful.")
                        .data(loginResponse)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }
}