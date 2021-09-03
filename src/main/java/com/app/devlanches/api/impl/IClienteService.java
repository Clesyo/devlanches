package com.app.devlanches.api.impl;

import java.util.List;

import com.app.devlanches.api.models.Cliente;

public interface IClienteService {

	List<Cliente> findAll();
	Cliente findById(Long id);
	Cliente findByEmail(String email);
	Cliente save(Cliente cliente);
	Cliente update(Long id, Cliente cliente);
	void delete(Long id);
}
