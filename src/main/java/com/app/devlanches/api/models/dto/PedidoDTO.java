package com.app.devlanches.api.models.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class PedidoDTO {

	private Integer quantidade;
	private String status;
	private BigDecimal total;
	private Long cliente;
	
	private List<ItemPedidoDTO> itens;
	
	
}
