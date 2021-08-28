package com.app.devlanches.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.devlanches.api.models.Pedido;
import com.app.devlanches.api.models.dto.ItemPedidoDetalhadoDTO;
import com.app.devlanches.api.models.dto.PedidoDTO;
import com.app.devlanches.api.models.dto.PedidoDetalhadoDTO;
import com.app.devlanches.api.service.PedidoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/pedido", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PedidoController {

	private final PedidoService pedidoService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PedidoDetalhadoDTO save(@RequestBody PedidoDTO pedido) {
		return convertPedido(pedidoService.save(pedido));
	}

	private PedidoDetalhadoDTO convertPedido(Pedido pedido) {

		List<ItemPedidoDetalhadoDTO> items = pedido.getItemPedidos().stream().map(item -> {
			return ItemPedidoDetalhadoDTO.builder().quantidade(item.getQuantidade())
					.produto(item.getProduto().getNome()).build();
		}).collect(Collectors.toList());

		return PedidoDetalhadoDTO.builder().cliente(pedido.getCliente().getNome()).pedido(pedido.getId())
				.total(pedido.getTotal()).itens(items).build();
	}
}
