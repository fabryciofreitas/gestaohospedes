package com.hotel.gestaohospedes.records;

import jakarta.validation.constraints.NotBlank;

public record HospedeRequestDTO(
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotBlank(message = "O documento é obrigatório") String documento,
        @NotBlank(message = "O telefone é obrigatório") String telefone
) {}
