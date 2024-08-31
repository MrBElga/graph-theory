package com.example.grafo.graficos;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MatrizVisualization extends GridPane {

    public MatrizVisualization(int[][] matrix, String[] labels) {
        setHgap(10);
        setVgap(10);

        // Cabeçalhos das colunas
        for (int i = 0; i < labels.length; i++) {
            Label label = new Label(labels[i]);
            label.setFont(new Font("Arial", 14));
            label.setTextFill(Color.RED);
            add(label, i + 1, 0);
        }

        // Cabeçalhos das linhas e valores da matriz
        for (int i = 0; i < matrix.length; i++) {
            Label rowLabel = new Label(labels[i]);
            rowLabel.setFont(new Font("Arial", 14));
            rowLabel.setTextFill(Color.GREEN);
            add(rowLabel, 0, i + 1);

            for (int j = 0; j < matrix[i].length; j++) {
                Text text = new Text(Integer.toString(matrix[i][j]));
                text.setFont(new Font("Arial", 14));
                text.setFill(Color.WHITE);
                add(text, j + 1, i + 1);
            }
        }

    }
}
