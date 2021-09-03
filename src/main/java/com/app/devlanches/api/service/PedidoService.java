package com.app.devlanches.api.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.devlanches.api.enums.StatusPedido;
import com.app.devlanches.api.exception.EntityNotExist;
import com.app.devlanches.api.impl.IPedidoService;
import com.app.devlanches.api.models.Cliente;
import com.app.devlanches.api.models.ItemPedido;
import com.app.devlanches.api.models.Pedido;
import com.app.devlanches.api.models.Produto;
import com.app.devlanches.api.models.dto.ItemPedidoDTO;
import com.app.devlanches.api.models.dto.PedidoDTO;
import com.app.devlanches.api.repository.PedidoRepository;

@Service
public class PedidoService implements IPedidoService{

	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ItemPedidoService itemPedidoService;

	public PedidoService(PedidoRepository pedidoRepository, ProdutoService produtoService,
			ClienteService clienteService, ItemPedidoService itemPedidoService) {
		this.pedidoRepository = pedidoRepository;
		this.produtoService = produtoService;
		this.clienteService = clienteService;
		this.itemPedidoService = itemPedidoService;
	}

	private double total = 0;

	@Override
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

	@Override
	public Pedido findById(Long id) {
		return findOrFail(id);
	}

	@Override
	public Pedido save(PedidoDTO pedidoDto) {
		Cliente cliente = clienteService.findById(pedidoDto.getCliente());

		pedidoDto.getItens().forEach(dto -> {
			Produto produto = produtoService.findById(dto.getProduto());
			total += (produto.getValor().doubleValue() * dto.getQuantidade());
		});
		Pedido pedidoBuilder = new Pedido(StatusPedido.PENDENTE, BigDecimal.valueOf(total), cliente);

		Pedido pedido = pedidoRepository.save(pedidoBuilder);
		List<ItemPedido> listItemPedido = convertItems(pedido, pedidoDto.getItens());
		itemPedidoService.saveAll(listItemPedido);
		pedido.setItemPedidos(listItemPedido);
		return pedido;
	}

	@Override
	public List<Pedido> findByIdCliente(Long id) {
		return pedidoRepository.findByIdCliente(id);
	}

	@Transactional
	@Override
	public void cancelPedido(Long id) {

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
	@Override
	public void changeStatusPedido(Long id, StatusPedido status) {
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
			Produto produto = produtoService.findById(itemDto.getProduto());
			return new ItemPedido(pedido, itemDto.getQuantidade(), produto);
		}).collect(Collectors.toList());
	}
}
