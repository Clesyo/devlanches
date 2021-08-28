package com.app.devlanches.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.app.devlanches.api.exception.ApiException;
import com.app.devlanches.api.models.Produto;
import com.app.devlanches.api.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

	private final ProdutoRepository produtoRepository;

	public List<Produto> getAll() {
		return produtoRepository.findAll();
	}

	public Produto getProdutoById(Long id) {
		return findOrFail(id);
	}

	public Produto save(Produto produto) {
		String classificacao = produto.getClassificacao().toUpperCase();
		produto.setClassificacao(classificacao);
		return produtoRepository.save(produto);
	}

	public Produto update(Long id, Produto produto) {
		Produto p = findOrFail(id);

		BeanUtils.copyProperties(produto, p, "id");
		return produtoRepository.save(p);
	}

	public void delete(Long id) {
		Produto produto = findOrFail(id);
		produtoRepository.delete(produto);
	}

	private Produto findOrFail(Long id) {
		return produtoRepository.findById(id).orElseThrow(() -> new ApiException("Produto não encontrado"));
	}
}