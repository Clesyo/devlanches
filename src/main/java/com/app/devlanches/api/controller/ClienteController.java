package com.app.devlanches.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.app.devlanches.api.impl.IClienteService;
import com.app.devlanches.api.models.Cliente;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {

	@Autowired
	private IClienteService	 iClienteService;
	
	@GetMapping
	@ApiOperation("Lista todos os clientes")
	public List<Cliente> findAll() {
		return iClienteService.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation("Busca um cliente pelo seu código ID")
	@ApiResponse(code = 404, message = "Cliente não encontrado")
	public Cliente getClienteById(@PathVariable Long id) {
		return iClienteService.findById(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Salve um Cliente")
	@ApiResponses({ @ApiResponse(code = 201, message = "Cliente salvo com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public Cliente save(@RequestBody @Valid Cliente cliente) {
		return iClienteService.save(cliente);
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Altera um Cliente")
	@ApiResponses({ @ApiResponse(code = 201, message = "Cliente alterado com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação"),
			@ApiResponse(code = 404, message = "Cliente não encontrado") })
	public Cliente update(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {
		return iClienteService.update(id, cliente);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Deleta um Cliente")
	@ApiResponses({ @ApiResponse(code = 201, message = "Cliente deletado com sucesso"),
			@ApiResponse(code = 404, message = "Cliente não encontrado") })
	public void delete(@PathVariable Long id) {
		iClienteService.delete(id);
	}

}
