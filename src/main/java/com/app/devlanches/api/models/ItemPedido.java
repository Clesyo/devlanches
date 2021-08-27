package com.app.devlanches.api.models;


import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produtos")
public class ItemPedido {

	private Pedido pedido;
	private Produto produto;
}
