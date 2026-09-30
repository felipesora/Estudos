from langchain_ollama import ChatOllama
from langchain_core.tools import tool
from langchain_core.messages import HumanMessage
from langgraph.prebuilt import create_react_agent


# 1. Modelo local
llm = ChatOllama(
    model="qwen3:4b",
    base_url="http://localhost:11434"
)


# 2. Tools

@tool
def consultar_horario() -> str:
    """Consulta o horário atual do sistema."""
    from datetime import datetime
    horario = datetime.now().strftime("%H:%M:%S")
    return f"O horário atual é {horario}."


@tool
def criar_tarefa(titulo: str) -> str:
    """Cria uma tarefa com o título informado pelo usuário."""
    return f'Tarefa "{titulo}" criada com sucesso.'


@tool
def listar_tarefas() -> str:
    """Lista as tarefas cadastradas no sistema."""
    tarefas = [
        "Estudar LangChain",
        "Estudar agentes",
        "Criar projeto com Ollama"
    ]
    return "Tarefas cadastradas:\n- " + "\n- ".join(tarefas)


tools = [consultar_horario, criar_tarefa, listar_tarefas]


# 3. Agente
agent = create_react_agent(
    model=llm,
    tools=tools
)


# 4. Função de consulta
def perguntar(pergunta: str):
    resultado = agent.invoke({
        "messages": [HumanMessage(content=pergunta)]
    })
    return resultado["messages"][-1].content


# 5. Testes
perguntas = [
    "Que horas são?",
    "Crie uma tarefa chamada estudar agentes de IA.",
    "Quais tarefas eu tenho?"
]

for pergunta in perguntas:
    print("\n========================================")
    print("Pergunta:", pergunta)
    print("Resposta:", perguntar(pergunta))
