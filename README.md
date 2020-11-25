# API Controle de Estoque

API Rest para fazer controle de estoque.

#### Instruções de execução
Api foi desenvolvida utilizando JAVA 11 e Maven. Para rodar através do console basta executar o comando na raiz do projeto:
```
mvn spring-boot:run
```

#### Documentação da API
Está sendo utilizado o Swagger para documentar esta API. Para acessar, basta abrir o seguinte link com o projeto rodando:
```
http://localhost:8080/swagger-ui.html
```

#### Banco de dados
Está sendo utilizado o banco H2 em memória. Toda vez ao desligar a aplicação, o banco é zerado.

#### Utilizando a API
Endpoints relacionados ao CRUD do produto

###### Inserir um produto
```
POST http://localhost:8080/api/produto

Body:
{
  "descricao": "Notebook",
  "tipo": "ELETRONICO",
  "valorFornecedor": 10
}
```

###### Buscar todos os produtos
```
GET http://localhost:8080/api/produto
```

###### Buscar um produto
```
GET http://localhost:8080/api/produto/{idProduto}
```

###### OBS: Ao acessar o Swagger terá todos os exemplos e a possibilidade de executar.

