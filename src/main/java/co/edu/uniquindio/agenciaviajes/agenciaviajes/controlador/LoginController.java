package co.edu.uniquindio.agenciaviajes.agenciaviajes.controlador;

import co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo.Agencia;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.MainAgencia;

public class LoginController {

    public PasswordField passwordField;
    public TextField usernameField;
    public Button buttonIniciarSesion;
    public Button buttonCrearCuenta;
    ModelFactoryController domain = ModelFactoryController.getInstance();
    Agencia agencia;
    MainAgencia main;

    public void setMain(MainAgencia mainAgencia) {
        this.main = mainAgencia;
    }

    public void inicializarAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public void iniciarSesion(ActionEvent actionEvent) throws IOException {
        System.out.println("Entro al metodo");
        String usuario = usernameField.getText();
        String contrasenia = passwordField.getText();

        if(usuario != null && contrasenia != null){
            if (usuario.equalsIgnoreCase("administrador") && contrasenia.equalsIgnoreCase("soyeladmin")) {
                main.abrirPanelAdmin();
            }else{



        try {
            System.out.println("Entro al try");
            Cliente clienteAutenticado = agencia.iniciarSesionCliente(usuario, contrasenia);
            if(clienteAutenticado != null) {
                main.abrirPanelCliente();
            }
        } catch (Exception e) {
            System.out.println("Error al iniciar sesión como cliente: " + e.getMessage());
            e.printStackTrace();
        }

            }
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void crearCuentaCliente(ActionEvent actionEvent) throws IOException {
        main.abrirPanelRegistroCliente();
    }




}
