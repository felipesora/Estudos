package com.br.rag_java_advanced.service;

import com.br.rag_java_advanced.dto.DocumentRequest;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocumentIngestionService {

    private final VectorStore vectorStore;

    public DocumentIngestionService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public int ingest(DocumentRequest request){

        Map<String, Object> metadata = new HashMap<>();

        metadata.put("source", request.source());

        if (request.category() != null) {
            metadata.put("category", request.category());
        }

        if (request.department() != null) {
            metadata.put("department", request.department());
        }

        if (request.documentType() != null) {
            metadata.put("documentType", request.documentType());
        }

        if (request.year() != null) {
            metadata.put("year", request.year());
        }

        if (request.metadata() != null) {
            metadata.putAll(request.metadata());
        }

        Document document = new Document(request.content(), metadata);

        TokenTextSplitter splitter = TokenTextSplitter.builder()
                .withChunkSize(300)
                .withMinChunkSizeChars(100)
                .withMinChunkLengthToEmbed(5)
                .withMaxNumChunks(10000)
                .withKeepSeparator(true)
                .build();

        List<Document> chunks = splitter.apply(List.of(document));

        vectorStore.add(chunks);

        return chunks.size();
    }
}
