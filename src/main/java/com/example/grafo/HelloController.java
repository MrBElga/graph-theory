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
    private Pane graphPane;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        Matriz.leitor();

        int[][] adjacencyMatrix = Matriz.getAdjacencyMatrix();
        String[] labels = Matriz.getLabels();

        if (graphPane != null) {
            graphPane.getChildren().clear();  // Clear previous graph
            GraphVisualization graphVisualization = new GraphVisualization(adjacencyMatrix, labels);
            graphPane.getChildren().add(graphVisualization);
        }
    }
}
