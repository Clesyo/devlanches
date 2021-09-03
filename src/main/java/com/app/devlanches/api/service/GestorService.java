package com.app.devlanches.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.devlanches.api.exception.ApiException;
import com.app.devlanches.api.exception.EntityNotExist;
import com.app.devlanches.api.impl.IGestorService;
import com.app.devlanches.api.models.Gestor;
import com.app.devlanches.api.repository.GestorRepository;

@Service
public class GestorService implements IGestorService {

	@Autowired
	private GestorRepository gestorRepository;

	@Override
	public Gestor save(Gestor gestor) {

		if (verificaQuantidadeGestor(findAll())) {
			return gestorRepository.save(gestor);
		}
		return null;
	}

	private boolean verificaQuantidadeGestor(List<Gestor> lista) {

		int limite = lista.size();

		if (limite >= 1) {
			throw new ApiException("Registor de Gestor já atigu o limite.");
		}

		return true;
	}

	@Override
	public List<Gestor> findAll() {
		return gestorRepository.findAll();
	}

	@Override
	public Gestor findByEmail(String emial) {
		return gestorRepository.findByEmail(emial).orElseThrow(() -> new EntityNotExist("Gestor não encontrado."));
	}
}
