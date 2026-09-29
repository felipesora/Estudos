from langchain_ollama import ChatOllama
from langchain_core.prompts import PromptTemplate


llm = ChatOllama(
    model="qwen3:4b",
    base_url="http://localhost:11434"
)


prompt_explicacao = PromptTemplate.from_template(
    "Explique o conceito de {conceito} para um estudante iniciante de tecnologia."
)


prompt_resumo = PromptTemplate.from_template(
    "Resuma a explicação abaixo em uma frase simples:\n\n{explicacao}"
)


chain_explicacao = prompt_explicacao | llm


chain_resumo = prompt_resumo | llm


chain = (
    {"explicacao": chain_explicacao}
    | prompt_resumo
    | llm
)


resultado = chain.invoke({
    "conceito": "RAG"
})


print("\nResultado final:")
print(resultado.content)
