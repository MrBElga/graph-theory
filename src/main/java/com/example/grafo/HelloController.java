package com.example.grafo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import java.io.IOException;


public class HelloController {

    @FXML
    private TextField fileNameField;

    @FXML
    private StackPane graphPane;

    @FXML
    private StackPane graphPane1;

    @FXML
    private StackPane graphPane2;
    private String nomeArquivo = null;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        nomeArquivo = fileNameField.getText();
        Matriz.leitor(nomeArquivo);

        int[][] matrizAdjacencia = Matriz.getMatrizAdjacencia();
        String[] rotulos = Matriz.getRotulos();

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
            analise.append("Grafo Orientado: ").append(Matriz.grafoOrientado() ? "Sim" : "Não").append("\n");
            analise.append("Grafo Simples: ").append(Matriz.grafoSimples() ? "Sim" : "Não").append("\n");
            analise.append("Grafo Regular: ").append(Matriz.grafoRegular() ? "Sim" : "Não").append("\n");
            analise.append("Grafo Completo: ").append(Matriz.grafoCompleto() ? "Sim" : "Não").append("\n");

            Text analiseText = new Text(analise.toString());
            analiseText.setFont(new Font("Arial", 14));
            String hexColor = "#ACACAC";
            Color color = Color.web(hexColor);
            analiseText.setFill(color   );

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
}
