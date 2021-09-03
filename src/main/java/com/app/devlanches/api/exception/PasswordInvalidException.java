package com.app.devlanches.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PasswordInvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PasswordInvalidException() {
		super("Invalid password");
	}

}
