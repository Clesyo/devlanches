package com.app.devlanches.api.models.dto;

public class ItemPedidoDTO {

	private Integer quantidade;
	private Long produto;

	public ItemPedidoDTO() {
		// TODO Auto-generated constructor stub
	}

	public ItemPedidoDTO(Integer quantidade, Long produto) {
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

	public Long getProduto() {
		return produto;
	}

	public void setProduto(Long produto) {
		this.produto = produto;
	}

}
