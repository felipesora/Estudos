from pathlib import Path

from langchain_ollama import ChatOllama, OllamaEmbeddings
from langchain_text_splitters import RecursiveCharacterTextSplitter
from langchain_core.documents import Document
from langchain_core.prompts import PromptTemplate
from langchain_core.output_parsers import StrOutputParser
from langchain_core.runnables import RunnablePassthrough
from langchain_chroma import Chroma

# 1. Carregar a base
base_path = Path("nexa_ai_base.txt")
texto = base_path.read_text(encoding="utf-8")

documento = Document(
    page_content=texto,
    metadata={"source": base_path.name}
)

# 2. Dividir em chunks
splitter = RecursiveCharacterTextSplitter(
    chunk_size=500,
    chunk_overlap=80
)

documentos = splitter.split_documents([documento])

print("Quantidade de chunks:", len(documentos))
print("\nPrimeiro chunk:")
print(documentos[0].page_content)

# 3. Modelo de embeddings local
# Antes de executar:
# docker exec -it langchain-ollama ollama pull nomic-embed-text

embeddings = OllamaEmbeddings(
    model="nomic-embed-text",
    base_url="http://localhost:11434"
)

# 4. Gerar um embedding
texto_teste = "Como a Nexa AI ajuda a organizar tarefas?"
vetor = embeddings.embed_query(texto_teste)

print("\nTamanho do vetor:", len(vetor))
print("Primeiros valores:", vetor[:10])

# 5. Criar Vector Store
vector_store = Chroma.from_documents(
    documents=documentos,
    embedding=embeddings,
    collection_name="nexa-ai-estudo"
)

# 6. Criar Retriever
retriever = vector_store.as_retriever(
    search_kwargs={"k": 3}
)

# 7. Testar Retrieval
pergunta = "O que a Central IA pode fazer?"
documentos_relevantes = retriever.invoke(pergunta)

print("\nDocumentos recuperados:")
for i, doc in enumerate(documentos_relevantes, start=1):
    print(f"\n--- Chunk {i} ---")
    print(doc.page_content)

# 8. Conectar Retrieval ao Qwen
llm = ChatOllama(
    model="qwen3:4b",
    base_url="http://localhost:11434"
)

prompt = PromptTemplate.from_template(
    """
    Responda à pergunta utilizando somente o contexto fornecido.

    Se a resposta não estiver no contexto, diga que a informação
    não está disponível na base de conhecimento.

    Contexto:
    {context}

    Pergunta:
    {question}
    """
)

def format_documents(docs):
    return "\n\n".join(doc.page_content for doc in docs)

chain = (
    {
        "context": retriever | format_documents,
        "question": RunnablePassthrough()
    }
    | prompt
    | llm
    | StrOutputParser()
)

# 9. Fazer perguntas usando Retrieval + LLM
for pergunta in [
    "Quais funcionalidades existem na Central IA?",
    "Qual banco de dados a Nexa AI utiliza?"
]:
    resposta = chain.invoke(pergunta)

    print("\nPergunta:", pergunta)
    print("Resposta:", resposta)
