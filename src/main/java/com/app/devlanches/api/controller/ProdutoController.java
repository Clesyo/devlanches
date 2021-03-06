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

import com.app.devlanches.api.impl.IProdutoService;
import com.app.devlanches.api.models.Produto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/produto", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {

	@Autowired
	private IProdutoService iProdutoService;

	@GetMapping
	@ApiOperation("Lista todos os Produtos")
	public List<Produto> getAll() {
		return iProdutoService.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation("Busca um produto pelo seu código ID")
	@ApiResponse(code = 404, message = "Produto não encontrado")
	public Produto findById(@PathVariable Long id) {
		return iProdutoService.findById(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Salve um Produto")
	@ApiResponses({ @ApiResponse(code = 201, message = "Produto salvo com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public Produto save(@RequestBody @Valid Produto produto) {
		return iProdutoService.save(produto);

	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Altera um Produto")
	@ApiResponses({ @ApiResponse(code = 204, message = "Produto alterado com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação"),
			@ApiResponse(code = 404, message = "Produto não encontrado") })
	public Produto update(@PathVariable Long id, @RequestBody @Valid Produto produto) {
		return iProdutoService.update(id, produto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Deleta um Cliente")
	@ApiResponses({ @ApiResponse(code = 204, message = "Produto deletado com sucesso"),
			@ApiResponse(code = 404, message = "Produto não encontrado") })
	public void delete(@PathVariable Long id) {
		iProdutoService.delete(id);
	}
}
