package co.edu.uniquindio.agenciaviajes.agenciaviajes.controlador;

import co.edu.uniquindio.agenciaviajes.agenciaviajes.MainAgencia;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo.Agencia;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class RegistroClienteController {
    public TextField identificacionCli;
    public TextField nombreCli;
    public TextField correoCli;
    public TextField numCli;
    public TextField direccionCli;
    public Button buttonGuardar;
    public Button buttonCancelar;
    public Button buttonAtras;
    public PasswordField contraseniaCli;
    private MainAgencia main;

    public void setMain(MainAgencia mainAgencia) {
        this.main = mainAgencia;
    }

    @FXML
    public void guardarCliente(ActionEvent event) {
        try {
            String identificacion = identificacionCli.getText();
            String nombre = nombreCli.getText();
            String correo = correoCli.getText();
            String numeroTelefono = numCli.getText();
            String direccion = direccionCli.getText();
            String contrasenia = contraseniaCli.getText();

            if (identificacion.isEmpty() || contrasenia.isEmpty()) {
                mostrarAlerta("Error", "Identificación y Nombre son campos obligatorios.");
                return;
            }

            Cliente cliente = new Cliente(identificacion, nombre, correo, numeroTelefono, direccion, contrasenia);

            try {
                Agencia.obtenerInstancia().registrarCliente(cliente);
                mostrarAlerta("Éxito", "Cliente registrado correctamente.");
                limpiarCamposCliente();
                main.iniciarLogin();
            } catch (Exception e) {
                mostrarAlerta("Error", "Error al intentar registrar al cliente. Verifica que no esté duplicado.");
                e.printStackTrace();
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al intentar registrar al cliente.");
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCamposCliente() {
        identificacionCli.clear();
        nombreCli.clear();
        correoCli.clear();
        numCli.clear();
        direccionCli.clear();
    }

    public void cancelarPeticion(MouseEvent actionEvent) throws IOException {
        main.iniciarLogin();
    }

    public void atras(ActionEvent actionEvent) throws IOException {
        main.iniciarLogin();
    }
}
