package com.voucher.jwt;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.voucher.securityservice.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

 

@Component
public class JwtUtility {

 

	@Value("${jwtSecret}")
	private String jwtSecret = "jwtSecretKey";

 

	@Value("${jwtExpirationMs}")
	private int jwtExpirationMs;

 

	public String generateToken(Authentication authentication) {

 


 

		UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
		Date now = new Date();
		Date expiredate = new Date(now.getTime() + jwtExpirationMs);
		return Jwts.builder().setSubject(user.getUsername()).claim("roles", roles).setIssuedAt(now)
				.setExpiration(expiredate).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

 

	public Boolean validateToken(String token) {


 

		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

 

	public String getJwtSecret() {
		return jwtSecret;
	}

 

	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}

 



 

	public String getUsername(String token) {
		return parseClaims(token).getSubject();
	}


 

	public List<String> extractRolesFromToken(String token) {
		Claims claims = parseClaims(token);
		return claims.get("roles", List.class);
	}

 

	private Claims parseClaims(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		
	}

 

}
