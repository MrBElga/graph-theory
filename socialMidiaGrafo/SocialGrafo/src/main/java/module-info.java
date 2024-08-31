module com.example.socialgrafo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.socialgrafo to javafx.fxml;
    exports com.example.socialgrafo;
}