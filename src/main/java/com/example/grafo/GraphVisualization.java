package com.example.grafo;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GraphVisualization extends Pane {

    private final int[][] adjacencyMatrix;
    private final String[] labels;

    public GraphVisualization(int[][] adjacencyMatrix, String[] labels) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.labels = labels;
        drawGraph();
    }

    private void drawGraph() {
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int nodeCount = labels.length;
        double angleStep = 2 * Math.PI / nodeCount;
        double radius = 200;
        double centerX = canvas.getWidth() / 2;
        double centerY = canvas.getHeight() / 2;

        // Draw nodes
        gc.setFill(Color.BLUE);
        for (int i = 0; i < nodeCount; i++) {
            double angle = i * angleStep;
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);

            gc.fillOval(x - 15, y - 15, 30, 30);
            gc.setFill(Color.WHITE);
            gc.fillText(labels[i], x - 10, y + 5);
            gc.setFill(Color.BLUE);
        }

        // Draw edges
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        for (int i = 0; i < nodeCount; i++) {
            for (int j = i + 1; j < nodeCount; j++) {
                if (adjacencyMatrix[i][j] > 0) {
                    double x1 = centerX + radius * Math.cos(i * angleStep);
                    double y1 = centerY + radius * Math.sin(i * angleStep);
                    double x2 = centerX + radius * Math.cos(j * angleStep);
                    double y2 = centerY + radius * Math.sin(j * angleStep);

                    gc.strokeLine(x1, y1, x2, y2);
                }
            }
        }

        getChildren().add(canvas);
    }
}
