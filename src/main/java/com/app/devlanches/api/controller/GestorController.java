package com.app.devlanches.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.devlanches.api.models.Gestor;
import com.app.devlanches.api.models.dto.GestorDTO;
import com.app.devlanches.api.service.GestorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/gestor", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GestorController {

	private final GestorService service;

	@GetMapping
	@ApiOperation("Busca todos os gestores")
	public List<GestorDTO> getAll() {
		return service.getAll().stream().map(gestor -> {
			return GestorDTO.convertToDto(gestor);
		}).collect(Collectors.toList());
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Salve um Gestor")
	@ApiResponses({ @ApiResponse(code = 201, message = "Gestor salvo com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public GestorDTO salvar(@RequestBody @Valid Gestor gestor) {
		return GestorDTO.convertToDto(service.salvar(gestor));
	}
}
