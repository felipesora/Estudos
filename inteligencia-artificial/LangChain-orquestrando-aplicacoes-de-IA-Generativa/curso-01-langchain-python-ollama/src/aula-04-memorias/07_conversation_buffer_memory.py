from langchain_ollama import ChatOllama
from langchain_classic.memory import ConversationBufferMemory
from langchain_classic.chains import ConversationChain


llm = ChatOllama(
    model="qwen3:4b",
    base_url="http://localhost:11434"
)

memory = ConversationBufferMemory(return_messages=True)

conversation = ConversationChain(
    llm=llm,
    memory=memory,
    verbose=True
)

resposta = conversation.invoke(
    "Meu nome é Felipe e estou estudando inteligência artificial."
)

print("Resposta 1:")
print(resposta["response"])

resposta = conversation.invoke(
    "O que eu estou estudando?"
)

print("\nResposta 2:")
print(resposta["response"])

print(memory.load_memory_variables({}))