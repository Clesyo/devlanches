package com.app.devlanches.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.devlanches.api.exception.EntityNotExist;
import com.app.devlanches.api.impl.IProdutoService;
import com.app.devlanches.api.models.Produto;
import com.app.devlanches.api.repository.ProdutoRepository;

@Service
public class ProdutoService implements IProdutoService{

	@Autowired
	private  ProdutoRepository produtoRepository;

	@Override
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	@Override
	public Produto findById(Long id) {
		return produtoRepository.findById(id).orElseThrow(() -> new EntityNotExist("Produto não encontrado"));
	}

	@Override
	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Override
	public Produto update(Long id, Produto produto) {
		return produtoRepository.findById(id).map(p -> {
			produto.setId(p.getId());
			return produtoRepository.save(produto);
		})
				.orElseThrow(() -> new EntityNotExist("Produto não encontrado"));

	}

	@Override
	public void delete(Long id) {
		Produto produto = produtoRepository.findById(id).map(p -> {
			 return p;
		}).orElseThrow(() -> new EntityNotExist("Produto não encontrado"));

		produtoRepository.delete(produto);
	}

}
