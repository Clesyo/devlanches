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
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "clientes")
public class Cliente {

	public Cliente() {
	}
	
	public Cliente(String nome, String nascimento, String telefone, String email) {
		this.nome = nome;
		this.nascimento = nascimento;
		this.telefone = telefone;
		this.email = email;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@NotEmpty(message = "Compo Nome deve ser preenchido")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	
}
