package com.example.grafo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.util.Arrays;

public class HelloController {

    @FXML
    private Label textoBoasVindas;

    @FXML
    private TextField fileNameField;

    @FXML
    private StackPane graphPane;

    @FXML
    private StackPane graphPane1;

    @FXML
    private StackPane graphPane2;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        String nomeArquivo = fileNameField.getText();
        if (nomeArquivo == null || nomeArquivo.isEmpty()) {
            textoBoasVindas.setText("Por favor, insira um nome de arquivo.");
            return;
        }

        Matriz.leitor(nomeArquivo);

        int[][] matrizAdjacencia = Matriz.getMatrizAdjacencia();
        String[] rotulos = Matriz.getRotulos();

        // Ordenar os rótulos em ordem alfabética e reorganizar a matriz de acordo
        String[] rotulosOrdenados = Arrays.copyOf(rotulos, rotulos.length);
        Arrays.sort(rotulosOrdenados);

        int[][] matrizOrdenada = new int[rotulosOrdenados.length][rotulosOrdenados.length];
        for (int i = 0; i < rotulosOrdenados.length; i++) {
            int indiceOriginal = Arrays.asList(rotulos).indexOf(rotulosOrdenados[i]);
            for (int j = 0; j < rotulosOrdenados.length; j++) {
                int indiceOrdenado = Arrays.asList(rotulos).indexOf(rotulosOrdenados[j]);
                matrizOrdenada[i][j] = matrizAdjacencia[indiceOriginal][indiceOrdenado];
            }
        }

        // Exibir o grafo no Pane esquerdo
        if (graphPane != null) {
            graphPane.getChildren().clear();
            GraphVisualization visualizacaoGrafo = new GraphVisualization(matrizOrdenada, rotulosOrdenados);
            graphPane.getChildren().add(visualizacaoGrafo);
        }

        // Exibir a matriz no Pane direito
        if (graphPane1 != null) {
            graphPane1.getChildren().clear();
            MatrizVisualization visualizacaoMatriz = new MatrizVisualization(matrizOrdenada, rotulosOrdenados);
            graphPane1.getChildren().add(visualizacaoMatriz);
        }

        // Exibir a análise do grafo no Pane inferior
        if (graphPane2 != null) {
            graphPane2.getChildren().clear();

            StringBuilder analise = new StringBuilder();
            analise.append("Grafo Orientado: ").append(Matriz.isGrafoOrientado() ? "Sim" : "Não").append("\n");
            analise.append("Grafo Simples: ").append(Matriz.isGrafoSimples() ? "Sim" : "Não").append("\n");
            analise.append("Grafo Regular: ").append(Matriz.isGrafoRegular() ? "Sim" : "Não").append("\n");
            analise.append("Grafo Completo: ").append(Matriz.isGrafoCompleto() ? "Sim" : "Não").append("\n");

            Text analiseText = new Text(analise.toString());
            analiseText.setFont(new Font("Arial", 14));
            analiseText.setFill(Color.BLACK);

            graphPane2.getChildren().add(analiseText);
            graphPane2.setStyle(" -fx-padding: 10;-fx-alignment: center-left;");
        }
    }

    @FXML
    protected void onClearButtonClick() {
        if (graphPane != null) {
            graphPane.getChildren().clear();
        }
        if (graphPane1 != null) {
            graphPane1.getChildren().clear();
        }
        if (graphPane2 != null) {
            graphPane2.getChildren().clear();
        }

    }
}
