package com.hotel.gestaohospedes.records;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(@NotBlank String nomeUsuario, @NotBlank String senha) {}
