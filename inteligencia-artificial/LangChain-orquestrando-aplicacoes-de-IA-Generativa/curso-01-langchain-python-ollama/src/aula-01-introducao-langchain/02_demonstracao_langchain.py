from langchain_ollama import ChatOllama

llm = ChatOllama(
    model="qwen3:4b",
    base_url="http://localhost:11434"
)

prompt = "Explique o que é a inteligência artificial em poucas palavras"

response = llm.invoke(prompt)

print(response.content)