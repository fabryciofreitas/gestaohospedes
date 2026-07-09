package com.hotel.gestaohospedes.tests;

import com.hotel.gestaohospedes.exception.NaoEncontradoException;
import com.hotel.gestaohospedes.mappers.HospedeMapper;
import com.hotel.gestaohospedes.repository.HospedeRepository;
import com.hotel.gestaohospedes.services.HospedeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class HospedeServiceTest {
    @Mock private HospedeRepository hospedeRepository;
    @Mock private HospedeMapper hospedeMapper;
    @InjectMocks private HospedeService hospedeService;
    @Test
    void lancaExcecaoAoBuscarHospedeInexistente() {
        when(hospedeRepository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> hospedeService.buscarPorId(99L))
                .isInstanceOf(NaoEncontradoException.class)
                .hasMessageContaining("99");
    }
}
