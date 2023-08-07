package com.r2s.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
	private String name;
	private Integer age;
	private String expriredDate; // dd/MM/yyyy
	private String username;
	private String password;
}
