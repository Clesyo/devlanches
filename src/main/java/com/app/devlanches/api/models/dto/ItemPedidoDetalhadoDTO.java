package com.app.devlanches.api.models.dto;

public class ItemPedidoDetalhadoDTO {

	private Integer quantidade;
	private String produto;

	public ItemPedidoDetalhadoDTO() {
	}

	public ItemPedidoDetalhadoDTO(Integer quantidade, String produto) {
		super();
		this.quantidade = quantidade;
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

}
