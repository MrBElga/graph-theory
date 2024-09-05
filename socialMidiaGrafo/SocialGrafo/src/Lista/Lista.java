package Lista;

import java.util.*;

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

    public void exibirListap() {
        No atual = inicio;
        if (atual == null) {
            System.out.println("Lista vazia");
        } else {
            System.out.print(atual.getAresta());
            atual = atual.getProx();
            while (atual != null) {
                System.out.print(" -> " + atual.getCusto() + " | " + atual.getAresta());
                atual = atual.getProx();
            }
            System.out.println();
        }
    }

    public void exibirLista() {
        No atual = inicio;
        if (atual == null) {
            System.out.println("Lista vazia");
        } else {
            System.out.println("========="+atual.getAresta()+"========");
            atual = atual.getProx();
            while (atual != null) {
                System.out.println(" " + atual.getAresta());
                atual = atual.getProx();
            }
            System.out.println("=======================");
        }
    }


    public No getInicio() {
        return inicio;
    }

    // Busca em profundidade para construir árvore geradora e encontrar pontos de articulação
    public void buscarArticulacao(Lista[] listaAdjacencia, String[] rotulos) {
        int n = listaAdjacencia.length;
        boolean[] visitado = new boolean[n];
        int[] prenum = new int[n];  // Ordem de visita na DFS
        int[] menor = new int[n];   // Valor de menor[v]
        int[] pais = new int[n];    // Pai de cada vértice
        boolean[] articulacao = new boolean[n]; // Se o vértice é articulação
        int tempo = 0;

        // Inicializa vetores
        Arrays.fill(pais, -1);
        Arrays.fill(visitado, false);
        Arrays.fill(articulacao, false);

        // Ordenar os rótulos em ordem alfabética e ajustar a lista de adjacência
        // Precisamos de uma lista auxiliar de índices para ajustar os rótulos
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        // Ordenar os índices de acordo com os rótulos em ordem alfabética
        Arrays.sort(indices, Comparator.comparingInt(i -> rotulos[i].charAt(0)));

        // Função de busca em profundidade (DFS) recursiva
        for (int i = 0; i < n; i++) {
            int v = indices[i];
            if (!visitado[v]) {
                dfs(listaAdjacencia, v, visitado, prenum, menor, pais, articulacao, tempo, rotulos);
            }
        }

        exibirArvoreDFSVisual(rotulos, pais);

        // Exibir pontos de articulação
        System.out.println("Influencers:");
        for (int i = 0; i < n; i++) {
            if (articulacao[i]) {
                System.out.println(rotulos[i]);
            }
        }
    }

    private void dfs(Lista[] listaAdjacencia, int v, boolean[] visitado, int[] prenum, int[] menor,
                     int[] pais, boolean[] articulacao, int tempo, String[] rotulos) {
        visitado[v] = true;
        prenum[v] = menor[v] = ++tempo; // Definir tempo de visita
        int filhos = 0;

        // Criar uma lista de adjacentes e ordená-los em ordem alfabética
        List<Integer> adjacentes = new ArrayList<>();
        No atual = listaAdjacencia[v].getInicio();
        while (atual != null) {
            adjacentes.add(findIndex(rotulos, atual.getAresta()));
            atual = atual.getProx();
        }

        // Ordenar os adjacentes de acordo com a ordem alfabética dos rótulos
        adjacentes.sort(Comparator.comparingInt(i -> rotulos[i].charAt(0)));

        // Realizar a DFS para cada adjacente em ordem alfabética
        for (int adj : adjacentes) {
            if (!visitado[adj]) {
                filhos++;
                pais[adj] = v;
                dfs(listaAdjacencia, adj, visitado, prenum, menor, pais, articulacao, tempo, rotulos);

                // Atualiza o valor menor[v]
                menor[v] = Math.min(menor[v], menor[adj]);

                // Condições para identificar pontos de articulação
                if (pais[v] == -1 && filhos > 1) {
                    articulacao[v] = true; // A raiz é articulação se tiver mais de um filho
                }
                if (pais[v] != -1 && menor[adj] >= prenum[v]) {
                    articulacao[v] = true; // Verifica a condição para vértices não raiz
                }
            } else if (adj != pais[v]) {
                // Atualiza o valor menor[v] para o ancestral
                menor[v] = Math.min(menor[v], prenum[adj]);
            }
        }
    }

    public static int findIndex(String[] rotulos, String rotulo) {
        for (int i = 0; i < rotulos.length; i++) {
            if (rotulos[i].equals(rotulo)) {
                return i;
            }
        }
        return -1;
    }

    public void exibirArvoreDFSVisual(String[] rotulos, int[] pais) {
        System.out.println("Estrutura da Árvore DFS (visual):");

        // Começa por todas as raízes (vértices com pais == -1)
        for (int i = 0; i < pais.length; i++) {
            if (pais[i] == -1) {
                exibirSubArvore(i, 0, rotulos, pais);  // Raiz com nível 0
            }
        }
    }

    // Função recursiva para exibir os nós da árvore
    private void exibirSubArvore(int vertice, int nivel, String[] rotulos, int[] pais) {
        // Exibir o vértice atual com a indentação de acordo com o nível
        for (int i = 0; i < nivel; i++) {
            System.out.print("   ");  // Indenta o nível
        }
        System.out.println(rotulos[vertice]);

        // Procurar e exibir todos os filhos deste vértice
        for (int i = 0; i < pais.length; i++) {
            if (pais[i] == vertice) {
                exibirSubArvore(i, nivel + 1, rotulos, pais);  // Chamar recursivamente para os filhos
            }
        }
    }
}
