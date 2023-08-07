package com.r2s.demo.service;

import org.springframework.http.ResponseEntity;

import com.r2s.demo.DTO.UserRequestDTO;
import com.r2s.demo.entity.User;

public interface UserService {
	ResponseEntity<?> save(UserRequestDTO user);

	ResponseEntity<?> getAll(int type);

	User getById(long id);
}
