package com.app.devlanches.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/pedido", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PedidoController {

	private final PedidoService pedidoService;
	private String statusPedido = "";

	@GetMapping
	@ApiOperation("Lista todos os pedidos")
	public List<PedidoDetalhadoDTO> getAll() {
		List<Pedido> pedidos = pedidoService.findAll();
		return pedidos.stream().map(pedido -> {
			return convertPedido(pedido);
		}).collect(Collectors.toList());
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Cria um novo pedido")
	@ApiResponses({ @ApiResponse(code = 201, message = "Pedido criado com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public PedidoDetalhadoDTO save(@RequestBody @Valid PedidoDTO pedido) {
		return convertPedido(pedidoService.save(pedido));
	}

	private PedidoDetalhadoDTO convertPedido(Pedido pedido) {

		List<ItemPedidoDetalhadoDTO> items = pedido.getItemPedidos().stream().map(item -> {
			return ItemPedidoDetalhadoDTO.builder().quantidade(item.getQuantidade())
					.produto(item.getProduto().getNome()).build();
		}).collect(Collectors.toList());

		switch (pedido.getStatus()) {
		case 1:
			statusPedido = "PENDENTE";
			break;
		case 2:
			statusPedido = "ATENDIMENTO";
			break;
		case 3:
			statusPedido = "FINALIZADO";
			break;
		case 4:
			statusPedido = "CANCELADO";
			break;

		default:
			break;
		}

		return PedidoDetalhadoDTO.builder().cliente(pedido.getCliente().getNome()).pedido(pedido.getId())
				.total(pedido.getTotal()).itens(items).status(statusPedido).build();
	}
}
