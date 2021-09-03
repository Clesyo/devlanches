package com.app.devlanches.api.controller.mock;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.devlanches.api.models.Gestor;

public class GestorMock {

	public static Gestor allData() {
		Gestor gestor = new Gestor("Teste", "teste@gmail.com", new BCryptPasswordEncoder().encode("123"));
		gestor.setId(1L);
		return gestor;
	}
}
