package com.app.devlanches.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.devlanches.api.models.ItemPedido;
import com.app.devlanches.api.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	private  ItemPedidoRepository itemPedidoRepository;
	
	public ItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
		this.itemPedidoRepository = itemPedidoRepository;
	}

	public ItemPedido save(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}
	
	public List<ItemPedido> saveAll(List<ItemPedido> itens) {
		return itemPedidoRepository.saveAll(itens);
	}
}
