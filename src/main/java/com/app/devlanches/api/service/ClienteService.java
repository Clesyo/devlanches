package com.app.devlanches.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.devlanches.api.exception.ApiException;
import com.app.devlanches.api.exception.EntityNotExist;
import com.app.devlanches.api.impl.IClienteService;
import com.app.devlanches.api.models.Cliente;
import com.app.devlanches.api.repository.ClienteRepository;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente save(Cliente cliente) {
		Cliente clienteByEmail = findByEmail(cliente.getEmail());

		if (clienteByEmail != null) {
			throw new ApiException("Email já está sendo usado.");
		}

		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente findById(Long id) {
		return findOrFail(id);
	}

	@Override
	public Cliente update(Long id, Cliente cliente) {
		Cliente c = findOrFail(id);
		BeanUtils.copyProperties(cliente, c, "id");
		return clienteRepository.save(c);
	}

	@Override
	public void delete(Long id) {
		Cliente cliente = findOrFail(id);
		clienteRepository.delete(cliente);
	}

	private Cliente findOrFail(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new EntityNotExist("Cliente não encontrado."));
	}

	@Override
	public Cliente findByEmail(String email) {
		return clienteRepository.findByEmail(email).orElseThrow(() -> new EntityNotExist("Cliente não encontrado"));
	}

}
