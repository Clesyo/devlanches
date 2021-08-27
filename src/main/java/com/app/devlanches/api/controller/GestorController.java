package com.app.devlanches.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.devlanches.api.models.Gestor;
import com.app.devlanches.api.service.GestorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/gestor", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GestorController {

	
	private final GestorService service;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Gestor salvar(@RequestBody Gestor gestor) {
		return service.salvar(gestor);
	}
}
