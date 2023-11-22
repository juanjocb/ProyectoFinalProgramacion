module co.edu.uniquindio.agenciaviajes.agenciaviajes {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.logging;
    requires java.desktop;

    opens co.edu.uniquindio.agenciaviajes.agenciaviajes.controlador to javafx.fxml;
    exports co.edu.uniquindio.agenciaviajes.agenciaviajes;
}