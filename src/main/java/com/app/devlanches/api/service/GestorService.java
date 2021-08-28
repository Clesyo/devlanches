package com.app.devlanches.api.service;

import org.springframework.stereotype.Service;

import com.app.devlanches.api.exception.ApiException;
import com.app.devlanches.api.exception.EntityNotExist;
import com.app.devlanches.api.models.Gestor;
import com.app.devlanches.api.repository.GestorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GestorService {

	private final GestorRepository gestorRepository;
	
	public Gestor salvar(Gestor gestor) {
		int limite = gestorRepository.findAll().size();
		
		if(limite < 1) {
			return gestorRepository.save(gestor);
		}
		throw new ApiException("Registor de Gestor já atigu o limite.");
	}
	
}
