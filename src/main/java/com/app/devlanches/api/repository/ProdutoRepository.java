package com.app.devlanches.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.devlanches.api.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
