package com.app.devlanches.api.controller;

import java.util.List;

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

import com.app.devlanches.api.models.Produto;
import com.app.devlanches.api.service.ProdutoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/produto", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProdutoController {

	private final ProdutoService produtoService;
	
	@GetMapping
	public List<Produto> getAll() {
		return produtoService.getAll();
	}
	
	@GetMapping("/{id}")
	public Produto getProdutoById(@PathVariable Long id) {
		return produtoService.getProdutoById(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Produto save(@RequestBody Produto produto) {
		return produtoService.save(produto);
				
	}
	
	@PutMapping("/{id}")
	public Produto update(@PathVariable Long id, @RequestBody Produto produto) {
		return produtoService.update(id, produto);
	}
	
	@DeleteMapping("/{di}")
	public void delete(@PathVariable Long id) {
		
	}
}
