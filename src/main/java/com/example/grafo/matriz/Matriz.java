package com.example.grafo.matriz;

public class Matriz {

    public void analisarGrafo(int[][] matrizAdjacencia) {
        boolean orientado = grafoOrientado(matrizAdjacencia);
        boolean simples = grafoSimples(matrizAdjacencia);
        boolean regular = grafoRegular(matrizAdjacencia);
        boolean completo = grafoCompleto(matrizAdjacencia);

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
            for (int j = 0; j < tamanho; j++)
                grauAtual += matrizAdjacencia[i][j];
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
                    if (matrizAdjacencia[i][j] != 1) { // Os outros elementos devem ser 1
                        return false;
                    }
                }
            }
        }
        return true; // É completo
    }
}
