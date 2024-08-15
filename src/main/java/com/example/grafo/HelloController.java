package com.example.grafo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import com.example.grafo.Matriz;
import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Pane graphPane;  // Add this pane to your FXML layout

    @FXML
    protected void onHelloButtonClick() throws IOException {
        welcomeText.setText("Welcome to JavaFX Application!");
        Matriz.leitor(); // Carrega a matriz e os rótulos

        int[][] adjacencyMatrix = Matriz.getAdjacencyMatrix();
        String[] labels = Matriz.getLabels();

        if (graphPane != null) {
            graphPane.getChildren().clear();  // Clear previous graph
            GraphVisualization graphVisualization = new GraphVisualization(adjacencyMatrix, labels);
            graphPane.getChildren().add(graphVisualization);
        }
    }
}
