package com.br.rag_java.controller;

import com.br.rag_java.dto.PerguntaRequest;
import com.br.rag_java.dto.PerguntaResponse;
import com.br.rag_java.service.RagService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/perguntas")
public class PerguntaController {

    private final RagService ragService;

    public PerguntaController(RagService ragService) {
        this.ragService = ragService;
    }

    @PostMapping
    public PerguntaResponse perguntar(@RequestBody PerguntaRequest request){
        String resposta = ragService.responder(request.pergunta());
        return new PerguntaResponse(resposta);
    }
}
