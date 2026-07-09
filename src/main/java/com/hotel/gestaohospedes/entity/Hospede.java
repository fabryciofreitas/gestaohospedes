package com.hotel.gestaohospedes.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "hospede")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hospede implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 30)
    private String documento;

    @Column(nullable = false, length = 20)
    private String telefone;
}
