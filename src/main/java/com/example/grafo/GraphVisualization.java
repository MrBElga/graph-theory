package com.example.grafo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GraphVisualization extends Pane {

    private final int[][] matrizAdj;
    private final String[] labels;

    public GraphVisualization(int[][] matrizAdj, String[] labels) {
        this.matrizAdj = matrizAdj;
        this.labels = labels;

        // Adiciona um listener para quando o tamanho do Pane muda
        widthProperty().addListener((obs, oldVal, newVal) -> drawGraph());
        heightProperty().addListener((obs, oldVal, newVal) -> drawGraph());

        // Inicializa o canvas e a visualização
        drawGraph();
    }

    private void drawGraph() {
        double paneWidth = getWidth();
        double paneHeight = getHeight();

        // Remove o canvas antigo, se houver
        getChildren().clear();

        // Criar um novo canvas com as dimensões atuais do Pane
        Canvas canvas = new Canvas(paneWidth, paneHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, paneWidth, paneHeight);

        int nodeCount = labels.length;

        // Definindo margens e espaçamento para que os pontos não fiquem na borda do Pane
        double margin = 50;
        double gridSize = Math.ceil(Math.sqrt(nodeCount)); // Tamanho da grade (quadrado)
        double cellSize = (Math.min(paneWidth, paneHeight) - 2 * margin) / gridSize; // Tamanho de cada célula

        double[][] positions = new double[nodeCount][2];

        // Definindo posições para os nós (vértices) em uma grade
        for (int i = 0; i < nodeCount; i++) {
            int row = i / (int)gridSize;
            int col = i % (int)gridSize;

            positions[i][0] = margin + col * cellSize + cellSize / 2;
            positions[i][1] = margin + row * cellSize + cellSize / 2;
        }

        // Desenhar vértices
        gc.setFill(Color.RED);
        for (int i = 0; i < nodeCount; i++) {
            double x = positions[i][0];
            double y = positions[i][1];

            gc.fillOval(x - 15, y - 15, 30, 30);
            gc.setFill(Color.WHITE);
            gc.fillText(labels[i], x - 10, y + 5);
            gc.setFill(Color.RED);
        }

        // Desenhar arestas
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                if (matrizAdj[i][j] > 0) {
                    double x1 = positions[i][0];
                    double y1 = positions[i][1];
                    double x2 = positions[j][0];
                    double y2 = positions[j][1];

                    // Ajusta o comprimento das linhas para que não toquem as bordas dos nós
                    double dx = x2 - x1;
                    double dy = y2 - y1;
                    double length = Math.sqrt(dx * dx + dy * dy);
                    double offset = 15; // Tamanho do raio do círculo
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
