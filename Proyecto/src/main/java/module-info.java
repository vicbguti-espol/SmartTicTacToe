module com.dienarau.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.dienarau.proyecto to javafx.fxml;
    exports com.dienarau.proyecto;
}
