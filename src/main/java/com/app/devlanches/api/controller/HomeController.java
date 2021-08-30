package com.app.devlanches.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "<h1>Bem vindo a API - Dev Lanches</h1>";
	}
}
