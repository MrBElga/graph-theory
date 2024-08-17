# Projeto de Teoria dos Grafos

Este projeto é uma aplicação Java que realiza a análise e visualização de grafos a partir de matrizes de adjacência. A interface gráfica é construída utilizando JavaFX, permitindo ao usuário carregar arquivos de texto com a matriz de adjacência do grafo, visualizar o grafo gerado e a matriz correspondente, além de realizar análises sobre suas propriedades.

## Funcionalidades

- **Carregar e visualizar grafos**: O usuário pode carregar um arquivo de texto contendo a matriz de adjacência e visualizar o grafo correspondente.
- **Visualização da matriz de adjacência**: Exibição da matriz de adjacência do grafo carregado.
- **Análise do grafo**: O programa realiza diversas análises no grafo, como:
  - Verificação se o grafo é orientado.
  - Verificação se o grafo é simples.
  - Verificação se o grafo é regular.
  - Verificação se o grafo é completo.

  - Caso orientado se o grafo é regular de emissão ou transmissão.

## Estrutura do Projeto

```plaintext
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── grafo/
│   │               ├── graficos/
│   │               │   ├── GraphVisualization.java
│   │               │   └── MatrizVisualization.java
│   │               ├── matriz/
│   │               │   └── Matriz.java
│   │               ├── HelloApplication.java
│   │               └── HelloController.java
│   └── resources/
│       └── com/
│           └── example/
│               └── grafo/
│                   └── hello-view.fxml
└── test/
    └── java/
        └── com/
            └── example/
                └── grafo/
                    └── HelloControllerTest.java
