package com.app.devlanches.api.impl;

import java.util.List;

import com.app.devlanches.api.models.Produto;

public interface IProdutoService {

	List<Produto> findAll();
	Produto findById(Long id);
	Produto save(Produto produto);
	Produto update(Long id, Produto produto);
	void delete(Long id);
}
