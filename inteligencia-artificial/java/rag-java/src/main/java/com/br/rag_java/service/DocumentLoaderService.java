package com.br.rag_java.service;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class DocumentLoaderService {

    private final VectorStore vectorStore;

    public DocumentLoaderService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void loadDocument() throws IOException{
        ClassPathResource resource = new ClassPathResource("documentos/nexa-tech.txt");

        String content = resource.getContentAsString(StandardCharsets.UTF_8);

        Document document = new Document(content);

        TokenTextSplitter splitter = TokenTextSplitter.builder()
                .withChunkSize(300)
                .withMinChunkSizeChars(100)
                .withMinChunkLengthToEmbed(5)
                .withMaxNumChunks(10000)
                .withKeepSeparator(true)
                .build();

        List<Document> chunks = splitter.apply(List.of(document));

        vectorStore.add(chunks);

        System.out.println("Documento carregado. Chunks: " + chunks.size());
    }
}
