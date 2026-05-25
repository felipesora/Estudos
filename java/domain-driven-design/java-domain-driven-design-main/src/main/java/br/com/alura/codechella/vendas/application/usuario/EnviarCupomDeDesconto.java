package br.com.alura.codechella.vendas.application.usuario;

import br.com.alura.codechella.vendas.infra.usuario.Usuario;

public interface EnviarCupomDeDesconto {

    void enviarPara(Usuario usuario);
}
