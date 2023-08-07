package com.r2s.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.demo.DTO.AuthenticationRequestDTO;
import com.r2s.demo.service.AuthenticationService;

@RequestMapping("auth")
@RestController
public class AuthenticationController {
	@Autowired
	@Qualifier(value = "AuthenticationServiceImpl")
	private AuthenticationService authenticationService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
		return this.authenticationService.login(authenticationRequestDTO);
	}
}
