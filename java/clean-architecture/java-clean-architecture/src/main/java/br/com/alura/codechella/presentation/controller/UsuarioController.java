package br.com.alura.codechella.presentation.controller;

import br.com.alura.codechella.application.usecase.CriarUsuarioUseCase;
import br.com.alura.codechella.application.usecase.ListarUsuariosUseCase;
import br.com.alura.codechella.domain.entity.usuario.Usuario;
import br.com.alura.codechella.presentation.request.CreateUsuarioRequest;
import br.com.alura.codechella.presentation.response.UsuarioResponse;
import br.com.alura.codechella.presentation.mapper.UsuarioControllerMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final ListarUsuariosUseCase listarUsuariosUseCase;
    private final UsuarioControllerMapper mapper;

    public UsuarioController(CriarUsuarioUseCase criarUsuarioUseCase, ListarUsuariosUseCase listarUsuariosUseCase,  UsuarioControllerMapper mapper) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
        this.listarUsuariosUseCase = listarUsuariosUseCase;
        this.mapper = mapper;
    }

    @GetMapping
    public List<UsuarioResponse> listarTodosUsuarios() {
        return listarUsuariosUseCase.obterTodosUsuarios()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @PostMapping
    public UsuarioResponse cadastrarUsuario(@RequestBody CreateUsuarioRequest request) {
        Usuario usuario = mapper.toDomain(request);
        Usuario salvo = criarUsuarioUseCase.cadastrarUsuario(usuario);
        return mapper.toResponse(salvo);
    }
}
