package com.app.devlanches.api.models.dto;

import java.util.List;

public class PedidoDTO {

	private Long cliente;

	private List<ItemPedidoDTO> itens;

	public PedidoDTO(Long cliente, List<ItemPedidoDTO> itens) {
		this.cliente = cliente;
		this.itens = itens;
	}

	public Long getCliente() {
		return cliente;
	}

	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedidoDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoDTO> itens) {
		this.itens = itens;
	}

}
