package com.example.grafo.matriz;

import com.example.grafo.lista.Lista;
import com.example.grafo.lista.No;

import java.util.Arrays;

public class Matriz {

    public void analisarGrafo(int[][] matrizAdjacencia) {
        boolean orientado = grafoOrientado(matrizAdjacencia);
        boolean simples = grafoSimples(matrizAdjacencia);
        boolean regular = grafoRegular(matrizAdjacencia);
        boolean completo = grafoCompleto(matrizAdjacencia);
        System.out.println("---------Matriz-------------");
        System.out.println("Grafo Orientado: " + orientado);
        System.out.println("Grafo Simples: " + simples);
        System.out.println("Grafo Regular: " + regular);
        System.out.println("Grafo Completo: " + completo);
    }

    public static boolean grafoOrientado(int[][] matrizAdjacencia) {
        int tamanho = matrizAdjacencia.length;
        for (int i = 0; i < tamanho; i++)
            for (int j = 0; j < tamanho; j++)
                if (matrizAdjacencia[i][j] != matrizAdjacencia[j][i])
                    return true;
        return false;
    }

    public static boolean grafoSimples(int[][] matrizAdjacencia) {
        int tamanho = matrizAdjacencia.length;
        for (int i = 0; i < tamanho; i++)
            if (matrizAdjacencia[i][i] != 0)
                return false; // Grafo não é simples se houver laços
        return true; // É simples
    }

    public static boolean grafoRegular(int[][] matrizAdjacencia) {
        int tamanho = matrizAdjacencia.length;
        int grau = -1;
        for (int i = 0; i < tamanho; i++) {
            int grauAtual = 0;
            for (int j = 0; j < tamanho; j++) {
                if (matrizAdjacencia[i][j]>0)
                    grauAtual++;
            }
            if (grau == -1)
                grau = grauAtual;
            else if (grau != grauAtual)
                return false; // Não é regular se houver diferentes graus
        }
        return true; // É regular
    }

    public static boolean grafoCompleto(int[][] matrizAdjacencia) {
        int tamanho = matrizAdjacencia.length;
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (i == j) {
                    if (matrizAdjacencia[i][j] != 0) { // A diagonal principal deve ser 0
                        return false;
                    }
                } else {
                    if (matrizAdjacencia[i][j] < 1) { // Os outros elementos devem ser maior 1
                        return false;
                    }
                }
            }
        }
        return true; // É completo
    }


    public static int[][] listaParaMatriz(Lista[] lista, String[] rotulos) {
        int n = lista.length;
        int[][] matriz = new int[n][n];
        for (int i = 0; i < n; i++) {
            No atual = lista[i].getInicio();
            while (atual != null) {
                int j = findIndex(rotulos, atual.getAresta());
                matriz[i][j] = atual.getCusto();
                atual = atual.getProx();

            }
        }
        return matriz;
    }

    private static int findIndex(String[] rotulos, String rotulo) {
        for (int i = 0; i < rotulos.length; i++) {
            if (rotulos[i].equals(rotulo)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean grafoRegularDeEmissao(int[][] matrizAdjacencia) {
        int n = matrizAdjacencia.length;
        int[] grauSaida = new int[n];
        int[] grauEntrada = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grauSaida[i] += matrizAdjacencia[i][j];
                grauEntrada[j] += matrizAdjacencia[i][j];
            }
        }

        int grauSaidaEsperado = grauSaida[0];
        int grauEntradaEsperado = grauEntrada[0];

        for (int i = 1; i < n; i++) {
            if (grauSaida[i] != grauSaidaEsperado || grauEntrada[i] != grauEntradaEsperado) {
                return false; // Não é regular de emissão
            }
        }
        return true; // É regular de emissão
    }

    public static boolean grafoRegularDeTransmissao(int[][] matrizAdjacencia) {
        int n = matrizAdjacencia.length;
        int[] grauSaida = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grauSaida[i] += matrizAdjacencia[i][j];
            }
        }
        int grauSaidaEsperado = grauSaida[0];
        for (int i = 1; i < n; i++) {
            if (grauSaida[i] != grauSaidaEsperado) {
                return false; // Não é regular de transmissão
            }
        }
        return true; // É regular de transmissão
    }


}
