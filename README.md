# AvaliacaoJava

Projeto para avaliação em Java.

Este projeto utiliza Gradle, pois é mais simples para um projeto de menor porte e facilita sua implementação e manutenção.

## Índice
- [Sobre o Projeto](#sobre-o-projeto)
- [Instalação e Configuração](#instalacao-e-configuracao)
- [Utilização da API](#utilizacao-da-api)
  - [Listar Filmes](#listar-filmes)
  - [Filmes Vencedores](#filmes-vencedores)
  - [Filtrar por Ano](#filtrar-por-ano)
  - [Maior Intervalo de Tempo entre Prêmios](#maior-intervalo-de-tempo-entre-premios)
  - [Menor Intervalo de Tempo entre Prêmios](#menor-intervalo-de-tempo-entre-premios)
  - [Maior vencedor e menor vencedor de Prêmios](#todos-intervalos)
  - [Inserção de Filmes](#insercao-de-filmes)
  - [Atualização de Filmes](#atualizacao-de-filmes)
  - [Deleção de Filmes](#deleção-de-filmes)
- [Executando os Testes](#executando-os-testes)

## Sobre o Projeto
Este projeto foi desenvolvido como parte de uma avaliação para demonstrar conhecimentos em Java. Ele consiste em uma API para gerenciamento de filmes, incluindo operações de consulta, inserção, atualização e deleção. A API também permite realizar consultas específicas, como identificar o produtor com o maior e o menor intervalo de tempo entre prêmios consecutivos.

## Instalação e Configuração
Para rodar este projeto, é necessário ter o Gradle instalado. Após clonar o repositório, entre na pasta do projeto e instale as dependências necessárias:

```bash
Utilização da API
A API possui os seguintes endpoints para interagir com o sistema de filmes:

Listar Filmes
Método: GET
URL: http://localhost:8080/api/filmes

Filmes Vencedores
Método: GET
URL: http://localhost:8080/api/filmes/vencedores

Filtrar por Ano
Método: GET
Parametro: ano
URL: http://localhost:8080/api/filmes/filmeporano?ano=1980

Maior Intervalo de Tempo entre Prêmios
Método: GET
URL: http://localhost:8080/api/filmes/premiadodemaiorintervalo

Menor Intervalo de Tempo entre Prêmios
Método: GET
URL: http://localhost:8080/api/filmes/premiadomenorintervalo

Menor Maior e menor vencedor por intervalo de tempo
Método: GET
URL: http://localhost:8080/api/filmes/todosintervalos

Inserção de Filmes
Método: POST
URL: http://localhost:8080/api/filmes
Request Body:
```
```json
Copiar código
{
    "ano": 1980,
    "titulo": "Cruising Alterado",
    "estudio": "Lorimar Productions, United Artists",
    "produtor": "Jerry Weintraub",
    "vencedor": false
}
```

```bash
Atualização de Filmes
Método: PUT
URL: http://localhost:8080/api/filmes/{id}
Request Body:
```
```json
Copiar código
{
    "ano": 1980,
    "titulo": "Cruising Alterado",
    "estudio": "Lorimar Productions, United Artists",
    "produtor": "Jerry Weintraub",
    "vencedor": false
}
```

```bash
Deleção de Filmes
Método: DELETE
URL: http://localhost:8080/api/filmes/{id}
```

## Executando os Testes
Para rodar os testes diretamente pela linha de comando no IntelliJ, execute o seguinte comando:
```bash
comandos:
./gradlew test
```