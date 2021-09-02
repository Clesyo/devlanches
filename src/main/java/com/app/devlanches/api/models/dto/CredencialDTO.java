package com.app.devlanches.api.models.dto;

public class CredencialDTO {

	private String login;
	private String password;

	public CredencialDTO() {
	}

	public CredencialDTO(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
