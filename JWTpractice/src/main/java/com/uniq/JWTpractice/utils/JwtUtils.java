package com.uniq.JWTpractice.utils;

import java.security.Key;

import org.springframework.stereotype.Component;

import com.uniq.JWTpractice.Entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	private static final Key k = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public String generateJwt(User user) {

		Claims claims = Jwts.claims().setIssuer(String.valueOf(user.getId()));
		claims.put("Name", user.getName());
		claims.put("Email", user.getEmail());
		claims.put("role", user.getRole());

		String token = Jwts.builder().setClaims(claims).signWith(k).compact();

		return token;

	}

	public boolean verify(String token) throws Exception {

		try {
			Jwts.parserBuilder().setSigningKey(k).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String extractJwtToken(String token) {
		
		return Jwts.parserBuilder().setSigningKey(k).build().parseClaimsJws(token).getBody().getIssuer();
	}
	
	
	
	//0.12 VERSION
//	public String extractIssuer(String token) {
//        Claims claims = Jwts.parser()  // ✅ Use Jwts.parser() in 0.12.0
//                .verifyWith(k)        // ✅ Replaces setSigningKey()
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();          // ✅ Not getBody() anymore
//
//        return claims.getIssuer();      // ✅ issuer from payload
//    }
}