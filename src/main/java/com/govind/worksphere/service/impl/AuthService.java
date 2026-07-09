package com.govind.worksphere.service;

import com.govind.worksphere.dto.LoginRequestDTO;
import com.govind.worksphere.dto.LoginResponseDTO;
import com.govind.worksphere.dto.RegisterRequestDTO;

public interface AuthService {

    String register(RegisterRequestDTO request);

    LoginResponseDTO login(LoginRequestDTO request);

}