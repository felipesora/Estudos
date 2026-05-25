package br.com.alura.codechella.vendas.domain.evento;

public interface RepositorioDeEvento {

    Evento buscarEventoPorCidade(Endereco cep);
}
