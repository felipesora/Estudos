from langchain_ollama import ChatOllama
from langchain_classic.memory import ConversationBufferWindowMemory
from langchain_classic.chains import ConversationChain


# 1. Configurando o modelo local do Ollama
llm = ChatOllama(
    model="qwen3:4b",
    base_url="http://localhost:11434"
)


# 2. Criando uma memória com janela de 2 interações
#
# k=2 significa que a memória mantém as 2 interações mais recentes
# da conversa.
memory = ConversationBufferWindowMemory(
    k=2,
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
    "Estou estudando Python e LangChain."
)

print("\nResposta 2:")
print(resposta["response"])


# 6. Terceira interação
resposta = conversation.invoke(
    "Também estou aprendendo a usar o Ollama."
)

print("\nResposta 3:")
print(resposta["response"])


# 7. Quarta interação
#
# Como k=2, as interações mais antigas começam a sair da janela.
resposta = conversation.invoke(
    "Qual tecnologia local estou usando para executar o modelo?"
)

print("\nResposta 4:")
print(resposta["response"])


# 8. Visualizando o conteúdo atual da memória
print("\nMemória atual:")
print(memory.load_memory_variables({}))
