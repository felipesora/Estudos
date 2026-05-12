package br.com.felipe.spring_boot_essentials.controller;

import br.com.felipe.spring_boot_essentials.dto.TreinoDto;
import br.com.felipe.spring_boot_essentials.service.TreinosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/treinos")
@RequiredArgsConstructor
@Validated
public class TreinosController {

    private final TreinosService treinosService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarTreino(@Valid @RequestBody TreinoDto treinoDto) {
        treinosService.criarTreino(treinoDto);
    }
}
