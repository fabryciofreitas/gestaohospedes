package com.hotel.gestaohospedes.repository;

import com.hotel.gestaohospedes.entity.Hospede;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospedeRepository extends JpaRepository<Hospede, Long> {

    Page<Hospede> findByNomeContainingIgnoreCaseOrDocumentoContainingIgnoreCaseOrTelefoneContainingIgnoreCase(
            String nome, String documento, String telefone, Pageable pageable);
}
