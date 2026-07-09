package com.hotel.gestaohospedes.entity;

import com.hotel.gestaohospedes.enums.PerfilAcesso;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_usuario", nullable = false, unique = true, length = 60)
    private String nomeUsuario;

    // gravada em hash
    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PerfilAcesso perfil;
}
