package com.br.rag_java_advanced.dto;

import java.util.List;

public record QuestionResponse(

        String answer,

        List<SourceResponse> sources

) {
}
