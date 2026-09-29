from langchain_ollama import ChatOllama
from langchain_core.prompts import PromptTemplate
from langchain.chains import LLMChain
from langchain.chains import SimpleSequentialChain


llm = ChatOllama(
    model="qwen3:4b",
    base_url="http://localhost:11434"
)


prompt_explicacao = PromptTemplate.from_template(
    "Explique o conceito de {conceito} para um estudante iniciante de tecnologia."
)

chain_explicacao = LLMChain(
    llm=llm,
    prompt=prompt_explicacao
)


prompt_resumo = PromptTemplate.from_template(
    "Resuma a explicação abaixo em uma frase simples:\n\n{explicacao}"
)

chain_resumo = LLMChain(
    llm=llm,
    prompt=prompt_resumo
)


chain = SimpleSequentialChain(
    chains=[chain_explicacao, chain_resumo],
    verbose=True
)


resultado = chain.invoke("RAG")

print("\nResultado final:")
print(resultado["output"])
