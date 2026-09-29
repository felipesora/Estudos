## 1. Ambiente virtual
No Windows:

```
python -m venv .venv
```

Ativar:
```
.venv\Scripts\activate
```

Depois:
```
python --version
pip --version
```

E atualizar o pip:
```
python -m pip install --upgrade pip
```

## 2. `requirements.txt`

Começaria propositalmente com poucas dependências:

```txt
langchain
langchain-ollama
jupyter
ipykernel
python-dotenv
```

Instalação:
```
pip install -r requirements.txt
```

E podemos registrar o ambiente como kernel do Jupyter:
```
python -m ipykernel install --user --name langchain-ollama --display-name "Python (LangChain + Ollama)"
```

## 3. Ollama com Docker

Subir container:
```
docker compose up -d
```

Depois precisamos baixar o modelo dentro do container:
```
docker exec -it langchain-ollama ollama pull qwen3:4b
```

E podemos verificar:
```
docker exec -it langchain-ollama ollama list
```

Deve aparecer algo semelhante a:
```txt
NAME       SIZE
qwen3:4b   ...
```