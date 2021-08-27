package com.app.devlanches.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.devlanches.api.models.Gestor;

public interface GestorRepository extends JpaRepository<Gestor, Long> {

}
