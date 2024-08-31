package com.example.socialgrafo;

import com.example.socialgrafo.Lista.Lista;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    private static Lista[] listaAdjacencia;
    public static String[] rotulos;
    private static int[][] matrizAdjacencia;
    private String nomeArquivo = null;
    private boolean isMatriz = true; // Controla o formato atual
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}