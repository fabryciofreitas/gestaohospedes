package com.hotel.gestaohospedes.security;

import com.hotel.gestaohospedes.enums.PerfilAcesso;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Component
public class GeradorToken {

    private final SecretKey chave;
    private final long minutosDeValidade;

    public GeradorToken(@Value("${app.jwt.chave-secreta}") String chaveSecreta,
                        @Value("${app.jwt.minutos-de-validade}") long minutosDeValidade) {
        this.chave = Keys.hmacShaKeyFor(chaveSecreta.getBytes());
        this.minutosDeValidade = minutosDeValidade;
    }

    public Token gerar(String nomeUsuario, PerfilAcesso perfil) {

        Instant agora = Instant.now();
        Instant expiracao = agora.plusSeconds(minutosDeValidade * 60);

        String token = Jwts.builder()
                .subject(nomeUsuario)
                .claim("perfil", perfil.name())
                .issuedAt(Date.from(agora))
                .expiration(Date.from(expiracao))
                .signWith(chave)
                .compact();

        return new Token(token, expiracao);
    }

    public Claims validarEExtrairClaims(String token) {
        return Jwts.parser().verifyWith(chave).build()
                .parseSignedClaims(token).getPayload();
    }

}
