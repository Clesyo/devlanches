package com.app.devlanches.api.controller.mock;

import com.app.devlanches.api.models.dto.CredencialDTO;

public class CredentialMock {

	public static CredencialDTO credential() {
		return new CredencialDTO("teste@gmail.com", "123");
	}
	
	public static CredencialDTO credentialWithoutPassword() {
		return new CredencialDTO("teste@gmail.com", "");
	}
}
