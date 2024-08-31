module com.example.grafo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.grafo to javafx.fxml;
    exports com.example.grafo;
    exports com.example.grafo.matriz;
    opens com.example.grafo.matriz to javafx.fxml;
    exports com.example.grafo.graficos;
    opens com.example.grafo.graficos to javafx.fxml;
}