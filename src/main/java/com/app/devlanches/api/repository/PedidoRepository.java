package com.app.devlanches.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.devlanches.api.models.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query(value = "select * from pedidos where cliente_id = :cliente_id", nativeQuery = true)
	List<Pedido> findByIdCliente(@Param(value = "cliente_id") Long id);
}
