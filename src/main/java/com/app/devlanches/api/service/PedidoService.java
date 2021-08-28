package com.app.devlanches.api.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.devlanches.api.enums.StatusPedido;
import com.app.devlanches.api.exception.EntityNotExist;
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
	private double total = 0;

	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

	public Pedido getPedidoById(Long id) {
		return findOrFail(id);
	}

	public Pedido save(PedidoDTO pedidoDto) {
		Cliente cliente = clienteService.getClienteById(pedidoDto.getCliente());

		pedidoDto.getItens().forEach(dto -> {
			Produto produto = produtoService.getProdutoById(dto.getProduto());
			total += (produto.getValor().doubleValue() * dto.getQuantidade());
		});
		Pedido pedidoBuilder = Pedido.builder().status(StatusPedido.PENDENTE).total(BigDecimal.valueOf(total))
				.cliente(cliente).build();

		Pedido pedido = pedidoRepository.save(pedidoBuilder);
		List<ItemPedido> listItemPedido = convertItems(pedido, pedidoDto.getItens());
		itemPedidoService.saveAll(listItemPedido);
		pedido.setItemPedidos(listItemPedido);
		return pedido;
	}

	public List<Pedido> getPedidoByIdCliente(Long id) {
		return pedidoRepository.findByIdCliente(id);
	}

	@Transactional
	public void cancelaPedido(Long id) {

		pedidoRepository.findById(id).map(pedido -> {
			/*
			 * Caso o pedido tenha o status diferente de PENDENTE, é lançado uma exceção
			 */
			if (pedido.getStatus() != StatusPedido.PENDENTE) {
				throw new EntityNotExist("Pedido não pode ser cancelado.");
			}
			/*
			 * Muda status do pedido para CANCELADO
			 */
			pedido.setStatus(StatusPedido.CANCELADO);
			return pedidoRepository.save(pedido);
		}).orElseThrow(() -> new EntityNotExist("Pedido não encontrado."));
	
	}

	@Transactional
	public void mudaStatuPedido(Long id, StatusPedido status) {
		pedidoRepository.findById(id).map(pedido -> {
			pedido.setStatus(status);
			return pedidoRepository.save(pedido);
		}).orElseThrow(() -> new EntityNotExist("Pedido não encontrado."));
	}

	private Pedido findOrFail(Long id) {
		return pedidoRepository.findById(id).orElseThrow(() -> new EntityNotExist("Pedido não encontrado."));
	}

	private List<ItemPedido> convertItems(Pedido pedido, List<ItemPedidoDTO> items) {

		if (items.isEmpty()) {
			throw new EntityNotExist("Não é possivel fazer pedido sem itens.");
		}

		return items.stream().map(itemDto -> {
			Produto produto = produtoService.getProdutoById(itemDto.getProduto());
			return ItemPedido.builder().pedido(pedido).produto(produto).quantidade(itemDto.getQuantidade()).build();
		}).collect(Collectors.toList());
	}
}
