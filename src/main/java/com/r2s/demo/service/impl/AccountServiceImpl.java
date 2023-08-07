package com.r2s.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.r2s.demo.DTO.AccountRequestDTO;
import com.r2s.demo.DTO.AccountResponseDTO;
import com.r2s.demo.constants.ResponseCode;
import com.r2s.demo.entity.Account;
import com.r2s.demo.entity.User;
import com.r2s.demo.exceptions.InvalidValueException;
import com.r2s.demo.exceptions.NoParamException;
import com.r2s.demo.repository.AccountRepository;
import com.r2s.demo.repository.UserRepository;
import com.r2s.demo.service.AccountService;
import com.r2s.demo.utils.Validation;

@Service(value = "AccountServiceImpl")
public class AccountServiceImpl extends BaseServiceImpl implements AccountService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public ResponseEntity<?> createAccount(AccountRequestDTO accountRequestDTO) {
		try {
			if (Validation.validateSaveAccount(accountRequestDTO)) {
				Long userId = accountRequestDTO.getUserId();
				User foundUser = this.userRepository.findById(userId).orElse(null);
				if (ObjectUtils.isEmpty(foundUser)) {
					return error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
				}

				/**
				 * co 2 cach luu<br>
				 * 1. luu theo user <br>
				 * 2. luu theo account
				 */
				/*
				 * cach 1
				 */
				Account account = new Account();
				account.setBalance(accountRequestDTO.getBalance());
				account.setUser(foundUser);

//				foundUser.getAccounts().add(account);
//				this.userRepository.save(foundUser);

				/*
				 * cach 2
				 */
				account = this.accountRepository.save(account);

				return success(new AccountResponseDTO(account));
			} else {
				return error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());
			}
		} catch (NoParamException e) {
			return error(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMessage());
		} catch (InvalidValueException e) {
			return error(ResponseCode.INVALID_VALUE.getCode(), ResponseCode.INVALID_VALUE.getMessage());
		}
	}

}
