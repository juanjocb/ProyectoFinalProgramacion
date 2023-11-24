package co.edu.uniquindio.agenciaviajes.agenciaviajes.controlador;

import co.edu.uniquindio.agenciaviajes.agenciaviajes.MainAgencia;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminController {

    public Button buttonIniciarSesion;
    ModelFactoryController domain = ModelFactoryController.getInstance();
    private MainAgencia main;

    public void setMain(MainAgencia mainAgencia) {
        this.main = mainAgencia;
    }

    public void gestionGuia(ActionEvent actionEvent) {
        // Crear un hilo para el retraso de 5 segundos
        Thread thread = new Thread(() -> {
            try {
                // Retraso de 5 segundos
                Thread.sleep(5000);

                // Después del retraso, ejecutar la apertura del panel de gestión de guía en el hilo de JavaFX
                Platform.runLater(() -> {
                    try {
                        main.abrirPanelGestionGuia();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Iniciar el hilo
        thread.start();
    }

    public void gestionPaquete(ActionEvent actionEvent) throws IOException {
        main.abrirPanelGestionPaquete();
    }

    public void gestionDestino(ActionEvent actionEvent) throws IOException {
        main.abrirPanelGestionDestino();
    }

    public void salir(ActionEvent actionEvent) throws IOException {
        main.iniciarLogin();
    }
}
