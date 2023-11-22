package co.edu.uniquindio.agenciaviajes.agenciaviajes.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.MainAgencia;

public class LoginController {

    public PasswordField passwordField;
    public TextField usernameField;
    public Button buttonIniciarSesion;
    ModelFactoryController domain = ModelFactoryController.getInstance();
    MainAgencia main;


    public void setMain(MainAgencia mainAgencia) {
        this.main = mainAgencia;
    }

    public void iniciarSesionCliente(ActionEvent actionEvent) {
        try {
            String usuario = usernameField.getText();
            String contrasenia = passwordField.getText();

            if (usuario.equalsIgnoreCase("administrador") && contrasenia.equalsIgnoreCase("soyeladmin")) {
                main.abrirPanelAdmin();
            } else {
                System.out.println("Datos incorrectos");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crearCuentaCliente(ActionEvent actionEvent) {
    }


}
