package com.hotel.gestaohospedes.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class TratadorGlobalDeExceptions {

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<ErroRespostaDTO> tratar(NaoEncontradoException ex, HttpServletRequest req) {
        return construirResposta(HttpStatus.NOT_FOUND, ex.getMessage(), req);
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<ErroRespostaDTO> tratar(RegraDeNegocioException ex, HttpServletRequest req) {
        return construirResposta(HttpStatus.UNPROCESSABLE_CONTENT, ex.getMessage(), req);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErroRespostaDTO> tratar(AccessDeniedException ex, HttpServletRequest req) {
        return construirResposta(HttpStatus.FORBIDDEN, "Você não tem permissão para executar esta ação.", req);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroRespostaDTO> tratar(MethodArgumentNotValidException ex, HttpServletRequest req) {
        String mensagem = ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .reduce((a, b) -> a + " | " + b)
                .orElse("Dados inválidos.");
        return construirResposta(HttpStatus.BAD_REQUEST, mensagem, req);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroRespostaDTO> tratar(Exception ex, HttpServletRequest req) {
        return construirResposta(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno inesperado.", req);
    }

    private ResponseEntity<ErroRespostaDTO> construirResposta(HttpStatus status, String mensagem, HttpServletRequest req) {
        ErroRespostaDTO corpo = new ErroRespostaDTO(LocalDateTime.now(), status.value(), mensagem,
                req.getRequestURI());
        return ResponseEntity.status(status).body(corpo);
    }
}
