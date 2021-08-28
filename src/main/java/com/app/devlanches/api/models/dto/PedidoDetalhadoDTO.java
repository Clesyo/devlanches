package com.app.devlanches.api.models.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.app.devlanches.api.models.Pedido;

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
	private String status;
	private String cliente;
	private BigDecimal total;
	private List<ItemPedidoDetalhadoDTO> itens;
	
	public static PedidoDetalhadoDTO convertPedido(Pedido pedido) {
		List<ItemPedidoDetalhadoDTO> items = pedido.getItemPedidos().stream().map(item -> {
			return ItemPedidoDetalhadoDTO.builder().quantidade(item.getQuantidade())
					.produto(item.getProduto().getNome()).build();
		}).collect(Collectors.toList());

		return PedidoDetalhadoDTO.builder().cliente(pedido.getCliente().getNome()).pedido(pedido.getId())
				.total(pedido.getTotal()).itens(items).status(pedido.getStatus().name()).build();
	}
}
