package com.app.devlanches.api.models.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDetalhadoDTO {

	private Long pedido;
	private String cliente;
	private BigDecimal total;
	private List<ItemPedidoDetalhadoDTO> itens;
}
