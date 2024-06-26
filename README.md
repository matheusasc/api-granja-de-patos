# API de Gerenciamento de Patos

Esta é uma API simples para gerenciar informações sobre patos, incluindo seus nomes, valores, status etc.

## Tecnologias Utilizadas

- Spring Boot
- Spring Data JPA
- MySQL
- Hibernate
- Maven
- Spring Web
- H2 Database (para ambiente de desenvolvimento)
- iTextPDF
- JasperReports
- Apache POI
- Insomnia e Postman (para testar a API)

## Como Iniciar a API

Siga as etapas abaixo para iniciar a API localmente:

### Pré-requisitos

Certifique-se de ter instalado em sua máquina:

- Java JDK 11 ou superior
- Maven
- MySQL

### Passos

1. **Clone o repositório:**

    ```bash
    git clone https://github.com/matheusasc/api-granja-de-patos.git
    ```

2. **Configure o banco de dados:**

    - Crie um banco de dados MySQL chamado `granja_de_patos`.
    - Altere as configurações do banco de dados no arquivo `application.properties` conforme necessário.

3. **Compile o projeto:**

    ```bash
    cd api-granja-de-patos
    mvn clean install
    ```

4. **Execute a aplicação:**

    ```bash
    mvn spring-boot:run
    ```

A API estará disponível em `http://localhost:8080`.

### Documentação da API

Você pode acessar a documentação da API e testar os endpoints at
