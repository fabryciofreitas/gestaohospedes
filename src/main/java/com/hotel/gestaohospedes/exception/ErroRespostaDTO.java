package com.hotel.gestaohospedes.exception;

import java.time.LocalDateTime;

public record ErroRespostaDTO(LocalDateTime momento, int status, String mensagem, String caminho) {}
