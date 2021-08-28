package com.app.devlanches.api.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@NotBlank(message = "Compo Nome deve ser preenchido")
	private String nome;
	@Column(nullable = false)
	@NotBlank(message = "Compo Nascimento deve ser preenchido")
	private String nascimento;
	@Column(nullable = false)
	@NotBlank(message = "Compo Telefone deve ser preenchido")
	private String telefone;
	@Column(unique = true, nullable = false)
	@NotBlank(message = "Compo Email deve ser preenchido")
	@Email(message = "Endereço de e-mail enviado em um formato inválido")
	private String email;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;
}
