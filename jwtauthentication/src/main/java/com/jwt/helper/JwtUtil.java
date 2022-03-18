package com.jwt.helper;

import java.util.*;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

/* This is a util class of jwt that will have following methods:
 *  1. For generating Token
 *  2. For validating token
 *  3. For checking if token is expired or not
*/

@Component
public class JwtUtil {
	
	private static final long serialVersionUID = 2550185165626007488L;
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	private String SECRET = "mysecret";

	// retrieve username from jwt token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // retrieve expiration date from jwt token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    // for retrieving any information from token, we will need the secret key
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    // check if token is expired or not
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /* Because of JJWT’s fluent interface, the creation of the JWT is basically a three-step process:
     *  1. Define internal claims of the token; like Issuer, Expiration, Subject, and the ID.
     *  2. The cryptographic signing of the JWT (making it a JWS). Sign the JWT using the HS512 algorithm and secret key.
     *  3. The compaction of the JWT to a URL-safe string, according to JWT Compact Serialization
     *  (https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
     *  
     *  The final JWT will be a three-part base64-encoded string, signed with the specified signature algorithm, and using
     *  the provided key. After this point, the token is ready to be shared with the another party.
     */
    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();
    }

    // validate the token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
