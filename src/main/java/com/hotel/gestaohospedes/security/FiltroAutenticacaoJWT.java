package com.hotel.gestaohospedes.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
public class FiltroAutenticacaoJWT extends OncePerRequestFilter {

    private final GeradorToken geradorToken;

    public FiltroAutenticacaoJWT(GeradorToken geradorDeToken) {
        this.geradorToken = geradorDeToken;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String cabecalho = request.getHeader("Authorization");

        if (cabecalho != null && cabecalho.startsWith("Bearer ")) {
            try {
                String token = cabecalho.substring(7);
                Claims claims = geradorToken.validarEExtrairClaims(token);
                String nomeUsuario = claims.getSubject();
                String perfil = claims.get("perfil", String.class);
                var autoridades = List.of(new SimpleGrantedAuthority("ROLE_" + perfil));
                var autenticacao = new UsernamePasswordAuthenticationToken(nomeUsuario, null, autoridades);
                SecurityContextHolder.getContext().setAuthentication(autenticacao);
            } catch (Exception tokenInvalido) {
                SecurityContextHolder.clearContext();
            }
        }
        chain.doFilter(request, response);
    }
}
