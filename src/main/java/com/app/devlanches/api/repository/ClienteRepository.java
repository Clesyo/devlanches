package com.app.devlanches.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.app.devlanches.api.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Cliente findByEmail(String email);
}
