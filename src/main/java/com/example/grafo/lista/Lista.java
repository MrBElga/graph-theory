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

    public void analisarGrafoL(Lista [] listaAdjacencia) {
        //Lista[] listaAdjacencia = Lista.matrizParaLista(matrizAdjacencia, rotulos);

        boolean orientado = grafoOrientado(listaAdjacencia, rotulos);
        boolean simples = grafoSimples(listaAdjacencia, rotulos);
        boolean regular = grafoRegular(listaAdjacencia);
        boolean completo = grafoCompleto(listaAdjacencia, rotulos);

        System.out.println("---------Lista-------------");
        System.out.println("Grafo Orientado: " + orientado);
        System.out.println("Grafo Simples: " + simples);
        System.out.println("Grafo Regular: " + regular);
        System.out.println("Grafo Completo: " + completo);
        System.out.println("---------------------------");
    }

    private static int findIndex(String[] rotulos, String rotulo) {
        for (int i = 0; i < rotulos.length; i++) {
            if (rotulos[i].equals(rotulo)) {
                return i;
            }
        }
        return -1; // Retorna -1 se o rótulo não for encontrado
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

    public static boolean grafoOrientado(Lista[] listaAdjacencia, String[] rotulos) {
        int n = listaAdjacencia.length;
        for (int i = 0; i < n; i++) {
            No atual = listaAdjacencia[i].getInicio();
            while (atual != null) {
                int j = findIndex(rotulos, atual.getAresta());
                No adjacente = listaAdjacencia[j].getInicio();
                boolean encontrouArestaReversa = false;
                while (adjacente != null) {
                    if (findIndex(rotulos, adjacente.getAresta()) == i) {
                        encontrouArestaReversa = true;
                        break;
                    }
                    adjacente = adjacente.getProx();
                }
                if (!encontrouArestaReversa) {
                    return true; // O grafo é orientado se não houver aresta reversa
                }
                atual = atual.getProx();
            }
        }
        return false; // O grafo não é orientado
    }

    public static boolean grafoSimples(Lista[] listaAdjacencia, String[] rotulos) {
        int n = listaAdjacencia.length;
        for (int i = 0; i < n; i++) {
            No atual = listaAdjacencia[i].getInicio();
            while (atual != null) {
                if (findIndex(rotulos, atual.getAresta()) == i) {
                    return false; // Grafo não é simples se houver laço
                }
                atual = atual.getProx();
            }
        }
        return true; // O grafo é simples
    }

    public static boolean grafoRegular(Lista[] listaAdjacencia) {
        int n = listaAdjacencia.length;
        int grau = -1;
        for (int i = 0; i < n; i++) {
            int grauAtual = 0;
            No atual = listaAdjacencia[i].getInicio();
            while (atual != null) {
                grauAtual++;
                atual = atual.getProx();
            }
            if (grau == -1) {
                grau = grauAtual;
            } else if (grau != grauAtual) {
                return false; // Não é regular se houver diferentes graus
            }
        }
        return true; // O grafo é regular
    }

    public static boolean grafoCompleto(Lista[] listaAdjacencia, String[] rotulos) {
        int n = listaAdjacencia.length;
        for (int i = 0; i < n; i++) {
            boolean[] visitado = new boolean[n];
            No atual = listaAdjacencia[i].getInicio();
            while (atual != null) {
                int j = findIndex(rotulos, atual.getAresta());
                visitado[j] = true;
                atual = atual.getProx();
            }
            for (int j = 0; j < n; j++) {
                if (i != j && !visitado[j]) {
                    return false; // O grafo não é completo se algum vértice não tiver aresta
                }
            }
        }
        return true; // O grafo é completo
    }

}
