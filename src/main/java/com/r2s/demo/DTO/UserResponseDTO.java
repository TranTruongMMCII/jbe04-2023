package com.r2s.demo.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.ObjectUtils;

import com.r2s.demo.entity.Account;
import com.r2s.demo.entity.Identification;
import com.r2s.demo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
	private Long id;
	private String name;
	private Integer age;
	private Date expriredDate;
	private List<AccountResponseDTO> accounts = new ArrayList<>();

	public UserResponseDTO(User user) {
		this.id = user.getId();
		this.age = user.getAge();
		this.name = user.getName();

		if (user.getIdentification() != null) {
			Identification identification = user.getIdentification();
			this.expriredDate = identification.getExpriredDate();
		}

		if (!ObjectUtils.isEmpty(user.getAccounts())) {
			for (Account account : user.getAccounts()) {
				this.accounts.add(new AccountResponseDTO(account));
			}
		}
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class AccountResponseDTO {
		private Long id;
		private Double balance;

		public AccountResponseDTO(Account account) {
			this.id = account.getId();
			this.balance = account.getBalance();
		}
	}
}
