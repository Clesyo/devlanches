package com.app.devlanches.api.models.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.app.devlanches.api.models.Pedido;

public class PedidoDetalhadoDTO {

	private Long pedido;
	private String status;
	private String cliente;
	private BigDecimal total;
	private List<ItemPedidoDetalhadoDTO> itens;

	public PedidoDetalhadoDTO() {
		// TODO Auto-generated constructor stub
	}

	public PedidoDetalhadoDTO(Long pedido, String status, String cliente, BigDecimal total,
			List<ItemPedidoDetalhadoDTO> itens) {
		super();
		this.pedido = pedido;
		this.status = status;
		this.cliente = cliente;
		this.total = total;
		this.itens = itens;
	}

	public static PedidoDetalhadoDTO convertPedido(Pedido pedido) {
		List<ItemPedidoDetalhadoDTO> items = pedido.getItemPedidos().stream().map(item -> {
			return new ItemPedidoDetalhadoDTO(item.getQuantidade(), item.getProduto().getNome());
		}).collect(Collectors.toList());

		return new PedidoDetalhadoDTO(pedido.getId(), pedido.getStatus().name(), pedido.getCliente().getNome(),
				pedido.getTotal(), items);
	}

	public Long getPedido() {
		return pedido;
	}

	public void setPedido(Long pedido) {
		this.pedido = pedido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<ItemPedidoDetalhadoDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoDetalhadoDTO> itens) {
		this.itens = itens;
	}

}
