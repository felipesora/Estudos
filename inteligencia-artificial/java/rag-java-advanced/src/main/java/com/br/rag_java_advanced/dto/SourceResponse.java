package com.br.rag_java_advanced.dto;

import java.util.Map;

public record SourceResponse(

        String source,

        Map<String, Object> metadata,

        Double score,

        String content
) {
}
