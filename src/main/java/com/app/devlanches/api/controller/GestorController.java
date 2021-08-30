package com.app.devlanches.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.devlanches.api.configuration.security.auth.ApiUserDetailService;
import com.app.devlanches.api.configuration.security.auth.jwt.JwtService;
import com.app.devlanches.api.exception.PasswordInvalidException;
import com.app.devlanches.api.models.Gestor;
import com.app.devlanches.api.models.dto.CredencialDTO;
import com.app.devlanches.api.models.dto.GestorDTO;
import com.app.devlanches.api.models.dto.TokenDTO;
import com.app.devlanches.api.service.GestorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/gestor", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GestorController {

	private final GestorService service;
	private final PasswordEncoder encode;
	private final ApiUserDetailService userDetailService;
	private final JwtService jwtService;

	@GetMapping
	@ApiOperation("Busca todos os gestores")
	public List<GestorDTO> getAll() {
		return service.getAll().stream().map(gestor -> {
			return GestorDTO.convertToDto(gestor);
		}).collect(Collectors.toList());
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Salve um Gestor")
	@ApiResponses({ @ApiResponse(code = 201, message = "Gestor salvo com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public GestorDTO salvar(@RequestBody @Valid Gestor gestor) {
		String senha = encode.encode(gestor.getSenha());
		gestor.setSenha(senha);
		return GestorDTO.convertToDto(service.salvar(gestor));
	}
	
	@PostMapping("/auth")
	public TokenDTO authentication(@RequestBody CredencialDTO credencial) {
		try {
			Gestor gestor = Gestor.builder().email(credencial.getLogin()).senha(credencial.getPassword()).build();
			UserDetails userAuthenticate = userDetailService.authenticate(gestor);
			String token = jwtService.gerarToken(gestor);
			return new TokenDTO(gestor.getEmail(), token);
		} catch (UsernameNotFoundException | PasswordInvalidException e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}
}
