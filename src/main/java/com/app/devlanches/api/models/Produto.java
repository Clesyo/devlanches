package com.app.devlanches.api.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produtos")
public class Produto {

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
	@NotBlank(message = "Informe uma classificação para o produto.")
	@ApiModelProperty("Classificação do produto ex: BEBIDA, COMIDA, SOBREMESA")
	private String classificacao;
	
	@JsonIgnore
	@OneToMany(mappedBy = "produto")
	private List<ItemPedido> itemPedidos;
}
