package com.app.devlanches.api.configuration.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.devlanches.api.exception.PasswordInvalidException;
import com.app.devlanches.api.models.Gestor;
import com.app.devlanches.api.service.GestorService;

@Service
public class ApiUserDetailService implements UserDetailsService {

	@Autowired
	private GestorService gestorService;
	private PasswordEncoder encode = new BCryptPasswordEncoder();

	public UserDetails authenticate(Gestor gestor) {

		UserDetails userLogin = loadUserByUsername(gestor.getEmail());
		boolean isValidPassword = encode.matches(gestor.getSenha(), userLogin.getPassword());

		if (isValidPassword) {
			return userLogin;
		}
		throw new PasswordInvalidException();
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Gestor gestor = gestorService.findByEmail(email);

		return UserCustom.builder().password(gestor.getSenha()).username(gestor.getEmail()).roles("ADMIN").build();
	}

}
