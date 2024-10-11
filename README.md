# AvaliacaoJava

Projeto para avaliação em Java.

Este projeto utiliza Gradle, pois é mais simples para um projeto de menor porte e facilita sua implementação e manutenção.

## Índice
- [Sobre o Projeto](#sobre-o-projeto)
- [Instalação e Configuração](#instalação-e-configuração)
- [Utilização da API](#utilização-da-api)
  - [Listar Filmes](#listar-filmes)
  - [Filmes Vencedores](#filmes-vencedores)
  - [Filtrar por Ano](#filtrar-por-ano)
  - [Maior Intervalo de Tempo entre Prêmios](#maior-intervalo-de-tempo-entre-prêmios)
  - [Menor Intervalo de Tempo entre Prêmios](#menor-intervalo-de-tempo-entre-prêmios)
  - [Maior vencedor e menor vencedor de Prêmios](#maior-e-menor-vencedor-por-intervalo-de-tempo)
  - [Inserção de Filmes](#inserção-de-filmes)
  - [Atualização de Filmes](#atualização-de-filmes)
  - [Deleção de Filmes](#deleção-de-filmes)
- [Executando os Testes](#executando-os-testes)

## Sobre o Projeto
Este projeto foi desenvolvido como parte de uma avaliação para demonstrar conhecimentos em Java. Ele consiste em uma API para gerenciamento de filmes, incluindo operações de consulta, inserção, atualização e deleção. A API também permite realizar consultas específicas, como identificar o produtor com o maior e o menor intervalo de tempo entre prêmios consecutivos.

## Instalação e Configuração
Para rodar este projeto, é necessário ter o Gradle instalado. Após clonar o repositório, entre na pasta do projeto e instale as dependências necessárias:

## Utilização da API
A API possui os seguintes endpoints para interagir com o sistema de filmes:

## Listar Filmes
Método: GET <br>
URL: 
```bash
http://localhost:8080/api/filmes
```

## Filmes Vencedores
Método: GET <br>
URL:
```bash
http://localhost:8080/api/filmes/vencedores
```

## Filtrar por Ano
Método: GET <br>
Parametro: ano <br>
URL:
```bash
http://localhost:8080/api/filmes/filmeporano?ano=1980
```

## Maior Intervalo de Tempo entre Prêmios
Método: GET <br>
URL:
```bash
http://localhost:8080/api/filmes/premiadodemaiorintervalo
```

## Menor Intervalo de Tempo entre Prêmios
Método: GET <br>
URL:
```bash
http://localhost:8080/api/filmes/premiadomenorintervalo
```

## Maior e Menor vencedor por intervalo de tempo
Método: GET <br>
URL:
```bash
http://localhost:8080/api/filmes/todosintervalos
```

## Inserção de Filmes
Método: POST <br>
URL:
```bash
http://localhost:8080/api/filmes
```
Request Body:
```json
{
    "ano": 1980,
    "titulo": "Cruising Alterado",
    "estudio": "Lorimar Productions, United Artists",
    "produtor": "Jerry Weintraub",
    "vencedor": false
}
```

## Atualização de Filmes
Método: PUT <br>
URL:
```bash
http://localhost:8080/api/filmes/{id}
```
Request Body:
```json
{
    "ano": 1980,
    "titulo": "Cruising Alterado",
    "estudio": "Lorimar Productions, United Artists",
    "produtor": "Jerry Weintraub",
    "vencedor": false
}
```

## Deleção de Filmes
Método: DELETE <br>
URL:
```bash
http://localhost:8080/api/filmes/{id}
```

## Executando os Testes
Para rodar os testes diretamente pela linha de comando no IntelliJ, execute o seguinte comando:
```bash
./gradlew test
```