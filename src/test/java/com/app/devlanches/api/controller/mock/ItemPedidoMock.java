package com.app.devlanches.api.controller.mock;

import com.app.devlanches.api.models.dto.ItemPedidoDTO;

public class ItemPedidoMock {

	public static ItemPedidoDTO	getData() {
		return new ItemPedidoDTO(2, 1L);
	}
}
