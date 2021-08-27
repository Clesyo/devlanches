package com.app.devlanches.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.devlanches.api.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
