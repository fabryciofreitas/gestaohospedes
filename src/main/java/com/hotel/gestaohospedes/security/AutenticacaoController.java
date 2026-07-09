package com.hotel.gestaohospedes.security;

import com.hotel.gestaohospedes.records.LoginRequestDTO;
import com.hotel.gestaohospedes.records.LoginResponseDTO;
import com.hotel.gestaohospedes.services.AutenticacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    @PostMapping("/login")
    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO dto) {
        return autenticacaoService.autenticar(dto);
    }
}
