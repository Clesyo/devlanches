package com.app.devlanches.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.devlanches.api.models.ItemPedido;
import com.app.devlanches.api.repository.ItemPedidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemPedidoService {

	private final ItemPedidoRepository itemPedidoRepository;
	
	public ItemPedido save(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}
	
	public List<ItemPedido> saveAll(List<ItemPedido> itens) {
		return itemPedidoRepository.saveAll(itens);
	}
}
