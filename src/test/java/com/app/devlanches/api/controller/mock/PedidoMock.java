package com.app.devlanches.api.controller.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.app.devlanches.api.enums.StatusPedido;
import com.app.devlanches.api.models.Cliente;
import com.app.devlanches.api.models.Pedido;
import com.app.devlanches.api.models.dto.ItemPedidoDTO;
import com.app.devlanches.api.models.dto.PedidoDTO;

public class PedidoMock {

	public static PedidoDTO	 getDataDTO() {
		List<ItemPedidoDTO> list = new ArrayList<ItemPedidoDTO>();
		list.add(ItemPedidoMock.getData());
		list.add(ItemPedidoMock.getData());
		return new PedidoDTO(10L, list);
	}
	public static PedidoDTO	 getDataWithoutItems() {
		List<ItemPedidoDTO> list = new ArrayList<ItemPedidoDTO>();
		return new PedidoDTO(10L, list);
	}
	
	public static Optional<Pedido> getData() {
		Pedido pedido = new Pedido(StatusPedido.FINALIZADO, new BigDecimal(5), new Cliente());
		pedido.setId(1L);
		return Optional.of(pedido);
	}
}
