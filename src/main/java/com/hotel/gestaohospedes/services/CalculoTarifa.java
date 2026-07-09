package com.hotel.gestaohospedes.services;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class CalculoTarifa {

    private static final BigDecimal DIARIA_DIA_UTIL = new BigDecimal("120.00");
    private static final BigDecimal DIARIA_FIM_DE_SEMANA = new BigDecimal("150.00");
    private static final BigDecimal GARAGEM_DIA_UTIL = new BigDecimal("15.00");
    private static final BigDecimal GARAGEM_FIM_DE_SEMANA = new BigDecimal("20.00");
    private static final LocalTime HORARIO_LIMITE_SAIDA = LocalTime.of(16, 30);

    public BigDecimal calcular(LocalDateTime dataEntrada, LocalDateTime dataSaida, boolean possuiVeiculo) {
        if (dataSaida.isBefore(dataEntrada)) {
            throw new IllegalArgumentException("A data de saída não pode ser anterior à data de entrada.");
        }
        LocalDate diaEntrada = dataEntrada.toLocalDate();
        LocalDate diaSaida = dataSaida.toLocalDate();
        BigDecimal total = BigDecimal.ZERO;
        // Uma diária para cada noite realmente ocupada (mínimo de 1 diária)
        long noites = java.time.temporal.ChronoUnit.DAYS.between(diaEntrada, diaSaida);
        if (noites == 0) {
            total = total.add(valorDaDiaria(diaEntrada, possuiVeiculo));
        } else {
            for (long i = 0; i < noites; i++) {
                total = total.add(valorDaDiaria(diaEntrada.plusDays(i), possuiVeiculo));
            }
        }
        // 2) Diária extra se o check-out for depois das 16h30
        if (dataSaida.toLocalTime().isAfter(HORARIO_LIMITE_SAIDA)) {
            total = total.add(valorDaDiaria(diaSaida, possuiVeiculo));
        }
        return total;
    }

    private BigDecimal valorDaDiaria(LocalDate dia, boolean possuiVeiculo) {
        boolean fimDeSemana = dia.getDayOfWeek() == DayOfWeek.SATURDAY
                || dia.getDayOfWeek() == DayOfWeek.SUNDAY;
        BigDecimal valor = fimDeSemana ? DIARIA_FIM_DE_SEMANA : DIARIA_DIA_UTIL;
        if (possuiVeiculo) {
            valor = valor.add(fimDeSemana ? GARAGEM_FIM_DE_SEMANA : GARAGEM_DIA_UTIL);
        }
        return valor;
    }
}
