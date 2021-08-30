package com.app.devlanches.api.configuration.security.auth.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.devlanches.api.configuration.security.auth.ApiUserDetailService;

public class JwtAuthFilter extends OncePerRequestFilter {


	private JwtService jwtService;
	private ApiUserDetailService userDetailService;

	public JwtAuthFilter(JwtService jwtService, ApiUserDetailService userDetailService) {
		this.jwtService = jwtService;
		this.userDetailService = userDetailService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String authorization = request.getHeader("Authorization");

		if (authorization != null && authorization.startsWith("Bearer")) {
			String token = authorization.split(" ")[1];

			boolean isTokenValid = jwtService.tokenValid(token);

			if (isTokenValid) {
				String loginUser = jwtService.obtainLoginUser(token);
				UserDetails user = userDetailService.loadUserByUsername(loginUser);
				UsernamePasswordAuthenticationToken userPasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						user, null, user.getAuthorities());
				
				userPasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userPasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}}
