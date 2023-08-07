package com.r2s.demo.DTO;

import org.springframework.util.ObjectUtils;

import com.r2s.demo.entity.Account;
import com.r2s.demo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {
	private Long id;
	private Double balance;
	private String userName;

	public AccountResponseDTO(Account account) {
		this.id = account.getId();
		this.balance = account.getBalance();

		if (!ObjectUtils.isEmpty(account.getUser())) {
			User user = account.getUser();
			this.userName = user.getName();
		}
	}
}
