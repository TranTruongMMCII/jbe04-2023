package com.r2s.demo.service;

import org.springframework.http.ResponseEntity;

import com.r2s.demo.DTO.AuthenticationRequestDTO;

public interface AuthenticationService {
	ResponseEntity<?> login(AuthenticationRequestDTO authenticationRequestDTO);
}
