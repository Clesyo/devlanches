package com.app.devlanches.api.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.app.devlanches.api.enums.ClassificacaoProduto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "produtos")
public class Produto {

	public Produto() {
	}

	public Produto(String nome, BigDecimal valor, ClassificacaoProduto classificacao) {
		this.nome = nome;
		this.valor = valor;
		this.classificacao = classificacao;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@NotBlank(message = "Informe o nome do produto.")
	private String nome;
	@Column(nullable = false)
	@NotNull(message = "Não é possivel cadastrar produto sem preço.")
	private BigDecimal valor;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@ApiModelProperty("Classificação do produto ex: BEBIDA, COMIDA, SOBREMESA")
	private ClassificacaoProduto classificacao;

	@JsonIgnore
	@OneToMany(mappedBy = "produto")
	private List<ItemPedido> itemPedidos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public ClassificacaoProduto getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(ClassificacaoProduto classificacao) {
		this.classificacao = classificacao;
	}

	public List<ItemPedido> getItemPedidos() {
		return itemPedidos;
	}

	public void setItemPedidos(List<ItemPedido> itemPedidos) {
		this.itemPedidos = itemPedidos;
	}

}
