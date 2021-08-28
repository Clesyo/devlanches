package com.app.devlanches.api.models.dto;

import java.util.List;

import lombok.Data;

@Data
public class PedidoDTO {

	private Long cliente;
	
	private List<ItemPedidoDTO> itens;
	
	
}
