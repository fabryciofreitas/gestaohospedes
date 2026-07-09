package com.hotel.gestaohospedes.repository;

import com.hotel.gestaohospedes.entity.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {

    List<CheckIn> findByDataSaidaIsNull(); // hóspedes ainda no hotel
    List<CheckIn> findByDataSaidaIsNotNull(); // hóspedes que já saíram
    List<CheckIn> findByHospedeIdOrderByDataEntradaDesc(Long hospedeId);
}
