package com.app.devlanches.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.devlanches.api.models.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
