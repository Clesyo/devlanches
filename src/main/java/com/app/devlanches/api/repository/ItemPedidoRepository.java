package com.app.devlanches.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.devlanches.api.models.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

}
