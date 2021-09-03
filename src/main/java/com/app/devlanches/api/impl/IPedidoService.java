package com.app.devlanches.api.impl;

import java.util.List;

import com.app.devlanches.api.enums.StatusPedido;
import com.app.devlanches.api.models.Pedido;
import com.app.devlanches.api.models.dto.PedidoDTO;

public interface IPedidoService {

	List<Pedido> findAll();
	Pedido findById(Long id);
	Pedido save(PedidoDTO pedido);
	List<Pedido> findByIdCliente(Long id);
	void changeStatusPedido(Long id, StatusPedido statusPedido);
	void cancelPedido(Long id);
}
