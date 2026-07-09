package com.hotel.gestaohospedes.controller;

import com.hotel.gestaohospedes.records.CheckInRequestDTO;
import com.hotel.gestaohospedes.records.CheckInResponseDTO;
import com.hotel.gestaohospedes.services.CheckInService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/checkins")
@RequiredArgsConstructor
public class CheckInController {
    private final CheckInService checkInService;
    @PostMapping
    public ResponseEntity<CheckInResponseDTO> registrarEntrada(@Valid @RequestBody CheckInRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(checkInService.registrarEntrada(dto));
    }
    @PutMapping("/{id}/saida")
    public CheckInResponseDTO registrarSaida(
            @PathVariable Long id,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime
                    dataSaida) {
        return checkInService.registrarSaida(id, dataSaida);
    }
    @GetMapping("/presentes")
    public List<CheckInResponseDTO> presentes() {
        return checkInService.listarHospedesPresentes();
    }
    @GetMapping("/finalizados")
    public List<CheckInResponseDTO> finalizados() {
        return checkInService.listarHospedesQueJaSairam();
    }
}
