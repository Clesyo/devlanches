package com.app.devlanches.api.controller.mock;

import java.util.Optional;

import com.app.devlanches.api.models.Cliente;

public class ClienteMock {

	public static Optional<Cliente> getData() {
		Optional<Cliente> cliente = Optional.ofNullable(new Cliente("Clesyo", "24/04/1995", "(99) 99999-0000", "teste@email.com"));
		cliente.get().setId(1L);
		return cliente;
	}
}
