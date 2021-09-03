package com.app.devlanches.api.controller.mock;

import java.math.BigDecimal;

import com.app.devlanches.api.enums.ClassificacaoProduto;
import com.app.devlanches.api.models.Produto;

public class ProdutoMock {

	public static Produto getData() {
		
		Produto produto = new Produto("Produto teste", new BigDecimal(50), ClassificacaoProduto.COMIDA);
		produto.setId(1L);
		return produto;
	}
}
