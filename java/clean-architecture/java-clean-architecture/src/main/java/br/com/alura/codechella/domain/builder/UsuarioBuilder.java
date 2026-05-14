package br.com.alura.codechella.domain.builder;

import br.com.alura.codechella.domain.entity.usuario.Usuario;
import br.com.alura.codechella.domain.valueobject.Endereco;

import java.time.LocalDate;

public class UsuarioBuilder {

    private Usuario usuario;

    public UsuarioBuilder comNomeCpfNascimento(String nome, String cpf, LocalDate nascimento) {
        this.usuario = new Usuario(null, cpf, nome, nascimento, "");
        return this;
    }

    public UsuarioBuilder incluiEndereco(String cep, Integer numero, String complemento) {
        this.usuario.setEndereco(new Endereco(cep, numero, complemento));
        return this;
    }

    public Usuario build() {
        return usuario;
    }
}
