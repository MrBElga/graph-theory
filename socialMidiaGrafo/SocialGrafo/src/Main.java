import Lista.Lista;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static Lista[] listaAdjacencia;
    public static String[] rotulos;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("===========================GRAFOS=============================");
        System.out.println("Digite o nome do arquivo (sem .txt no final): ");
        lerArquivo(sc.nextLine());
        if (listaAdjacencia != null && rotulos != null) {
            System.out.println("Lista do grafo:");
            for (int i = 0; i < listaAdjacencia.length; i++) {
                listaAdjacencia[i].exibirLista();
            }

            // Executa a BFS a partir de um vértice inicial
            System.out.println("Digite o vértice inicial para a BFS:");
            String verticeInicial = sc.nextLine();
            listaAdjacencia[0].bfs(listaAdjacencia, rotulos, verticeInicial);
        } else {
            System.out.println("Lista de adjacência ou rótulos não carregados.");
        }

        System.out.println("==============================================================");
    }

    private static void lerArquivo(String nome) {
        String caminhoArquivo = "src/Matriz/" + nome + ".txt";
        System.out.println("##### Carregando arquivo: " + caminhoArquivo+" #####");

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha = reader.readLine();
            int valor;
            if (linha != null) {

                rotulos = linha.split(" ");
                int tamanho = rotulos.length;

                listaAdjacencia = new Lista[tamanho];
                for (int i = 0; i < tamanho; i++) {
                    listaAdjacencia[i] = new Lista();
                    listaAdjacencia[i].inserirFim(rotulos[i], 0);
                }

                int i = 0;
                while ((linha = reader.readLine()) != null && i < tamanho) {
                    String[] valores = linha.split(" ");
                    for (int j = 0; j < valores.length; j++) {
                        valor = Integer.parseInt(valores[j]);
                        if (valor != 0) {

                            String aresta = rotulos[j];
                            listaAdjacencia[i].inserirFim(aresta, valor);
                        }
                    }
                    i++;
                }
            }
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }
}