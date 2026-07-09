package com.hotel.gestaohospedes.records;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CheckInRequestDTO(
        @NotNull(message = "Informe o hóspede") Long hospedeId,
        LocalDateTime dataEntrada, // opcional: se nulo, usamos o momento atual
        boolean possuiVeiculo
) {}
