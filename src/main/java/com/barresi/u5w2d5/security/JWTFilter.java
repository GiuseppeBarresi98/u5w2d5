package com.barresi.u5w2d5.security;


import com.barresi.u5w2d5.entities.Dipendente;
import com.barresi.u5w2d5.exceptions.UnauthorizedException;
import com.barresi.u5w2d5.services.DipendenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private JWTTools jwtTools;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    if(authHeader == null) {
        throw new UnauthorizedException("Metti il token");
    }
    else {
        String accessToken = authHeader.substring(7);
        System.out.println("ACCESS TOKEN" + accessToken);

        jwtTools.verifyToken(accessToken);
        String id = jwtTools.getIDfromtoken(accessToken);
        Dipendente dipendente = dipendenteService.findDipendenteById(UUID.fromString(id));
        Authentication authentication = new UsernamePasswordAuthenticationToken(dipendente, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }


}
