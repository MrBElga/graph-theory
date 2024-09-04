import Lista.Lista;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main  {

    private static Lista[] listaAdjacencia;
    public static String[] rotulos;


    public static void main(String[] args) {
        System.out.println("Welcome to JavaFX Application!");
        lerArquivo("mat1");
        if (listaAdjacencia != null && rotulos != null) {
            Lista lista;
            int i = 0;
            lista = listaAdjacencia[i];
            while (lista != null){
                lista.exibirLista();
                i++;
                lista = listaAdjacencia[i];
            }
            lista.encontrarPontosDeArticulacao(listaAdjacencia, rotulos);
        } else {
            System.out.println("Lista de adjacência ou rótulos não carregados.");
        }
    }

    private static void lerArquivo(String nome) {
        String caminhoArquivo ="src/Matriz/mat1.txt";
        System.out.println("Carregando arquivo: " + caminhoArquivo);

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha = reader.readLine();
            if (linha != null) {
                rotulos = linha.split(" ");
                int tamanho = rotulos.length;


                listaAdjacencia = new Lista[tamanho];
                for (int i = 0; i < tamanho; i++) {
                    listaAdjacencia[i] = new Lista();
                }

                // Ler as linhas subsequentes para preencher a lista de adjacência
                int i = 0;
                while ((linha = reader.readLine()) != null && i < tamanho) {
                    String[] valores = linha.split(" ");
                    for (int j = 0; j < valores.length; j++) {
                        int valor = Integer.parseInt(valores[j]);
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