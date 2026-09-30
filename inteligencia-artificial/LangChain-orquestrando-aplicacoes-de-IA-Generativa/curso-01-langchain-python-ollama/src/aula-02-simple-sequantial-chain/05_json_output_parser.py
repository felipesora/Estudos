from langchain_ollama import ChatOllama
from langchain_core.prompts import PromptTemplate
from langchain_core.output_parsers import JsonOutputParser


llm = ChatOllama(
    model="qwen3:4b",
    base_url="http://localhost:11434",
    format="json"
)


parser = JsonOutputParser()


prompt = PromptTemplate.from_template(
    """
    Analise o conceito abaixo e responda em formato JSON.

    Conceito: {conceito}

    O JSON deve conter exatamente estas propriedades:
    - conceito: nome do conceito
    - definicao: uma definição curta
    - nivel: nível de dificuldade (iniciante, intermediário ou avançado)
    - exemplo: um exemplo prático

    Retorne somente o JSON, sem markdown.
    """
)


chain = prompt | llm | parser


resultado = chain.invoke({
    "conceito": "RAG"
})


print("Resultado:")
print(resultado)

print("\nTipo do resultado:")
print(type(resultado))

print("\nDefinição:")
print(resultado["definicao"])
