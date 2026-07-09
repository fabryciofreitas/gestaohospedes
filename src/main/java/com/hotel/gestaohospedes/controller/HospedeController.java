package com.hotel.gestaohospedes.controller;

import com.hotel.gestaohospedes.records.*;
import com.hotel.gestaohospedes.services.HospedeService;
import com.hotel.gestaohospedes.services.CheckInService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospedes")
@RequiredArgsConstructor
public class HospedeController {
    private final HospedeService hospedeService;
    private final CheckInService checkInService;
    @PostMapping
    public ResponseEntity<HospedeResponseDTO> cadastrar(@Valid @RequestBody HospedeRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hospedeService.cadastrar(dto));
    }
    @GetMapping
    public Page<HospedeResponseDTO> buscar(@RequestParam(required = false) String busca, Pageable pageable) {
        return hospedeService.buscar(busca, pageable);
    }
    @GetMapping("/{id}")
    public HospedeResponseDTO buscarPorId(@PathVariable Long id) {
        return hospedeService.buscarPorId(id);
    }
    @PutMapping("/{id}")
    public HospedeResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody HospedeRequestDTO dto) {
        return hospedeService.atualizar(id, dto);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<HospedeResponseDTO> remover(@PathVariable Long id) {
        hospedeService.remover(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/resumo-financeiro")
    public ResumoFinanceiroDTO resumoFinanceiro(@PathVariable Long id) {
        return checkInService.resumoFinanceiroDoHospede(id);
    }
}
