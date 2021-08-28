package com.app.devlanches.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.devlanches.api.exception.ApiException;
import com.app.devlanches.api.models.Cliente;
import com.app.devlanches.api.models.ItemPedido;
import com.app.devlanches.api.models.Pedido;
import com.app.devlanches.api.models.Produto;
import com.app.devlanches.api.models.dto.ItemPedidoDTO;
import com.app.devlanches.api.models.dto.PedidoDTO;
import com.app.devlanches.api.repository.PedidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	private final ProdutoService produtoService;
	private final ClienteService clienteService;
	private final ItemPedidoService itemPedidoService;

	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

	public Pedido getPedidoById(Long id) {
		return findOrFail(id);
	}

	public Pedido save(PedidoDTO pedidoDto) {
		Cliente cliente = clienteService.getClienteById(pedidoDto.getCliente());
		Pedido pedidoBuilder = Pedido.builder().status(pedidoDto.getStatus()).total(pedidoDto.getTotal())
				.cliente(cliente).build();

		Pedido pedido = pedidoRepository.save(pedidoBuilder);
		List<ItemPedido> listItemPedido = convertItems(pedido, pedidoDto.getItens());
		itemPedidoService.saveAll(listItemPedido);
		pedido.setItemPedidos(listItemPedido);
		return pedido;
	}

	private Pedido findOrFail(Long id) {
		return pedidoRepository.findById(id).orElseThrow(() -> new ApiException("Pedido não encontrado."));
	}

	private List<ItemPedido> convertItems(Pedido pedido, List<ItemPedidoDTO> items) {

		if (items.isEmpty()) {
			throw new ApiException("Não é possivel fazer pedido sem itens.");
		}

		return items.stream().map(itemDto -> {
			Produto produto = produtoService.getClienteById(itemDto.getProduto());
			return ItemPedido.builder().pedido(pedido).produto(produto).quantidade(itemDto.getQuantidade()).build();
		}).collect(Collectors.toList());
	}
}
