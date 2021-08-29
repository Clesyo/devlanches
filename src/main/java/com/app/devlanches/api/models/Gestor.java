package com.app.devlanches.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gestores")
public class Gestor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@Column(nullable = false)
	@NotBlank(message = "Nome do gestor deve der informado.")
	private String nome;
	@Column(nullable = false)
	@NotBlank(message = "Email do gestor deve der informado.")
	@Email(message = "Email está no formato errado")
	private String email;
	@Column(nullable = false)
	@NotBlank(message = "Senha deve deve ser informada.")
	private String senha;
}
