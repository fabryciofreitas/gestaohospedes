package com.hotel.gestaohospedes.mappers;

import com.hotel.gestaohospedes.entity.Hospede;
import com.hotel.gestaohospedes.records.HospedeRequestDTO;
import com.hotel.gestaohospedes.records.HospedeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HospedeMapper {

    public Hospede paraEntidade(HospedeRequestDTO dto) {
        return Hospede.builder()
                .nome(dto.nome())
                .documento(dto.documento())
                .telefone(dto.telefone())
                .build();
    }

    public HospedeResponseDTO paraResponseDTO(Hospede hospede) {
        return new HospedeResponseDTO(hospede.getId(), hospede.getNome(), hospede.getDocumento(),
                hospede.getTelefone());
    }
}
