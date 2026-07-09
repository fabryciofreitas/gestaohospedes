package com.hotel.gestaohospedes.repository;

import com.hotel.gestaohospedes.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNomeUsuario(String nomeUsuario);
}
