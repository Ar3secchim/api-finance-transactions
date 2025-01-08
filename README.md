> # Teste para Desenvolvedor Junior (Jr)

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

 1. Spring Boot:

- Configuração do projeto simplificada.
- Desenvolvimento rápido e eficiente de APIs RESTful.
- Integração com Spring Data JPA para persistência.

 2. JPA:

- Para mapeamento das entidades do banco de dados.
- Redução de complexidade no gerenciamento dos dados.

 3. Flyway:

- Controle de versão do banco de dados, garantindo consistência nas migrações.

 4. Lombok:

- Redução de boilerplate (getters, setters, construtores, etc.).
- Melhor legibilidade e manutenção do código.

 5. Banco H2:

- Banco em memória para simplificar o desenvolvimento e execução de testes.

### Decisões para Cada Endpoint

1. Criar Usuário

- POST /api/users
- Validação: Verificar idade mínima e CPF único antes de persistir o usuário.
- Banco de Dados: JPA gerenciará a persistência com validações usando anotações.
- Lombok: Utilizado para criar getters e setters automáticos nas entidades.
- Tratamento de Erros: Retornar 400 se CPF for duplicado ou idade for inválida.
- Flyway: Criar tabelas e constraints via script de migration.

Consultar Usuário

- GET /api/users/{id} e /api/users
- Banco de Dados: JPA realizará as consultas usando métodos derivados do Spring Data.
- Tratamento de Erros: Retornar 404 se o ID não existir.
- DTOs: Utilizar objetos DTO para formatar a resposta.

Transferir Dinheiro

- POST /api/transactions
- Validação: Verificar se ambas as contas existem e se o saldo do remetente é suficiente.
- Banco de Dados: Atualização de saldo será feita em transação controlada pelo Spring.
- Tratamento de Erros:
- 404: Conta não encontrada.
- 400: Saldo insuficiente ou requisição inválida.

### Regras de Negócio

1. **Cadastro de Usuário:**
   - O usuário deve ter nome, idade, CPF e um número de conta gerado automaticamente.
   - Apenas usuários com 18 anos ou mais podem ser cadastrados.
   - O CPF deve ser único para cada usuário.

2. **Consulta de Usuário:**
   - Permitir buscar um usuário por id ou listar todos os usuários.
   - A resposta deve conter nome, idade, CPF, número da conta e saldo.

3. **Transferências:**
   - Transferências só podem ser realizadas entre contas existentes.
   - O saldo do remetente deve ser maior ou igual ao valor transferido.
   - Caso uma das contas não exista, retornar erro com status 404.

4. **Tratamento de Erros:**
   - Retornar mensagens claras e status HTTP apropriados para entradas inválidas.
   - Exemplos:
     - 400: Requisição inválida (ex.: CPF duplicado ou saldo insuficiente).
     - 404: Conta ou usuário não encontrado.

## Endpoints

### Criar Usuário

``POST /api/users``

**Corpo da Requisição:**

```json
{
  "nome": "João Silva",
  "idade": 25,
  "cpf": "123.456.789-10"
}
```

**Resposta de Sucesso:**

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

**Resposta de Erro:**

```json
{
  "error": "CPF já cadastrado."
}
```

### Transferir Dinheiro

``POST /api/transactions``

**Corpo da Requisição:**

```json
{
  "contaOrigem": "000123",
  "contaDestino": "000456",
  "valor": 100.00
}
```

**Resposta de Sucesso:**

```json
{
  "mensagem": "Transferência realizada com sucesso."
}
```

**Erro (Saldo Insuficiente):**

```json
{
  "error": "Saldo insuficiente para a transação."
}
```

### Consultar Usuário

``GET /api/users/{id}``

**Resposta de Sucesso:**

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
