package com.br.rag_java_advanced.dto;

import jakarta.validation.constraints.NotBlank;

public record QuestionRequest(

        @NotBlank
        String question,

        String category,

        String department,

        Integer year,

        Integer topK,

        Double similarityThreshold
) {
}
