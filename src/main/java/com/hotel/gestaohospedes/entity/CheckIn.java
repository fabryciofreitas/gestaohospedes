package com.hotel.gestaohospedes.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "check_in")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckIn implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hospede_id", nullable = false)
    private Hospede hospede;

    @Column(name = "data_entrada", nullable = false)
    private LocalDateTime dataEntrada;

    @Column(name = "data_saida")
    private LocalDateTime dataSaida;

    @Column(name = "possui_veiculo", nullable = false)
    private boolean possuiVeiculo;

    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;

    public boolean estaHospedado() {
        return dataSaida == null;
    }
}
