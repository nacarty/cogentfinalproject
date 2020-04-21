package com.carty.config;

import io.jsonwebtoken.*;  //This is where Claims object comes from
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

//import com.carty.User;

import static com.carty.model.Constants.ACCESS_TOKEN_VALIDITY_SECONDS; //18000
import static com.carty.model.Constants.AUTHORITIES_KEY; //"scopes"
import static com.carty.model.Constants.SIGNING_KEY; // "nigel"

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TokenProvider implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 8325993735489987937L;

public String getUsernameFromToken(String token) {
	return getClaimFromToken(token, Claims::getSubject);
}

public Date getExpirationDateFromToken(String token) {
	return getClaimFromToken(token, Claims::getExpiration);
}

public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
	final Claims claims = getAllClaimsFromToken(token);
	return claimsResolver.apply(claims);
}

private Claims getAllClaimsFromToken(String token) {
	return Jwts.parser()
				.setSigningKey(SIGNING_KEY)
				.parseClaimsJws(token)
				.getBody();
}

private Boolean isTokenExpired(String token) {
	final Date expiration = getExpirationDateFromToken(token);
	return expiration.before(new Date());
}

public String generateToken(Authentication authentication) {
	final String authorities = authentication.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.joining(","));
	
			return Jwts.builder()
							.setSubject(authentication.getName())
							.claim(AUTHORITIES_KEY, authorities)
							.signWith(SignatureAlgorithm.HS256, SIGNING_KEY) //secret-key= 'nigel'
							.setIssuedAt(new Date(System.currentTimeMillis()))
							.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
							.compact();
}

public Boolean validateToken(String token, UserDetails userDetails) {
	final String username = getUsernameFromToken(token);
	return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
}

UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final UserDetails userDetails) {

final JwtParser jwtParser = Jwts.parser().setSigningKey(SIGNING_KEY); //secret-key= 'nigel'

final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

final Claims claims = claimsJws.getBody();

//claimsJws.getBody().

final Collection<? extends GrantedAuthority> authorities =
	Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))  //AUTHORITIES_KEY = 'scopes'
	.map(SimpleGrantedAuthority::new)
	.collect(Collectors.toList());

	return new UsernamePasswordAuthenticationToken(userDetails, "", authorities); //second parameter is credentials
}

}