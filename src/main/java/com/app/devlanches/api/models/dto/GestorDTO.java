package com.app.devlanches.api.models.dto;

import com.app.devlanches.api.models.Gestor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GestorDTO {

	private Long id;
	private String nome;
	private String email;

	public static GestorDTO convertToDto(Gestor gestor) {
		return new GestorDTO(gestor.getId(), gestor.getNome(), gestor.getEmail());
	}
}
