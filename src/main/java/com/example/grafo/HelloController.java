package com.example.grafo;

import com.example.grafo.graficos.GraphVisualization;
import com.example.grafo.graficos.MatrizVisualization;
import com.example.grafo.lista.Lista;
import com.example.grafo.matriz.Matriz;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class HelloController {

    @FXML
    private TextField fileNameField;

    @FXML
    private StackPane graphPane;

    @FXML
    private StackPane graphPane1;

    @FXML
    private StackPane graphPane2;

    private static String[] rotulos;
    private static int[][] matrizAdjacencia;
    private String nomeArquivo = null;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        nomeArquivo = fileNameField.getText();

        leitor(nomeArquivo);

        // Exibir o grafo
        if (graphPane != null) {
            graphPane.getChildren().clear();
            GraphVisualization visualizacaoGrafo = new GraphVisualization(matrizAdjacencia, rotulos);
            graphPane.getChildren().add(visualizacaoGrafo);
        }

        // Exibir a matriz
        if (graphPane1 != null) {
            graphPane1.getChildren().clear();
            MatrizVisualization visualizacaoMatriz = new MatrizVisualization(matrizAdjacencia, rotulos);
            graphPane1.getChildren().add(visualizacaoMatriz);
            graphPane1.setStyle(" -fx-padding: 10;-fx-alignment: center-left; -fx-background-color: #363636");
        }

        // Exibir a análise
        if (graphPane2 != null) {
            graphPane2.getChildren().clear();

            StringBuilder analise = new StringBuilder();
            analise.append("Grafo Orientado: ").append(Matriz.grafoOrientado(matrizAdjacencia) ? "Sim" : "Não").append("\n");
            analise.append("Grafo Simples: ").append(Matriz.grafoSimples(matrizAdjacencia) ? "Sim" : "Não").append("\n");
            analise.append("Grafo Regular: ").append(Matriz.grafoRegular(matrizAdjacencia) ? "Sim" : "Não").append("\n");
            analise.append("Grafo Completo: ").append(Matriz.grafoCompleto(matrizAdjacencia) ? "Sim" : "Não").append("\n");

            Text analiseText = new Text(analise.toString());
            analiseText.setFont(new Font("Arial", 14));
            String hexColor = "#ACACAC";
            Color color = Color.web(hexColor);
            analiseText.setFill(color);

            graphPane2.getChildren().add(analiseText);
            graphPane2.setStyle(" -fx-padding: 10;-fx-alignment: center-left; -fx-background-color: #363636");
        }
    }

    @FXML
    protected void onClearButtonClick() {
        if (graphPane != null)
            graphPane.getChildren().clear();
        if (graphPane1 != null)
            graphPane1.getChildren().clear();
        if (graphPane2 != null)
            graphPane2.getChildren().clear();
        nomeArquivo = null;
    }

    public static void leitor(String nome) {
        Matriz matriz = new Matriz();
        lerArquivo(nome);
        matriz.analisarGrafo(matrizAdjacencia);
    }

    private static void lerArquivo(String nome) {
        String caminhoArquivo = Paths.get("src/main/java/com/example/grafo/arquivosTxt", nome + ".txt").toString();
        System.out.println(caminhoArquivo);

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha = reader.readLine();
            if (linha != null) {
                rotulos = linha.split(",");
                int tamanho = rotulos.length;


                matrizAdjacencia = new int[tamanho][tamanho];
                Lista[] listaAdjacencia = new Lista[tamanho];

                for (int i = 0; i < tamanho; i++) {
                    listaAdjacencia[i] = new Lista();
                }

                int i = 0;
                while ((linha = reader.readLine()) != null) {
                    String[] valores = linha.split(",");
                    for (int j = 0; j < valores.length; j++) {
                        matrizAdjacencia[i][j] = Integer.parseInt(valores[j]);
                        if (matrizAdjacencia[i][j] != 0) {
                            String aresta = rotulos[i] + " -> " + rotulos[j];
                            listaAdjacencia[i].inserirFim(aresta, matrizAdjacencia[i][j]);
                        }
                    }
                    i++;
                }

                // Exibir a lista de adjacência
                for (int k = 0; k < tamanho; k++) {
                    System.out.println("Vértice " + rotulos[k] + ": ");
                    listaAdjacencia[k].exibirLista();
                }
            }
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

}
