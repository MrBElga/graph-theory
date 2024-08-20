package com.example.grafo.lista;

import java.util.Objects;

import static com.example.grafo.HelloController.rotulos;

public class Lista {
    private No inicio;

    public Lista() {
        this.inicio = null;
    }

    public void inicializa() {
        inicio = null;
    }

    public void inserirFim(String aresta, int custo) {
        No nova = new No(aresta, custo, null, null);
        if (inicio == null) {
            inicio = nova;
        } else {
            No atual = inicio;
            while (atual.getProx() != null) {
                atual = atual.getProx();
            }
            atual.setProx(nova);
            nova.setAnt(atual);
        }
    }

    public void exibirLista() {
        No atual = inicio;
        while (atual != null) {
            System.out.println(atual.getAresta() + " -> " + atual.getCusto());
            atual = atual.getProx();
        }
    }

    public No getInicio() {
        return inicio;
    }




    public static Lista[] matrizParaLista(int[][] matriz, String[] rotulos) {
        int n = matriz.length;
        Lista[] listaAdjacencia = new Lista[n];
        for (int i = 0; i < n; i++) {
            listaAdjacencia[i] = new Lista();
            for (int j = 0; j < n; j++) {
                if (matriz[i][j] != 0) {
                    listaAdjacencia[i].inserirFim(rotulos[j], matriz[i][j]);
                }
            }
        }
        return listaAdjacencia;
    }
}
