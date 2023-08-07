package com.r2s.demo.constants;

public enum ResponseCode {
	OK(200, "Success"),
	NOT_FOUND(404, "Not found"),
	USER_NOT_FOUND(4040, "User not found"),
	COURSE_NOT_FOUND(4041, "Course not found"),
	NO_PARAM(6001, "No param"),
	INVALID_VALUE(6015, "Invalid value"),
	NO_CONTENT(2004, "No content"),
	INCORRECT_AUTHEN(2005, "Invalid username or password");
	
	private int code;
	private String message;

	private ResponseCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}
}
