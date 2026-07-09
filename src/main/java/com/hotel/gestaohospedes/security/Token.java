package com.hotel.gestaohospedes.security;

import java.time.Instant;

public record Token(String token, Instant expiracao) {}
