package com.example.socialgrafo;

import com.example.socialgrafo.Lista.Lista;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class HelloController {
    private static Lista[] listaAdjacencia;
    public static String[] rotulos;

    private String nomeArquivo = null;
    private boolean isMatriz = true; // Controla o formato atual
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
       lerArquivo("mat0");
        if (listaAdjacencia != null && rotulos != null) {
            Lista lista = new Lista();
            lista.encontrarPontosDeArticulacao(listaAdjacencia, rotulos);
        } else {
            welcomeText.setText("Lista de adjacência ou rótulos não carregados.");
        }
    }

    private static void lerArquivo(String nome) {
        String caminhoArquivo ="src/main/java/com/example/socialgrafo/Matriz/mat0.txt";
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
