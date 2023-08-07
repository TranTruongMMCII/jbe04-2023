package com.r2s.demo.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.r2s.demo.DTO.UserRequestDTO;
import com.r2s.demo.DTO.UserResponseDTO;
import com.r2s.demo.constants.ResponseCode;
import com.r2s.demo.entity.User;
import com.r2s.demo.exceptions.InvalidValueException;
import com.r2s.demo.exceptions.NoParamException;
import com.r2s.demo.repository.UserRepository;
import com.r2s.demo.service.UserService;
import com.r2s.demo.utils.Validation;

@Service(value = "UserServiceImpl")
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public ResponseEntity<?> save(UserRequestDTO userRequestDTO) {
		try {
			User savedUser = Validation.validateSaveUser(userRequestDTO);
			savedUser.setPassword(this.passwordEncoder.encode(savedUser.getPassword()));

			return success(this.userRepository.save(savedUser));
		} catch (InvalidValueException e) {
			return error(ResponseCode.INVALID_VALUE.getCode(), ResponseCode.INVALID_VALUE.getMessage());
		} catch (NoParamException e) {
			return error(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMessage());
		} catch (ParseException e) {
			return error(ResponseCode.INVALID_VALUE.getCode(), ResponseCode.INVALID_VALUE.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> getAll(int type) {
//		return this.userRepository.findAll().stream().map(UserResponseDTO::new).collect(Collectors.toList());
		if (type != 0 && type != 1 && type != 2) {
			return error(ResponseCode.INVALID_VALUE.getCode(), ResponseCode.INVALID_VALUE.getMessage());
		}
		List<User> users;
		if (type == 0) {
			users = this.userRepository.findAll();
		} else if (type == 1) {
			users = this.userRepository.findByIsDeleted(true);
		} else {
			users = this.userRepository.findByIsDeleted(false);
		}

		List<UserResponseDTO> responses = new ArrayList<>();
		for (User user : users) {
			responses.add(new UserResponseDTO(user));
		}
		return success(responses);
	}

	@Override
	public User getById(long id) {
		return this.userRepository.findById(id).orElse(null);
	}

}
