package com.app.devlanches.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.devlanches.api.models.Cliente;
import com.app.devlanches.api.service.ClienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/cliente" , produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClienteController {

	private final ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> findAll() {
		return clienteService.findAll();
	}
	
	@GetMapping("/{id}")
	public Cliente getClienteById(Long id) {
		return clienteService.getClienteById(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cliente save(@RequestBody @Valid Cliente cliente) {
		return clienteService.save(cliente);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Cliente update(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {
		return clienteService.update(id, cliente);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clienteService.delete(id);
	}
	
	
}
