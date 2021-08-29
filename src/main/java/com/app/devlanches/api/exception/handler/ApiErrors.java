package com.app.devlanches.api.exception.handler;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrors {

	@Getter
	private List<String> errors;
	
	public ApiErrors(String message) {
		// TODO Auto-generated constructor stub
		this.errors = Arrays.asList(message);
	}
}
