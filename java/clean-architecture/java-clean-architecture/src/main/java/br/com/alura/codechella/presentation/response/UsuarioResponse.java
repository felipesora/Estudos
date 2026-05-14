package br.com.alura.codechella.presentation.response;

import java.time.LocalDate;

public record UsuarioResponse(
        Long id,

        String cpf,

        String nome,

        LocalDate nascimento,

        String email
) {
}
