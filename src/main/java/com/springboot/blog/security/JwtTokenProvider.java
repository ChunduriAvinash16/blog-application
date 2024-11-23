package com.springboot.blog.security;

import com.springboot.blog.exception.BlogAPIException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    // generate JWT token
    public String generateToken(Authentication authentication) {
        String name = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationDate);

        return Jwts.builder().subject(name)
                .issuedAt(currentDate)
                .expiration(expirationDate)
                .signWith(key())
                .compact();
    }

    //Get username from JWT token
    public String getUserNameFromToken(String token) {
        return Jwts.parser().verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    //Validate Jwt token
    public Boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException malformedJwtException) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT Token");
        } catch (ExpiredJwtException expiredJwtException) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Token is Expired");
        } catch (UnsupportedJwtException unsupportedJwtException) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported Jwt token");
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Jwt claims is null or empty");
        }

    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}
