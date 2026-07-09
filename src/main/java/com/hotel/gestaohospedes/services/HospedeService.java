package com.hotel.gestaohospedes.services;

import com.hotel.gestaohospedes.entity.Hospede;
import com.hotel.gestaohospedes.exception.NaoEncontradoException;
import com.hotel.gestaohospedes.mappers.HospedeMapper;
import com.hotel.gestaohospedes.records.HospedeRequestDTO;
import com.hotel.gestaohospedes.records.HospedeResponseDTO;
import com.hotel.gestaohospedes.repository.HospedeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HospedeService {

    private final HospedeRepository hospedeRepository;
    private final HospedeMapper hospedeMapper;

    @Transactional
    public HospedeResponseDTO cadastrar(HospedeRequestDTO dto) {
        Hospede hospede = hospedeMapper.paraEntidade(dto);
        return hospedeMapper.paraResponseDTO(hospedeRepository.save(hospede));
    }

    public Page<HospedeResponseDTO> buscar(String termo, Pageable pageable) {
        String filtro = termo == null ? "" : termo;
        return hospedeRepository
                .findByNomeContainingIgnoreCaseOrDocumentoContainingIgnoreCaseOrTelefoneContainingIgnoreCase(
                        filtro, filtro, filtro, pageable)
                .map(hospedeMapper::paraResponseDTO);
    }

    public HospedeResponseDTO buscarPorId(Long id) {
        return hospedeMapper.paraResponseDTO(buscarEntidadePorId(id));
    }

    @Transactional
    public HospedeResponseDTO atualizar(Long id, HospedeRequestDTO dto) {
        Hospede hospede = buscarEntidadePorId(id);
        hospede.setNome(dto.nome());
        hospede.setDocumento(dto.documento());
        hospede.setTelefone(dto.telefone());
        return hospedeMapper.paraResponseDTO(hospede);
    }

    @Transactional
    public void remover(Long id) {
        hospedeRepository.delete(buscarEntidadePorId(id));
    }

    private Hospede buscarEntidadePorId(Long id) {
        return hospedeRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Hóspede não encontrado: id " + id));
    }
}
