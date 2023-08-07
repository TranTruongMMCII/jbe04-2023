package com.r2s.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.r2s.demo.DTO.AuthenticationRequestDTO;
import com.r2s.demo.DTO.AuthenticationResponseDTO;
import com.r2s.demo.constants.ResponseCode;
import com.r2s.demo.service.AuthenticationService;
import com.r2s.demo.utils.JwtUtils;

@Service(value = "AuthenticationServiceImpl")
public class AuthenticationServiceImpl extends BaseServiceImpl implements AuthenticationService {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public ResponseEntity<?> login(AuthenticationRequestDTO authenticationRequestDTO) {
		if (ObjectUtils.isEmpty(authenticationRequestDTO) || ObjectUtils.isEmpty(authenticationRequestDTO.getPassword())
				|| ObjectUtils.isEmpty(authenticationRequestDTO.getUsername()))
			return error(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMessage());

		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequestDTO.getUsername(), authenticationRequestDTO.getPassword()));

			String token = JwtUtils.generateToken(authenticationRequestDTO.getUsername());
			AuthenticationResponseDTO authResponse = new AuthenticationResponseDTO(token, "Đăng nhập thành công");
			return success(authResponse);
		} catch (Exception e) {
			return error(ResponseCode.INCORRECT_AUTHEN.getCode(), ResponseCode.INCORRECT_AUTHEN.getMessage());
		}
	}

}
