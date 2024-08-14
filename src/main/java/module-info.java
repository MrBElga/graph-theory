module com.example.grafo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.grafo to javafx.fxml;
    exports com.example.grafo;
}