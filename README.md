# CEP Lookup Service

Este projeto é um desafio técnico que consiste em uma API para consulta de CEPs, com integração a um serviço externo e persistência de histórico de buscas (logs).

## 🎯 Objetivo
Desenvolver uma aplicação backend para:
- Consultar endereços a partir de um CEP.
- Consumir uma API externa (mockada via WireMock).
- Registrar cada consulta realizada em um banco de dados relacional para fins de log.

## ⚙️ Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.2.2**
- **Spring Data JPA**: Para persistência de dados.
- **Spring Cloud OpenFeign**: Para consumo da API externa.
- **PostgreSQL**: Banco de dados relacional.
- **Lombok**: Para redução de código boilerplate.
- **WireMock**: Para simulação da API externa de CEP.
- **Docker e Docker Compose**: Para orquestração do banco de dados e do mock.

## 🏗️ Arquitetura e Estrutura do Projeto
A aplicação segue uma organização baseada em camadas, separando responsabilidades de entrada, lógica de negócio e infraestrutura:

- `api`: Contém os controladores REST (`controller`), objetos de transferência de dados (`dto`) e tratamento de exceções.
- `domain`: Define o modelo de dados (`model`), interfaces de repositório (`repository`) e as interfaces/regras de serviço (`service`).
- `infrastructure`: Implementações de clientes externos (`client`) e configurações específicas.

### Fluxo da Aplicação
1. O usuário faz uma requisição GET com o CEP.
2. O serviço consulta a API externa via Feign Client.
3. A resposta é convertida e retornada ao usuário.
4. Os dados da consulta (CEP, JSON de resposta e timestamp) são persistidos na tabela `cep_log`.

## 🚀 Como Executar

### Pré-requisitos
- Docker e Docker Compose instalados.
- JDK 17 (caso queira rodar fora do Docker).
- Maven 3.8+.

### Passos para rodar
1. Suba a infraestrutura necessária (PostgreSQL e WireMock):
   ```bash
   docker-compose up -d
   ```
2. Execute a aplicação Spring Boot:
   ```bash
   mvn spring-boot:run
   ```

A API estará disponível em `http://localhost:8080`.

## 📡 Endpoints

### Consultar CEP
`GET /api/v1/cep/{cep}`

**Exemplo de resposta:**
```json
{
  "cep": "01001-000",
  "logradouro": "Praça da Sé",
  "complemento": "lado ímpar",
  "bairro": "Sé",
  "localidade": "São Paulo",
  "uf": "SP",
  "ibge": "3550308"
}
```

## 🛠️ Princípios Aplicados
- **Single Responsibility Principle (SRP)**: Separação clara entre controladores, serviços de negócio e clientes de infraestrutura.
- **Dependency Injection**: Utilizada extensivamente através do Spring Framework para desacoplamento de componentes.
- **Interface Segregation**: Uso de interfaces para definir o contrato dos serviços (`CepService`).

## 📈 Melhorias Possíveis
- Implementação de testes unitários e de integração.
- Adição de cache (como Redis) para evitar chamadas repetitivas ao serviço externo.
- Documentação dos endpoints com Swagger/OpenAPI.
- Validação mais rigorosa do formato do CEP.

---
**Projeto desenvolvido como parte de um desafio técnico.**
