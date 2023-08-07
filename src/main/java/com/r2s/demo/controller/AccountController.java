package com.r2s.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.demo.DTO.AccountRequestDTO;
import com.r2s.demo.service.AccountService;

@RestController
@RequestMapping(path = "accounts")
public class AccountController {
	@Autowired
	@Qualifier(value = "AccountServiceImpl")
	private AccountService accountService;

	@PostMapping
	public ResponseEntity<?> createAccount(@RequestBody AccountRequestDTO accountRequestDTO) {
		return this.accountService.createAccount(accountRequestDTO);
	}
}
