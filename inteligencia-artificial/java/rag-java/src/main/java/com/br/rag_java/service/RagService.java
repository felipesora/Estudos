package com.br.rag_java.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RagService {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public RagService(ChatClient chatClient, VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    public String responder(String pergunta) {
        List<Document> documentos = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(pergunta)
                        .topK(3)
                        .build());

        String contexto = documentos.stream()
                .map(Document::getText)
                .reduce("", (atual, texto) -> atual + "\n\n" + texto);

        String prompt = """
                Você é um assistente da empresa NexaTech.
                
                Responda à pergunta do usuário utilizando exclusivamente as informações
                presentes no contexto.
                
                Se a resposta não estiver no contexto, diga claramente que
                a informação não está disponível na base de conhecimento.
                
                Não invente informações.
                
                CONTEXTO:
                %s
                
                PERGUNTA:
                %s
                """.formatted(contexto, pergunta);

        return chatClient
                .prompt()
                .user(prompt)
                .call()
                .content();
    }
}
