package com.r2s.demo.service;

import org.springframework.http.ResponseEntity;

import com.r2s.demo.DTO.AccountRequestDTO;

public interface AccountService {
	ResponseEntity<?> createAccount(AccountRequestDTO accountRequestDTO);
}
