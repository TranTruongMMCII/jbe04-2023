package com.r2s.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.util.ObjectUtils;

import com.r2s.demo.DTO.AccountRequestDTO;
import com.r2s.demo.DTO.UserRequestDTO;
import com.r2s.demo.entity.Identification;
import com.r2s.demo.entity.Role;
import com.r2s.demo.entity.User;
import com.r2s.demo.exceptions.InvalidValueException;
import com.r2s.demo.exceptions.NoParamException;

public class Validation {
	public static User validateSaveUser(UserRequestDTO userRequestDTO)
			throws InvalidValueException, NoParamException, ParseException {
		if (ObjectUtils.isEmpty(userRequestDTO))
			throw new NoParamException();
		if (ObjectUtils.isEmpty(userRequestDTO.getAge()) || ObjectUtils.isEmpty(userRequestDTO.getName()))
			throw new NoParamException();
		if (ObjectUtils.isEmpty(userRequestDTO.getUsername()) || ObjectUtils.isEmpty(userRequestDTO.getPassword()))
			throw new NoParamException();
		if (userRequestDTO.getAge() < 0 || userRequestDTO.getAge() > 120)
			throw new InvalidValueException();
		if (userRequestDTO.getExpriredDate() == null) {
			throw new NoParamException();
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		simpleDateFormat.setLenient(false);
		Date date = simpleDateFormat.parse(userRequestDTO.getExpriredDate());
		if (!date.after(new Date())) {
			throw new InvalidValueException();
		}

		User parsedUser = new User();
		parsedUser.setAge(userRequestDTO.getAge());
		parsedUser.setName(userRequestDTO.getName());
		parsedUser.setUsername(userRequestDTO.getUsername());
		parsedUser.setPassword(userRequestDTO.getPassword());
		Set<Role> roles = new HashSet<>();
		Role role = new Role();
		role.setId(2L);
		roles.add(role);
		parsedUser.setRoles(roles);
		Identification identification = new Identification();
		identification.setExpriredDate(simpleDateFormat.parse(userRequestDTO.getExpriredDate()));
		parsedUser.setIdentification(identification);
		return parsedUser;
	}

	public static boolean validateSaveAccount(AccountRequestDTO accountRequestDTO)
			throws NoParamException, InvalidValueException {
		if (ObjectUtils.isEmpty(accountRequestDTO) || ObjectUtils.isEmpty(accountRequestDTO.getBalance())
				|| ObjectUtils.isEmpty(accountRequestDTO.getUserId()))
			throw new NoParamException();
		if (accountRequestDTO.getBalance() < 0)
			throw new InvalidValueException();

		return true;
	}
}
