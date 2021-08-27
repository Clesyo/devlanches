package com.app.devlanches.api.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedidos")
public class Pedido {

	private Long id;
	private BigDecimal total;
}
