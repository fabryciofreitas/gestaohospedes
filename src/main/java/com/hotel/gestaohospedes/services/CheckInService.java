package com.hotel.gestaohospedes.services;

import com.hotel.gestaohospedes.entity.CheckIn;
import com.hotel.gestaohospedes.entity.Hospede;
import com.hotel.gestaohospedes.records.*;
import com.hotel.gestaohospedes.exception.NaoEncontradoException;
import com.hotel.gestaohospedes.exception.RegraDeNegocioException;
import com.hotel.gestaohospedes.mappers.CheckInMapper;
import com.hotel.gestaohospedes.repository.CheckInRepository;
import com.hotel.gestaohospedes.repository.HospedeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckInService {

    private final CheckInRepository checkInRepository;
    private final HospedeRepository hospedeRepository;
    private final CalculoTarifa calculoTarifa;
    private final CheckInMapper checkInMapper;

    @Transactional
    public CheckInResponseDTO registrarEntrada(CheckInRequestDTO dto) {
        Hospede hospede = hospedeRepository.findById(dto.hospedeId())
                .orElseThrow(() -> new NaoEncontradoException("Hóspede não encontrado: id " +
                        dto.hospedeId()));
        CheckIn checkIn = CheckIn.builder()
                .hospede(hospede)
                .dataEntrada(dto.dataEntrada() != null ? dto.dataEntrada() : LocalDateTime.now())
                .possuiVeiculo(dto.possuiVeiculo())
                .build();
        return checkInMapper.paraResponseDTO(checkInRepository.save(checkIn));
    }

    @Transactional
    public CheckInResponseDTO registrarSaida(Long checkInId, LocalDateTime dataSaidaInformada) {
        CheckIn checkIn = checkInRepository.findById(checkInId)
                .orElseThrow(() -> new NaoEncontradoException("Check-in não encontrado: id " + checkInId));
        if (!checkIn.estaHospedado()) {
            throw new RegraDeNegocioException("Este check-in já possui check-out registrado.");
        }
        LocalDateTime dataSaida = dataSaidaInformada != null ? dataSaidaInformada : LocalDateTime.now();
        BigDecimal valor = calculoTarifa.calcular(checkIn.getDataEntrada(), dataSaida,
                checkIn.isPossuiVeiculo());
        checkIn.setDataSaida(dataSaida);
        checkIn.setValorTotal(valor);
        return checkInMapper.paraResponseDTO(checkIn);
    }

    public List<CheckInResponseDTO> listarHospedesPresentes() {
        return checkInRepository.findByDataSaidaIsNull().stream()
                .map(checkInMapper::paraResponseDTO).toList();
    }

    public List<CheckInResponseDTO> listarHospedesQueJaSairam() {
        return checkInRepository.findByDataSaidaIsNotNull().stream()
                .map(checkInMapper::paraResponseDTO).toList();
    }

    public ResumoFinanceiroDTO resumoFinanceiroDoHospede(Long hospedeId) {
        List<CheckIn> historico = checkInRepository.findByHospedeIdOrderByDataEntradaDesc(hospedeId);
        BigDecimal valorTotalGasto = historico.stream()
                .map(c -> c.getValorTotal() != null ? c.getValorTotal() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal valorUltimaHospedagem = historico.stream()
                .filter(c -> c.getValorTotal() != null)
                .findFirst()
                .map(CheckIn::getValorTotal)
                .orElse(BigDecimal.ZERO);
        return new ResumoFinanceiroDTO(valorTotalGasto, valorUltimaHospedagem);
    }
}
