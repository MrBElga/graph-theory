package com.example.grafo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Matriz {

    private static String[] labels;
    private static int[][] adjacencyMatrix;

    public static void leitor() {
        Matriz matriz = new Matriz();
        matriz.lerArquivo();
        matriz.analisarGrafo();
    }

    private void lerArquivo() {
        System.out.printf("Informe o nome do arquivo texto:\n");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        nome = nome.concat(".txt");
        System.out.println(nome);

        try (BufferedReader reader = new BufferedReader(new FileReader(nome))) {
            String line = reader.readLine();
            if (line != null) {
                labels = line.split(",");
                int size = labels.length;
                adjacencyMatrix = new int[size][size];

                int i = 0;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    for (int j = 0; j < values.length; j++) {
                        adjacencyMatrix[i][j] = Integer.parseInt(values[j]);
                    }
                    i++;
                }
            }
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

    public static int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public static String[] getLabels() {
        return labels;
    }

    private void analisarGrafo() {
        boolean isDirected = isGrafoOrientado();
        boolean isSimple = isGrafoSimples();
        boolean isRegular = isGrafoRegular();
        boolean isComplete = isGrafoCompleto();

        System.out.println("Grafo Orientado: " + isDirected);
        System.out.println("Grafo Simples: " + isSimple);
        System.out.println("Grafo Regular: " + isRegular);
        System.out.println("Grafo Completo: " + isComplete);
    }

    private boolean isGrafoOrientado() {
        int size = adjacencyMatrix.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (adjacencyMatrix[i][j] != adjacencyMatrix[j][i]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isGrafoSimples() {
        int size = adjacencyMatrix.length;
        for (int i = 0; i < size; i++) {
            if (adjacencyMatrix[i][i] != 0) {
                return false; // Grafo não simples, pois tem laço
            }
            for (int j = 0; j < size; j++) {
                if (adjacencyMatrix[i][j] > 1) {
                    return false; // Grafo não simples, pois tem múltiplas arestas
                }
            }
        }
        return true;
    }

    private boolean isGrafoRegular() {
        int size = adjacencyMatrix.length;
        int degree = -1;
        for (int i = 0; i < size; i++) {
            int currentDegree = 0;
            for (int j = 0; j < size; j++) {
                currentDegree += adjacencyMatrix[i][j];
            }
            if (degree == -1) {
                degree = currentDegree;
            } else if (degree != currentDegree) {
                return false; // Grafo não regular
            }
        }
        return true;
    }

    private boolean isGrafoCompleto() {
        int size = adjacencyMatrix.length;
        int totalEdges = size * (size - 1);
        int edgeCount = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                edgeCount += adjacencyMatrix[i][j];
            }
        }
        return edgeCount == totalEdges;
    }
}
