from langchain_ollama import ChatOllama
from langchain_classic.memory import ConversationSummaryMemory
from langchain_classic.chains import ConversationChain


# 1. Configurando o modelo local do Ollama
llm = ChatOllama(
    model="qwen3:4b",
    base_url="http://localhost:11434"
)


# 2. Criando a memória de resumo
#
# A ConversationSummaryMemory utiliza o próprio LLM para resumir
# o histórico da conversa à medida que novas interações acontecem.
memory = ConversationSummaryMemory(
    llm=llm,
    return_messages=True
)


# 3. Criando a conversa usando a memória
conversation = ConversationChain(
    llm=llm,
    memory=memory,
    verbose=True
)


# 4. Primeira interação
resposta = conversation.invoke(
    "Meu nome é Felipe e estou estudando inteligência artificial."
)

print("Resposta 1:")
print(resposta["response"])


# 5. Segunda interação
resposta = conversation.invoke(
    "Estou estudando Python, LangChain e desenvolvimento de aplicações com LLMs."
)

print("\nResposta 2:")
print(resposta["response"])


# 6. Terceira interação
resposta = conversation.invoke(
    "Para meus estudos, estou usando Ollama com o modelo Qwen 3 4B localmente."
)

print("\nResposta 3:")
print(resposta["response"])


# 7. Quarta interação
resposta = conversation.invoke(
    "Meu objetivo é aprender a construir aplicações de inteligência artificial para desenvolvedores."
)

print("\nResposta 4:")
print(resposta["response"])


# 8. Visualizando o resumo atual da memória
print("\nResumo atual da memória:")
print(memory.load_memory_variables({}))
