package com.br.rag_java_advanced.controller;

import com.br.rag_java_advanced.dto.DocumentRequest;
import com.br.rag_java_advanced.service.DocumentIngestionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/documents")
public class DocumentController {

    private final DocumentIngestionService documentIngestionService;

    public DocumentController(DocumentIngestionService documentIngestionService) {
        this.documentIngestionService = documentIngestionService;
    }

    @PostMapping
    public ResponseEntity<?> ingest(@Valid @RequestBody DocumentRequest request) {
        int chunks = documentIngestionService.ingest(request);

        return ResponseEntity.ok(
                Map.of(
                        "message", "Documento processado com sucesso",
                        "chunks", chunks
                )
        );
    }
}
