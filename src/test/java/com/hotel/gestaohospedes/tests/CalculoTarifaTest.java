package com.hotel.gestaohospedes.tests;

import com.hotel.gestaohospedes.services.CalculoTarifa;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

class CalculoTarifaTest {

    private final CalculoTarifa calculadora = new CalculoTarifa();

    @Test
    void duasDiariasEmDiaUtilSemVeiculo() {
        
        // segunda 14h -> quarta 10h
        LocalDateTime entrada = LocalDateTime.of(2026, 8, 10, 14, 0); // segunda-feira
        LocalDateTime saida = LocalDateTime.of(2026, 8, 12, 10, 0); // quarta-feira
        BigDecimal total = calculadora.calcular(entrada, saida, false);
        assertThat(total).isEqualByComparingTo("240.00"); // 2 x 120,00
    }

    @Test
    void cobraDiariaExtraQuandoSaidaDepoisDas16h30() {
        LocalDateTime entrada = LocalDateTime.of(2026, 8, 10, 14, 0); // segunda-feira
        LocalDateTime saida = LocalDateTime.of(2026, 8, 12, 17, 0); // quarta-feira, 17h
        BigDecimal total = calculadora.calcular(entrada, saida, true);
        // 2 diárias normais (seg+ter) + 1 extra (qua), todas dia útil, todas com garagem
        assertThat(total).isEqualByComparingTo("405.00");
    }

    @Test
    void aplicaTarifaDeFimDeSemanaCorretamente() {
        LocalDateTime entrada = LocalDateTime.of(2026, 8, 14, 12, 0); // sexta-feira
        LocalDateTime saida = LocalDateTime.of(2026, 8, 16, 11, 0); // domingo
        BigDecimal total = calculadora.calcular(entrada, saida, false);
        // sexta (dia útil, 120) + sábado (fim de semana, 150) = 270,00
        assertThat(total).isEqualByComparingTo("270.00");
    }

    @Test
    void estadiaNoMesmoDiaCobraUmaDiariaCheia() {
        LocalDateTime entrada = LocalDateTime.of(2026, 8, 10, 9, 0);
        LocalDateTime saida = LocalDateTime.of(2026, 8, 10, 15, 0);
        BigDecimal total = calculadora.calcular(entrada, saida, false);
        assertThat(total).isEqualByComparingTo("120.00");
    }
}
