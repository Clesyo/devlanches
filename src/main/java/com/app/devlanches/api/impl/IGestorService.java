package com.app.devlanches.api.impl;

import java.util.List;

import com.app.devlanches.api.models.Gestor;

public interface IGestorService {

	Gestor save(Gestor gestor);
	List<Gestor> findAll();
	Gestor findByEmail(String email);
}
