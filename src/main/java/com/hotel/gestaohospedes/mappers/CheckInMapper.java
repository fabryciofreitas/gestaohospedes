package com.hotel.gestaohospedes.mappers;

import com.hotel.gestaohospedes.entity.CheckIn;
import com.hotel.gestaohospedes.records.CheckInResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckInMapper {

    private final HospedeMapper hospedeMapper;

    public CheckInResponseDTO paraResponseDTO(CheckIn checkIn) {
        if (checkIn == null) {
            return null;
        }

        // Converte a entidade de hóspede vinculada para o DTO de hóspede correspondente
        var hospedeDTO = hospedeMapper.paraResponseDTO(checkIn.getHospede());

        // Monta e retorna o registro record do DTO de resposta do check-in
        return new CheckInResponseDTO(
                checkIn.getId(),
                hospedeDTO,
                checkIn.getDataEntrada(),
                checkIn.getDataSaida(),
                checkIn.isPossuiVeiculo(),
                checkIn.getValorTotal()
        );
    }
}
