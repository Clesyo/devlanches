package com.app.devlanches.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.devlanches.api.enums.StatusPedido;
import com.app.devlanches.api.models.Pedido;
import com.app.devlanches.api.models.dto.PedidoDTO;
import com.app.devlanches.api.models.dto.PedidoDetalhadoDTO;
import com.app.devlanches.api.models.dto.StatusPedidoDTO;
import com.app.devlanches.api.service.PedidoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/pedido", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController {

	@Autowired
	private  PedidoService pedidoService;
	
	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@GetMapping
	@ApiOperation("Lista todos os pedidos")
	public List<PedidoDetalhadoDTO> getAll() {
		List<Pedido> pedidos = pedidoService.findAll();
		return pedidos.stream().map(pedido -> {
			return PedidoDetalhadoDTO.convertPedido(pedido);
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/cliente/{id}")
	@ApiOperation("Lista todos pedidos de um cliente")
	public List<PedidoDetalhadoDTO> getPedidoByIdCliente(@PathVariable @ApiParam("ID do cliente") Long id) {
		List<Pedido> pedidos = pedidoService.getPedidoByIdCliente(id);
		
		return pedidos.stream().map(pedido -> {
			return PedidoDetalhadoDTO.convertPedido(pedido);
		}).collect(Collectors.toList());
	}
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Cria um novo pedido")
	@ApiResponses({ @ApiResponse(code = 201, message = "Pedido criado com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public PedidoDetalhadoDTO save(@RequestBody @Valid PedidoDTO pedido) {
		return PedidoDetalhadoDTO.convertPedido(pedidoService.save(pedido));
	}

	@PatchMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Altera o status de um pedido")
	@ApiResponses({ @ApiResponse(code = 204, message = "Status de pedido alterado com sucesso"),
			@ApiResponse(code = 404, message = "Pedido não encontrado"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public void updateStatus(@PathVariable @ApiParam("ID do pedido") Long id, @RequestBody StatusPedidoDTO status) {
		pedidoService.mudaStatuPedido(id, StatusPedido.valueOf(status.getStatus().toUpperCase()));
	}
	
	@PatchMapping("/{id}/cancelar")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Muda o status de um pedido para cancelado")
	@ApiResponses({ @ApiResponse(code = 204, message = "Pedido alterado com sucesso"),
		@ApiResponse(code = 404, message = "Pedido não encontrado")})
	public void updateStatus(@PathVariable @ApiParam("ID do pedido") Long id) {
		pedidoService.cancelaPedido(id);
	}

}
