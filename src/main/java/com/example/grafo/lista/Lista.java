package com.example.grafo.lista;

public class Lista {
    public String Aresta;
    public int Custo;
    public Lista prox;

    public Lista() {
    }

    public Lista(String aresta, int custo, Lista prox) {
        Aresta = aresta;
        Custo = custo;
        this.prox = prox;
    }

    public String getAresta() {
        return Aresta;
    }

    public void setAresta(String aresta) {
        Aresta = aresta;
    }

    public int getCusto() {
        return Custo;
    }

    public void setCusto(int custo) {
        Custo = custo;
    }

    public Lista getProx() {
        return prox;
    }

    public void setProx(Lista prox) {
        this.prox = prox;
    }
}
