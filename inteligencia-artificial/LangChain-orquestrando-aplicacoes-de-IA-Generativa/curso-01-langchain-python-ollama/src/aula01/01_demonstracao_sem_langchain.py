from ollama import Client

client = Client(host="http://localhost:11434")

prompt = "Explique o que é a inteligência artificial em poucas palavras"

response = client.chat(
    model="qwen3:4b",
    messages=[
        {
            "role": "user",
            "content": prompt
        }
    ]
)

print(response["message"]["content"])