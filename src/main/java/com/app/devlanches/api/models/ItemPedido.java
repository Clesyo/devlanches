package com.app.devlanches.api.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	@Column(nullable = false)
	private Integer quantidade;
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	public ItemPedido() {
	}

	public ItemPedido(Long id, Pedido pedido, Integer quantidade, Produto produto) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.quantidade = quantidade;
		this.produto = produto;
	}

	public ItemPedido(Pedido pedido, Integer quantidade, Produto produto) {
		super();
		this.pedido = pedido;
		this.quantidade = quantidade;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	

	
	
	
	
}
