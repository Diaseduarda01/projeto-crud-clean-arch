# Vampiros Project API

API RESTful para gerenciamento de vampiros, construída com **Spring Boot 4** e **Clean Architecture**.

## Tecnologias

- Java 21
- Spring Boot 4
- Spring Data JPA
- MySQL 8 (produção) / H2 (testes)
- Maven
- Docker / Docker Compose

## Arquitetura

O projeto segue os princípios da Clean Architecture, separando responsabilidades em camadas:

```
src/main/java/gerenciamento/vampiro/api/
├── core/
│   ├── domain/
│   │   └── vampiro/
│   │       ├── Vampiro.java               # Entidade de domínio
│   │       ├── valueObject/               # Value objects (Especie, Idade, Status)
│   │       └── exception/                 # Exceções de domínio
│   └── application/
│       ├── usecase/vampiro/               # Casos de uso (Cadastrar, Atualizar, Deletar, Listar)
│       └── repository/vampiro/            # Interfaces de repositório (ports)
└── infra/
    ├── persistence/jpa/                   # Adaptadores JPA (adapters)
    ├── web/rest/                          # Controller e GlobalExceptionHandler
    └── config/                            # Configuração de beans
```

## Endpoints

| Método | Endpoint          | Descrição                  | Status de sucesso |
|--------|-------------------|----------------------------|-------------------|
| POST   | `/vampiros`       | Cadastrar vampiro           | 201 Created       |
| GET    | `/vampiros`       | Listar todos os vampiros    | 200 OK / 204      |
| GET    | `/vampiros/{id}`  | Buscar vampiro por ID       | 200 OK            |
| PUT    | `/vampiros/{id}`  | Atualizar vampiro           | 200 OK            |
| DELETE | `/vampiros/{id}`  | Deletar vampiro             | 200 OK            |

### Exemplo de corpo da requisição (POST / PUT)

```json
{
  "nome": "Drácula",
  "idade": 500,
  "especie": "VAMPIRO",
  "status": "ATIVO",
  "cidade": "Transilvânia",
  "estaCompelido": false
}
```

### Valores válidos

- **especie**: `VAMPIRO`, `HIBRIDO`, `ORIGINAL`
- **status**: `ATIVO`, `ADORMECIDO`, `MORTO`
- **idade**: número inteiro positivo (> 0)

### Exemplo de resposta (201 / 200)

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "nome": "Drácula",
  "idade": 500,
  "especie": "VAMPIRO",
  "status": "ATIVO",
  "cidade": "Transilvânia",
  "estaCompelido": false
}
```

### Erros

```json
{
  "status": 400,
  "message": "Espécie: HUMANO inválida!",
  "timestamp": "2026-05-04T14:00:00"
}
```

## Como executar

### Com Docker Compose (recomendado)

```bash
# Gerar o jar
./mvnw clean package -DskipTests

# Subir banco e API
docker compose up
```

A API estará disponível em `http://localhost:8080`.

### Apenas o banco de dados

```bash
docker compose up db
```

E configure as variáveis de ambiente para rodar a API localmente:

```bash
DB_HOST=localhost DB_PORT=3306 DB_NAME=vampiros_db \
DB_USER=vampiro_user DB_PASS=vampiro_pass \
./mvnw spring-boot:run
```

## Testes

```bash
# Todos os testes (unitários + integração)
./mvnw test
```

Os testes de integração usam H2 em memória e não precisam de banco externo.
