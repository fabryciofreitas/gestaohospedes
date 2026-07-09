package com.hotel.gestaohospedes.records;

import com.hotel.gestaohospedes.security.Token;

import java.time.Instant;

public record LoginResponseDTO(Token token, String perfil, Instant expiraEm) {}
