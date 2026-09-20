# JobFlow

Sistema de autenticação e gestão de vagas em Java com Spring Boot, composto por dois microsserviços:

- `auth-service`: autenticação, login, geração e validação de JWT
- `job-service`: cadastro e gestão de vagas e candidaturas

## Visão geral

O projeto foi estruturado em microsserviços para separar responsabilidades de autenticação e processos de vagas. A comunicação entre os serviços é baseada em autenticação via JWT, com segurança configurada em cada módulo.

## Stack

- Java 21
- Spring Boot 4.1.1
- Maven
- PostgreSQL
- JWT (jjwt)

## Estrutura do projeto

```text
jobflow-main/
├── auth-service/
│   ├── src/
│   ├── pom.xml
│   └── mvnw
├── job-service/
│   ├── src/
│   ├── pom.xml
│   └── mvnw
├── .gitignore
├── README.md
└── .github/
```

## Requisitos

- JDK 21+
- Maven 3.9+
- PostgreSQL 14+
- Git

## Banco de dados

Crie os bancos locais:

```sql
CREATE DATABASE jobflow_auth;
CREATE DATABASE job_service;
```

As credenciais configuradas no projeto usam o usuário `postgres`, e a senha definida em `application.properties`.

## Configuração dos serviços

### 1) auth-service

```bash
cd auth-service
./mvnw clean install
./mvnw spring-boot:run
```

Aplicação disponível em:

- http://localhost:8080

### 2) job-service

```bash
cd job-service
./mvnw clean install
./mvnw spring-boot:run
```

Aplicação disponível em:

- http://localhost:8081

## Variáveis e configuração

Os arquivos de configuração atuais estão em:

- `auth-service/src/main/resources/application.properties`
- `job-service/src/main/resources/application.properties`

Esses arquivos definem:

- porta do serviço
- URL do banco PostgreSQL
- usuário e senha do banco
- segredo do token JWT

## Fluxo de autenticação

1. O cliente faz login em `auth-service`.
2. O serviço valida usuário e senha.
3. O serviço gera um JWT.
4. O cliente usa esse token nas requisições para `job-service`.

## Testes

```bash
cd auth-service
./mvnw test

cd ../job-service
./mvnw test
```

## Observação importante

Este projeto ainda está em desenvolvimento e pode exigir ajustes finos de segurança, configuração do banco e endpoints conforme o fluxo real do sistema.

## Contribuição

1. Faça um fork do projeto
2. Crie uma branch para a funcionalidade
3. Faça commit das alterações
4. Abra um pull request

## Licença

Este projeto está em desenvolvimento e não possui licença definida neste momento.
