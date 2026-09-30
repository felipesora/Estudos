from langchain_ollama import ChatOllama
from langchain_core.prompts import PromptTemplate


llm = ChatOllama(
    model="qwen3:4b",
    base_url="http://localhost:11434"
)


prompt_template = PromptTemplate.from_template(
    "Explique o conceito de {conceito} em poucas palavras."
)


prompt = prompt_template.invoke({
    "conceito": "inteligência artificial"
})


response = llm.invoke(prompt)

print(response.content)
