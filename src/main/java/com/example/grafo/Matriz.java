package com.example.grafo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Matriz {

    private static String[] rotulos;
    private static int[][] matrizAdjacencia;

    public static void leitor(String nome) {
        Matriz matriz = new Matriz();
        matriz.lerArquivo(nome);
        matriz.analisarGrafo();
    }

    private void lerArquivo(String nome) {
        nome = nome.concat(".txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(nome))) {
            String linha = reader.readLine();
            if (linha != null) {
                rotulos = linha.split(",");
                int tamanho = rotulos.length;
                matrizAdjacencia = new int[tamanho][tamanho];

                int i = 0;
                while ((linha = reader.readLine()) != null) {
                    String[] valores = linha.split(",");
                    for (int j = 0; j < valores.length; j++) {
                        matrizAdjacencia[i][j] = Integer.parseInt(valores[j]);
                    }
                    i++;
                }
            }
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

    public static int[][] getMatrizAdjacencia() {
        return matrizAdjacencia;
    }

    public static String[] getRotulos() {
        return rotulos;
    }

    public void analisarGrafo() {
        boolean orientado = isGrafoOrientado();
        boolean simples = isGrafoSimples();
        boolean regular = isGrafoRegular();
        boolean completo = isGrafoCompleto();

        System.out.println("Grafo Orientado: " + orientado);
        System.out.println("Grafo Simples: " + simples);
        System.out.println("Grafo Regular: " + regular);
        System.out.println("Grafo Completo: " + completo);
    }

    public static boolean isGrafoOrientado() {
        int tamanho = matrizAdjacencia.length;
        for (int i = 0; i < tamanho; i++)
            for (int j = 0; j < tamanho; j++)
                if (matrizAdjacencia[i][j] != matrizAdjacencia[j][i])
                    return true;
        return false;
    }

    public static boolean isGrafoSimples() {
        int tamanho = matrizAdjacencia.length;
        for (int i = 0; i < tamanho; i++) {
            if (matrizAdjacencia[i][i] != 0)
                return false; //caso grafo não simples tem laço
            for (int j = 0; j < tamanho; j++)
                if (matrizAdjacencia[i][j] > 1)
                    return false; //caso grafo não simples tem múltiplas arestas (eu acho)
        }
        return true; //eh simples
    }

    public static boolean isGrafoRegular() {
        int tamanho = matrizAdjacencia.length;
        int grau = -1;
        for (int i = 0; i < tamanho; i++) {
            int grauAtual = 0;
            for (int j = 0; j < tamanho; j++) {
                grauAtual += matrizAdjacencia[i][j];
            }
            if (grau == -1) {
                grau = grauAtual;
            } else if (grau != grauAtual) {
                return false; //caso não regular tem grau maior
            }
        }
        return true;// eh regular
    }

    public static boolean isGrafoCompleto() {
        int tamanho = matrizAdjacencia.length;
        int totalArestas = tamanho * (tamanho - 1);
        int contagemArestas = 0;
        for (int i = 0; i < tamanho; i++) {
            for (int j = i + 1; j < tamanho; j++) {
                contagemArestas += matrizAdjacencia[i][j];
            }
        }
        return contagemArestas == totalArestas; // retorna um true pq é igual o grua de todos
    }
}
