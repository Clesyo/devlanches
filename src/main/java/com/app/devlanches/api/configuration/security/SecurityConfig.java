package com.app.devlanches.api.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.devlanches.api.configuration.security.auth.ApiUserDetailService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ApiUserDetailService userDetailService;

	private String[] urls = { "/v2/api-docs", "configuration/ui", "/swagger-resources/**", "/configuration/security",
			"/swagger-ui.html", "/webjars/**" };

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/gestor/**").permitAll()
				.antMatchers("/cliente/**").permitAll().antMatchers(HttpMethod.POST, "/pedido/**").permitAll()
				.antMatchers("/pedido/**").hasRole("ADMIN").antMatchers("/produto/**").hasRole("ADMIN").anyRequest()
				.authenticated().and().httpBasic();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers(urls);
	}
}
