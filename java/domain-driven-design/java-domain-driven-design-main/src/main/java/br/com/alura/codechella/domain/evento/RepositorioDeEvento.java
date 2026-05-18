package br.com.alura.codechella.domain.evento;

public interface RepositorioDeEvento {

    Evento buscarEventoPorCidade(Endereco cep);
}
