# Projeto de Grafos

Este repositório contém a implementação de grafos utilizando tanto listas de adjacência quanto matrizes de adjacência. Além disso, há um exemplo de problema envolvendo redes sociais, onde o grafo é implementado utilizando listas de adjacência. O projeto foi desenvolvido em Java e utiliza JavaFX para a interface gráfica.

## Estrutura do Repositório

O repositório está organizado em duas pastas principais:

### 1. [Grafo](./grafo)
Esta pasta contém a implementação de um grafo usando duas abordagens diferentes:
- **Lista de Adjacência:** Representa o grafo como uma coleção de listas, onde cada lista corresponde a um vértice e contém os vizinhos conectados a ele.
- **Matriz de Adjacência:** Representa o grafo como uma matriz, onde as linhas e colunas correspondem aos vértices, e os valores nas células indicam a presença (ou ausência) de arestas.

#### Funcionalidades Implementadas:
- Carregar e visualizar grafos: O usuário pode carregar um arquivo de texto contendo a matriz de adjacência e visualizar o grafo correspondente.
- Visualização da matriz de adjacência: Exibição da matriz de adjacência do grafo carregado.
- Análise do grafo: O programa realiza diversas análises no grafo, como:
   - Verificação se o grafo é orientado.
   - Verificação se o grafo é simples.
   - Verificação se o grafo é regular.
   - Caso orientado se o grafo é regular de emissão ou transmissão.
   - Verificação se o grafo é completo.


### 2. [Rede Social](./socialMidiaGrafo/SocialGrafo)
Esta pasta contém a implementação de um problema específico que envolve uma rede social. O grafo da rede social é representado utilizando listas de adjacência.

#### Funcionalidades Implementadas:
  - EM CONSTRUçÃO

## Tecnologias Utilizadas

- **Linguagem:** Java
- **Interface Gráfica:** JavaFX


