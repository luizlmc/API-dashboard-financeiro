
<h1 align="center">
    Dashboard Financeiro
    <br>API RESTful<br>
    Spring | REST | Java 11
</h1>

<p align="center">
  <a href="#funcionalidades">Funcionalidades</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#stacks-utilizadas">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#como-executar">Como Executar</a>
</p>

Essa aplicação é uma API backend RESTful desenvolvida em Spring Boot em ambiente Docker para um dashboard financeiro com banco de dados em MySQL.
A API inclui operações CRUD de pessoas, categorias e lançamentos de receitas e despesas, e possui autenticação Oauth2 com refresh token. 

## Funcionalidades

### **Categorias**

- Buscar todas as categorias cadastradas
- Buscar uma categoria, pelo código
- Criar uma nova categoria

### **Pessoas**

- Buscar todas as categorias cadastradas
- Buscar uma pessoa, pelo código
- Cadastrar uma nova pessoa
- Remover uma pessoa, pelo código
- Atualizar os da dados de uma pessoa, pelo código

### **Lançamentos**

- Buscar lançamentos
- Buscar lançamentos pela descrição ou data de vencimento
- Buscar um lançamento pelo código
- Criar um novo lançamento
- Remover um lançamento, pelo código
- Atualizar um lançamento, pelo código


## Stacks utilizadas

- JDK 11
- Docker
- Spring boot
- Arquitetura REST
- MySQL
- Hibernate/JPA
- Maven
- Lombok
- MapStruct
- Mockito/ JUnit
- Spring Security (Basic e OAuth2/JWT + controle de permissões por usuário)


## Como executar

- Clone o projeto:

```bash
  $ git clone https://github.com/luizlmc/Dashboard-financeiro.git

  $ cd Dashboard-financeiro
```
- Agora no terminal digite o comando:
```bash
  $ mvn clean install
```
- Em seguida, com o docker instalado digite o comando:

```bash
  $ docker-compose up
```
- Agora é só consumir a API com as URLs abaixo, teste com postman.


    
## Documentação da API

- É preciso gerar um access token para consumir a API, para gerar é preciso uma autenticação basic com usuario e senha conforme a imagem:

<p align="center">
  <img alt="Exemplo Requisição" width="1080px" src="https://user-images.githubusercontent.com/77021623/165862598-c01acc15-f947-461a-99f9-9e3d4cd972cb.png" />
<p>
  
- Os headers ficam assim:
<p align="center">
  <img alt="Exemplo Requisição" width="1080px" src="https://user-images.githubusercontent.com/77021623/165862644-3fd76d2a-f93a-442b-bc44-c91422520073.png" />
<p>

- O body é preciso passar o email do usuario e a senha com o grant_type password, conforme a imagem:
<p align="center">
  <img alt="Exemplo Requisição" width="1080px" src="https://user-images.githubusercontent.com/77021623/165862640-1b61b678-7da5-4e15-a01a-63689b87790a.png" />
<p>
  
- Após gerar um access token passando o email e senha, é gerado um refresh token e armazenado nos cookies, sendo possível gerar um novo access token usando o refresh token, 
  basta passar no grant_type refresh_token:
<p align="center">
  <img alt="Exemplo Requisição" width="1080px" src="https://user-images.githubusercontent.com/77021623/165862645-b3707c59-187b-4b20-a460-57d6bb3683f6.png" />
<p>
  
- E para cada requisição é preciso passar o acces token no header **Authorization** com o valor **Bearer {seu access token gerado}**:
<p align="center">
  <img alt="Exemplo Requisição" width="1080px" src="https://user-images.githubusercontent.com/77021623/165862726-56f011ac-ccb8-41a8-b73e-53a3c62657de.png" />
<p>

- Todas as URLs das requicições disponiveis na API:

|  URL |  Método | Descrição |
|----------|--------------|--------------|
|`http://localhost:8080/oauth/token`                             | POST | Solicita Access Token |
|`http://localhost:8080/oauth/token`                             | POST | Solicita Refresh Token |
|`http://localhost:8080/tokens/revoke`                           | DELETE | Revoga o token atual |
|`http://localhost:8080/persons/`                                 | GET | Retorna todas as pessoas registradas no banco (com paginação) |
|`http://localhost:8080/persons/`                                 | POST | Registra uma pessoa |
|`http://localhost:8080/persons/{id}`                              | GET | Retorna o registro de uma pessoa baseada no ID dela |
|`http://localhost:8080/persons/{id}`                              | DELETE | Deleta o registro de uma pessoa baseada no ID dela |
|`http://localhost:8080/persons/{id}`                              | PUT | Atualiza o registro de uma pessoa baseado no ID dela |
|`http://localhost:8080/persons/{id}/active`                              | PUT | Atualiza a propriedade ativo de uma pessoa baseada no ID dela, no corpo da requisição é preciso enviar um JSON com true ou false |
|`http://localhost:8080/categories`                              | GET | Retorna todas as categorias |
|`http://localhost:8080/categories`                              | POST | Registra uma categoria |
|`http://localhost:8080/categories/{id}`                           | GET | Retorna uma categoria baseado no ID |
|`http://localhost:8080/journalentries`                             | POST | Registra uma lançamento |
|`http://localhost:8080/journalentries`                             | GET  | Retorna todos os lançamentos (com paginação) |
|`http://localhost:8080/journalentries/{id}`                          | GET | Retorna um lançamento baseado no ID |
|`http://localhost:8080/journalentries/{id}`                          | DELETE | Deleta um lançamento baseado no ID |
|`http://localhost:8080/journalentries?summary`           | GET | Retorna lançamentos os lançamentos resumidos (o resultado só retorna o nome da pessoa ao invés do registro completo com endereço) |
|`http://localhost:8080/journalentries?description={searchparam}&dueDateFrom=YYYY-MM-DD&dueDateTo=YYYY-MM-DD`                       | GET| Faz uma pesquisa nos lançamentos com os parâmetros inseridos na URL(descrição e intervalos de data de vencimento) |





## Autores

- [@luizcarvalho](https://www.github.com/luizlmc)

