### Teste Backend - Java

Nossos associados são aficionados por Star Wars e com isso, queremos criar um jogo com algumas informações da franquia.
Para possibilitar a equipe de front criar essa aplicação, queremos desenvolver uma API que contenha os dados dos planetas, que podem ser obtidas pela API pública do Star Wars 

APIs para listagem dos planetas e filmes

{
    "planets": "http://swapi.dev/api/planets/", 
    "films": "http://swapi.dev/api/films/"
}


### Requisitos

- A API deve ser REST.
- Para cada planeta, os seguintes dados devem ser obtidos da API do Star Wars e inseridos em um banco de dados:
 - Nome
 - Clima
 - Terreno
 - População
 
 Para cada planeta também devemos ter a quantidade de aparições em filmes que deve ser obtida pela api do Star Wars na inserção do planeta.


### Funcionalidades desejadas

- Criar um mecanismo de buscar os Planetas da API pública do STAR Wars e inserir no banco de dados
- Não devemos ter planetas duplicados
- Listar planetas do banco de dados
- Listar planetas da API do Star Wars
- Listar planetas por um filtro de população
- Buscar por nome no banco de dados
- Buscar por ID no banco de dados
- Remover planeta

**Linguagens que usamos:** Java
**Bancos que usamos:** Postgress e MongoDb
**Diferencial:** Utilizar framework Spring Boot e sua gama de configurações

E lembre-se! Um bom software é um software bem **testado**.

### Template

Nosso template esta utilizando um banco de dados Postgress com um database chamado planets, mas fique a vontade em utilizar o banco que quiser da forma que achar melhor.

## Docker
Na pasta "./src/main/resources/container" irá estar localizado o arquivo docker para iniciar a aplicação.
No diretorio acima rodar o "docker-compose up" para subir do banco em postgres

