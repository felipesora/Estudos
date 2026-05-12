package br.com.felipe.spring_boot_essentials.controller;

import br.com.felipe.spring_boot_essentials.dto.AvaliacaoFisicaDto;
import br.com.felipe.spring_boot_essentials.dto.AvaliacoesFisicasProjection;
import br.com.felipe.spring_boot_essentials.service.AvaliacoesFisicasService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/avaliacoes")
@RequiredArgsConstructor
@Validated
public class AvaliacoesFisicasController {

    private final AvaliacoesFisicasService avaliacoesFisicasService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAvaliacaoFisica(@Valid @RequestBody AvaliacaoFisicaDto avaliacaoFisicaDto) {
        avaliacoesFisicasService.criarAvaliacaoFisica(avaliacaoFisicaDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AvaliacoesFisicasProjection> getAllAvaliacoes() {
        return avaliacoesFisicasService.getAllAvaliacoes();
    }

    @GetMapping("/page/{page}/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public Page<AvaliacoesFisicasProjection> getAllAvaliacoes(@PathVariable Integer page,
                                                              @PathVariable Integer size) {
        return avaliacoesFisicasService.getAllAvaliacoes(page, size);
    }
}
