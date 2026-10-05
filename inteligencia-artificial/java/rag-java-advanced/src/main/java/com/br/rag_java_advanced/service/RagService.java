package com.br.rag_java_advanced.service;

import com.br.rag_java_advanced.dto.QuestionRequest;
import com.br.rag_java_advanced.dto.QuestionResponse;
import com.br.rag_java_advanced.dto.SourceResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RagService {

    private final ChatClient chatClient;
    private final RetrievalService retrievalService;

    public RagService(ChatClient chatClient, RetrievalService retrievalService) {
        this.chatClient = chatClient;
        this.retrievalService = retrievalService;
    }

    public QuestionResponse answer(QuestionRequest request) {
        int topK = request.topK() != null
                ? request.topK()
                : 5;

        List<Document> documents = retrievalService.search(request.question(), topK);

        if (documents.isEmpty()) {
            return new QuestionResponse("Não encontrei informações relevantes na base de conhecimento.", List.of());
        }

        String context = documents.stream()
                .map(Document::getText)
                .reduce(
                        "",
                        (current, text) -> current + "\n\n" + text
                );

        String prompt = """
                Você é um assistente especializado em responder
                perguntas utilizando uma base de conhecimento.
                
                REGRAS:

                1. Responda utilizando somente o CONTEXTO.
                2. Não invente informações.
                3. Se a resposta não estiver no contexto,
                   diga que a informação não está disponível.
                4. Seja objetivo.
                5. Não mencione que você é uma IA.

                CONTEXTO:

                %s

                PERGUNTA:

                %s
                """.formatted(context, request.question());

        String answer = chatClient
                .prompt()
                .user(prompt)
                .call()
                .content();

        List<SourceResponse> sources = documents.stream()
                .map(document -> new SourceResponse(
                        (String) document.getMetadata().get("source"),
                        document.getMetadata(),
                        extractScore(document),
                        document.getText()
                )).toList();

        return new QuestionResponse(answer, sources);
    }

    private Double extractScore(Document document) {
        Object score = document.getMetadata().get("distance");

        if (score instanceof Number number) {
            return number.doubleValue();
        }

        score =
                document.getMetadata().get("score");

        if (score instanceof Number number) {
            return number.doubleValue();
        }

        return null;
    }
}
