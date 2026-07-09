package com.hotel.gestaohospedes.services;

import com.hotel.gestaohospedes.entity.Usuario;
import com.hotel.gestaohospedes.exception.RegraDeNegocioException;
import com.hotel.gestaohospedes.records.LoginRequestDTO;
import com.hotel.gestaohospedes.records.LoginResponseDTO;
import com.hotel.gestaohospedes.repository.UsuarioRepository;
import com.hotel.gestaohospedes.security.GeradorToken;
import com.hotel.gestaohospedes.security.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final GeradorToken geradorToken;

    public LoginResponseDTO autenticar(LoginRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByNomeUsuario(dto.nomeUsuario())
                .orElseThrow(() -> new RegraDeNegocioException("Usuário ou senha inválidos."));

        if (!passwordEncoder.matches(dto.senha(), usuario.getSenha())) {
            throw new RegraDeNegocioException("Usuário ou senha inválidos.");
        }

        Token token = geradorToken.gerar(usuario.getNomeUsuario(), usuario.getPerfil());

        return new LoginResponseDTO(token, usuario.getPerfil().name(), token.expiracao());
    }
}
