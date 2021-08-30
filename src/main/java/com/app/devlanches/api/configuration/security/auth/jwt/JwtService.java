package com.app.devlanches.api.configuration.security.auth.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.devlanches.api.models.Gestor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Value("${security.jwt.expiration}")
	private String expiration;
	@Value("${security.jwt.subscription-key}")
	private String signKey;

	public String gerarToken(Gestor gestor) {
		Long expiracao = Long.valueOf(this.expiration);
		LocalDateTime horaExpiracao = LocalDateTime.now().plusMinutes(expiracao);
		Instant instant = horaExpiracao.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);

		return Jwts.builder().setSubject(gestor.getEmail()).setExpiration(date)
				.signWith(SignatureAlgorithm.ES512, this.signKey).compact();
	}

	private Claims obtainClaims(String token) throws ExpiredJwtException {
		return Jwts.parser().setSigningKey(this.signKey).parseClaimsJws(token).getBody();
	}

	public String obtainLoginUser(String token) throws ExpiredJwtException {
		return (String) obtainClaims(token).getSubject();
	}

	public boolean tokenValid(String token) {
		try {
			Claims claims = obtainClaims(token);
			Date dateExpiraton = claims.getExpiration();
			LocalDateTime localDateTime = dateExpiraton.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			return !LocalDateTime.now().isAfter(localDateTime);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
