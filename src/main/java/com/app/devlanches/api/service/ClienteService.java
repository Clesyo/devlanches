package com.app.devlanches.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.app.devlanches.api.exception.ApiException;
import com.app.devlanches.api.models.Cliente;
import com.app.devlanches.api.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private final ClienteRepository clienteRepository;
	
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente getClienteById (Long id) {
		return findOrFail(id);
	}
	
	public Cliente update(Long id, Cliente cliente) {
		Cliente c = findOrFail(id);
		BeanUtils.copyProperties(cliente, c, "id");
		return clienteRepository.save(c);
	}
	
	public void delete(Long id) {
		Cliente cliente = findOrFail(id);
		clienteRepository.delete(cliente);
	}
	
	private Cliente findOrFail(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new ApiException("Cliente não encontrado."));
	}
}
