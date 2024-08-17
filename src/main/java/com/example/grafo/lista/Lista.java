package com.example.grafo.lista;

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
}
