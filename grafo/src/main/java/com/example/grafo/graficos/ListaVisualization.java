package com.example.grafo.graficos;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import com.example.grafo.lista.Lista;

public class ListaVisualization extends VBox {

    public ListaVisualization(Lista[] listaAdjacencia, String[] labels) {
        setSpacing(10);

        for (int i = 0; i < listaAdjacencia.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Vértice ").append(labels[i]).append(": ");

            Lista lista = listaAdjacencia[i];
            com.example.grafo.lista.No atual = lista.getInicio();
            while (atual != null) {
                sb.append(atual.getAresta()).append(" (").append(atual.getCusto()).append("), ");
                atual = atual.getProx();
            }

            Label label = new Label(sb.toString());
            label.setFont(new Font("Arial", 14));
            label.setTextFill(Color.WHITE);
            getChildren().add(label);
        }

        setStyle("-fx-padding: 10; -fx-background-color: #363636");
    }
}
