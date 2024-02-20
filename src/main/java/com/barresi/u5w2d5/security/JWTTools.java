package com.barresi.u5w2d5.security;


import com.barresi.u5w2d5.entities.Dipendente;
import com.barresi.u5w2d5.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;


    public String createToken(Dipendente dipendente){
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()*1000*60*60))
                .subject(String.valueOf(dipendente.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }


    public void verifyToken(String token) {
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        } catch (Exception ex) {
            throw new UnauthorizedException("Problemi col token! Effettua di nuovo il login!");
        }
    }

    public String getIDfromtoken(String token){
          return  Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parseSignedClaims(token).getPayload().getSubject();
    }
}
