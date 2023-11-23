package co.edu.uniquindio.agenciaviajes.agenciaviajes.controlador;

import co.edu.uniquindio.agenciaviajes.agenciaviajes.MainAgencia;
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

    public void gestionGuia(ActionEvent actionEvent) throws IOException {
        main.abrirPanelGestionGuia();
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
