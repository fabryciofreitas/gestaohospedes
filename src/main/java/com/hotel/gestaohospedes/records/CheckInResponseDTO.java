package com.hotel.gestaohospedes.records;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CheckInResponseDTO(
        Long id,
        HospedeResponseDTO hospede,
        LocalDateTime dataEntrada,
        LocalDateTime dataSaida,
        boolean possuiVeiculo,
        BigDecimal valorTotal
) {}
