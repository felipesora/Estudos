package br.com.alura.codechella.presentation.request;

import java.time.LocalDate;

public record CreateUsuarioRequest(
        String cpf,

        String nome,

        LocalDate nascimento,

        String email
) {
}
