package com.example.hr.dto;

public class RestErrorMessage {
	private String message;

	public RestErrorMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
