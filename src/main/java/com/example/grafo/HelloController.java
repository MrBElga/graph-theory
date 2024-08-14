package com.example.grafo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.example.grafo.Matriz;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        welcomeText.setText("Welcome to JavaFX Application!");
        Matriz.leitor();
    }
}