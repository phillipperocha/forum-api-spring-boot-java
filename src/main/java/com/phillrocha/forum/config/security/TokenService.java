package com.phillrocha.forum.config.security;

import java.util.Date;

import com.phillrocha.forum.models.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private Long expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication ){
        Usuario loggedUser = (Usuario) authentication.getPrincipal();

        Date hoje = new Date();

        Date expirationDate = new Date(hoje.getTime() + expiration);

        return Jwts.builder()
                .setIssuer("Forum API")
                .setSubject(loggedUser.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256 ,secret)
                .compact();

    }

}
