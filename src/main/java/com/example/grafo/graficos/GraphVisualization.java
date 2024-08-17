package com.example.grafo.graficos;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GraphVisualization extends Pane {

    private final int[][] matrizAdj;
    private final String[] labels;

    public GraphVisualization(int[][] matrizAdj, String[] labels) {
        this.matrizAdj = matrizAdj;
        this.labels = labels;

        // para quando o tamanho do Pane muda
        widthProperty().addListener((obs, oldVal, newVal) -> drawGraph());
        heightProperty().addListener((obs, oldVal, newVal) -> drawGraph());

        drawGraph();
    }

    private void drawGraph() {
        double paneWidth = getWidth();
        double paneHeight = getHeight();

        // Remove o canvas antigo
        getChildren().clear();

        Canvas canvas = new Canvas(paneWidth, paneHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        String hexColor = "#363636";
        Color color = Color.web(hexColor);
        gc.setFill(color);
        gc.fillRect(0, 0, paneWidth, paneHeight);

        int nodeCount = labels.length;

        double margin = 50;
        double gridSize = Math.ceil(Math.sqrt(nodeCount));
        double cellSize = (Math.min(paneWidth, paneHeight) - 2 * margin) / gridSize;

        double[][] positions = new double[nodeCount][2];

        for (int i = 0; i < nodeCount; i++) {
            int row = i / (int)gridSize;
            int col = i % (int)gridSize;

            positions[i][0] = margin + col * cellSize + cellSize / 2;
            positions[i][1] = margin + row * cellSize + cellSize / 2;
        }

        // Desenhar vértices
        hexColor = "#ACACAC";
        color = Color.web(hexColor);
        gc.setFont(new Font("Stencil", 14));
        gc.setFill(color);
        for (int i = 0; i < nodeCount; i++) {
            double x = positions[i][0];
            double y = positions[i][1];

            gc.fillOval(x - 15, y - 15, 30, 30);
            hexColor = "#262626";
            color = Color.web(hexColor);
            gc.setFill(color);
            gc.fillText(labels[i], x - 5, y + 5);
            hexColor = "#ACACAC";
            color = Color.web(hexColor);
            gc.setFill(color);

        }

        // Desenhar arestas
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                if (matrizAdj[i][j] > 0) {
                    double x1 = positions[i][0];
                    double y1 = positions[i][1];
                    double x2 = positions[j][0];
                    double y2 = positions[j][1];

                    double dx = x2 - x1;
                    double dy = y2 - y1;
                    double length = Math.sqrt(dx * dx + dy * dy);
                    double offset = 15;
                    double sx1 = x1 + offset * (dx / length);
                    double sy1 = y1 + offset * (dy / length);
                    double sx2 = x2 - offset * (dx / length);
                    double sy2 = y2 - offset * (dy / length);

                    gc.strokeLine(sx1, sy1, sx2, sy2);
                }
            }
        }
        getChildren().add(canvas);
    }
}
