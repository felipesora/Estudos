package com.br.rag_java_advanced.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Map;

public record DocumentRequest(

        @NotBlank
        String content,

        @NotBlank
        String source,

        String category,

        String department,

        String documentType,

        Integer year,

        Map<String, Object> metadata
) {
}
