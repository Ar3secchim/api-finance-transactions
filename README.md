# Teste para Desenvolvedor Junior (Jr)

## 🚀 Objetivo do Teste

O objetivo deste teste é avaliar suas habilidades de programação, considerando o nível esperado para um desenvolvedor Junior (Jr). Serão analisados:

- Domínio da linguagem Java.
- Aplicação de princípios de POO (Programação Orientada a Objetos).
- Implementação de boas práticas como SOLID, DRY e Clean Code.
- Capacidade de desenvolver APIs RESTful com regras de negócio e tratamento de erros.
- Organização e clareza na documentação e estrutura do código.

Este teste faz parte de um processo seletivo, e a análise levará em conta a senioridade do candidato (Junior), com foco no aprendizado e potencial de crescimento.

## Decisões Técnicas

### Tecnologias Utilizadas

1. **Spring Boot**:

- Configuração do projeto simplificada.
- Desenvolvimento rápido e eficiente de APIs RESTful.
- Integração com Spring Data JPA para persistência.

2. **JPA**:

- Para mapeamento das entidades do banco de dados.
- Redução de complexidade no gerenciamento dos dados.

3. **Flyway**:

- Controle de versão do banco de dados, garantindo consistência nas migrações.

4. **Lombok**:

- Redução de boilerplate (getters, setters, construtores, etc.).
- Melhor legibilidade e manutenção do código.

5. **Docker**:

- Simplificação do ambiente de desenvolvimento e testes.

6. **Banco H2**:

- Banco em memória para simplificar a execução de testes.

### Decisões para Cada Endpoint

1. **Criar Usuário**

- **POST /api/users**
- Validação: Verificar idade mínima e CPF único antes de persistir o usuário.
- Banco de Dados: JPA gerenciará a persistência com validações usando anotações.
- Lombok: Utilizado para criar getters e setters automáticos nas entidades.
- Tratamento de Erros: Retornar 400 se CPF for duplicado ou idade for inválida.
- Flyway: Criar tabelas e constraints via script de migration.

2. **Consultar Usuário**

- **GET /api/users/{id}** e **/api/users**
- Banco de Dados: JPA realizará as consultas usando métodos derivados do Spring Data.
- Tratamento de Erros: Retornar 404 se o ID não existir.
- DTOs: Utilizar objetos DTO para formatar a resposta.

3. **Transferir Dinheiro**

- **POST /api/transactions**
- Validação: Verificar se ambas as contas existem e se o saldo do remetente é suficiente.
- Banco de Dados: Atualização de saldo será feita em transação controlada pelo Spring.
- Tratamento de Erros:
  - 404: Conta não encontrada.
  - 400: Saldo insuficiente ou requisição inválida.

## Endpoints

### Criar Usuário

#### Descrição

Cria um novo usuário no sistema.

**`POST /api/users`**

- **Corpo da Requisição**

  ```json
  {
   "nome": "João Silva",
   "idade": 25,
   "cpf": "123.456.789-10"
  }
  ```

- **Respostas**

  - **Sucesso (201 Created)**:

   ```json
   {
    "id": 1,
    "nome": "João Silva",
    "idade": 25,
    "cpf": "123.456.789-10",
    "numeroConta": "000123",
    "saldo": 0.00
   }
   ```

  - **Erro (400 Bad Request)**:

   ```json
   {
    "error": "CPF já cadastrado."
   }
   ```

   ```json
   {
    "error": "Idade mínima de 18 anos é obrigatória."
   }
   ```

### Consultar Usuário por ID

#### Descrição

Obtém os detalhes de um usuário com base no ID.

**`GET /api/users/{id}`**

- **Parâmetros**
  - `id` (path): ID do usuário.

- **Respostas**
  - **Sucesso (200 OK)**:

   ```json
   {
    "id": 1,
    "nome": "João Silva",
    "idade": 25,
    "cpf": "123.456.789-10",
    "numeroConta": "000123",
    "saldo": 150.00
   }
   ```

  - **Erro (404 Not Found)**:

   ```json
   {
    "error": "Usuário não encontrado."
   }
   ```

### Listar Todos os Usuários

#### Descrição

Obtém a lista de todos os usuários cadastrados no sistema.

**`GET /api/users`**

- **Respostas**
  - **Sucesso (200 OK)**:

   ```json
   [
    {
      "id": 1,
      "nome": "João Silva",
      "idade": 25,
      "cpf": "123.456.789-10",
      "numeroConta": "000123",
      "saldo": 150.00
    },
    {
      "id": 2,
      "nome": "Maria Oliveira",
      "idade": 30,
      "cpf": "987.654.321-00",
      "numeroConta": "000124",
      "saldo": 300.00
    }
   ]
   ```

### Transferir Dinheiro

#### Descrição

Realiza a transferência de dinheiro entre duas contas.

**`POST /api/transactions`**

- **Corpo da Requisição**

  ```json
  {
   "contaOrigem": "000123",
   "contaDestino": "000456",
   "valor": 100.00
  }
  ```

- **Respostas**
  - **Sucesso (200 OK)**:

   ```json
   {
    "mensagem": "Transferência realizada com sucesso."
   }
   ```

  - **Erro (404 Not Found)**:

   ```json
   {
    "error": "Conta de origem ou destino não encontrada."
   }
   ```

  - **Erro (400 Bad Request)**:

   ```json
   {
    "error": "Saldo insuficiente para a transação."
   }
   ```

   ```json
   {
    "error": "Conta de origem e destino não podem ser iguais."
   }
   ```

   ```json
   {
    "error": "Valor da transferência deve ser maior que zero."
   }
   ```

### Consultar Transações

#### Descrição

Lista todas as transações realizadas.

**`GET /api/transactions`**

- **Respostas**
  - **Sucesso (200 OK)**:

   ```json
   [
    {
      "id": "113faa26-7e9c-4eda-b2f6-307fe8864be4",
      "contaOrigem": "000123",
      "contaDestino": "000456",
      "valor": 100.00,
      "atCreated": "2025-01-09T10:00:00Z"
    },
    {
      "id": "223faa26-8e8c-5eda-c3f6-409fe9875ce5",
      "contaOrigem": "000124",
      "contaDestino": "000789",
      "valor": 200.00,
      "atCreated": "2025-01-09T12:00:00Z"
    }
   ]
   ```

  - **Erro (404 Not Found)**:

   ```json
   {
    "error": "Nenhuma transação encontrada."
   }
   ```

## Considerações

- Todos os endpoints retornam respostas em formato JSON.
- Utilize os códigos de status HTTP apropriados para validar a resposta das requisições.
- O tratamento de erros é padronizado e visa fornecer informações claras para o cliente.
