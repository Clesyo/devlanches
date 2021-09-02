package com.app.devlanches.api.models.dto;

import com.app.devlanches.api.models.Gestor;

public class GestorDTO {

	private Long id;
	private String nome;
	private String email;

	public GestorDTO() {
		// TODO Auto-generated constructor stub
	}

	public GestorDTO(Long id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public GestorDTO(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}

	public static GestorDTO convertToDto(Gestor gestor) {
		return new GestorDTO(gestor.getNome(), gestor.getEmail());
	}

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

}
