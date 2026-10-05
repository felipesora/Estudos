package com.br.rag_java_advanced.controller;

import com.br.rag_java_advanced.dto.QuestionRequest;
import com.br.rag_java_advanced.dto.QuestionResponse;
import com.br.rag_java_advanced.service.RagService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final RagService ragService;

    public QuestionController(RagService ragService) {
        this.ragService = ragService;
    }

    @PostMapping
    public ResponseEntity<QuestionResponse> question(@Valid @RequestBody QuestionRequest request) {

        QuestionResponse response = ragService.answer(request);
        return ResponseEntity.ok(response);
    }
}
