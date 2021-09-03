package com.app.devlanches.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "gestores")
public class Gestor {
	
	public Gestor() {
	}
	
	public Gestor(String nome,String email,String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	public  Gestor(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	
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
	@Length(min = 8, message = "Informe uma senha de minimo 8 caracteres")
	private String senha;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
