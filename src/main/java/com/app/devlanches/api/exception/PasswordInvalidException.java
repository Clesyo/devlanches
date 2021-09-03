package com.app.devlanches.api.exception;

public class PasswordInvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PasswordInvalidException() {
		super("Invalid password");
	}

}
