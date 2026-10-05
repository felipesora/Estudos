Suba:
```
docker compose up -d
```

Verifique:
```
docker ps
```

Você deverá ter:
```
rag-postgres
rag-ollama
```

3. Modelos do Ollama
   
Dentro do container:
```
docker exec -it rag-ollama ollama pull qwen3:4b
```

E o modelo de embedding:
```
docker exec -it rag-ollama ollama pull qwen3-embedding:0.6b
```