package co.edu.uniquindio.agenciaviajes.agenciaviajes.controlador;

import co.edu.uniquindio.agenciaviajes.agenciaviajes.MainAgencia;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.errores.GuiaNotFoundException;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo.Admin;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo.Destino;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo.GuiaTuristico;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import javax.swing.*;
import java.io.IOException;

public class DestinoController {
    public TextField txtNombreDestino;
    public TextField txtCiudadDestino;
    public TextArea txtDescripcionDestino;
    public Button buttonGuardarDestino;
    public Button buttonCancelarDestino;
    public Button buttonActualizarDestino;
    public Button buttonBorrarDestino;
    public Button buttonBuscarDestino;
    public MenuButton climaSeleccionado;
    private MainAgencia main;


    public void guardarDestino(){
        try {
            String nombre = txtNombreDestino.getText();
            String ciudad = txtCiudadDestino.getText();
            String descripcion = txtDescripcionDestino.getText();
            String clima = climaSeleccionado.getText();

            if (nombre.isEmpty() || ciudad.isEmpty() || descripcion.isEmpty() || clima.isEmpty()) {
                mostrarAlerta("ERROR", "Ups, no puedes agregar un Destino sin su informacion");
                return;
            }

            Destino destino = new Destino(nombre,ciudad,descripcion, clima);

            Admin.obtenerInstancia().agregarDestino(destino);

            txtNombreDestino.setText("");
            txtCiudadDestino.setText("");
            txtDescripcionDestino.setText("");

        } catch (Exception e) {
            mostrarAlerta("ERROR", "Tuvimos un error al intentar guardar el Destino, intentalo nuevamente");

        }
    }

    public void cancelarDestino() {
        limpiarCampos();
    }

    public void actualizarDestino() {
        try {
            String nombre = txtNombreDestino.getText();
            String ciudad = txtCiudadDestino.getText();
            String descripcion = txtDescripcionDestino.getText();
            String clima = climaSeleccionado.getText();


            if (nombre.isEmpty() || ciudad.isEmpty() || descripcion.isEmpty() || clima.isEmpty()) {
                mostrarAlerta("ERROR", "Ups, no puedes agregar un destino sin su informacion");

                return;
            }

            Destino destino = Admin.obtenerInstancia().buscarDestino(nombre);

            if (destino != null) {
                destino.setNombre(nombre);
                destino.setCiudad(ciudad);
                destino.setDescripcion(descripcion);
                destino.setClima(clima);

                Admin.obtenerInstancia().actualizarDestino(destino);

                txtNombreDestino.setText("");
                txtCiudadDestino.setText("");
                txtDescripcionDestino.setText("");
                climaSeleccionado.setText("");
            } else {
                mostrarAlerta("ERROR", "Tuvimos un error al intentar buscar el Destino, intentalo nuevamente");

            }

        } catch (Exception e) {
            mostrarAlerta("ERROR", "Tuvimos un error al intentar actualizar la informacion del Destino, intentalo nuevamente");
        }
    }

    public void borrarDestino() {
        String nombre = txtNombreDestino.getText();

        try {
            Destino destino = Admin.obtenerInstancia().buscarDestino(nombre);

            if (destino != null) {
                Admin.obtenerInstancia().eliminarDestino(destino);

                txtNombreDestino.setText("");
                txtCiudadDestino.setText("");
                txtDescripcionDestino.setText("");
                climaSeleccionado.setText("");
            } else {
                mostrarAlerta("ERROR", "No se encontró un Destino con el nombre proporcionada");
            }
        } catch (Exception e) {
            mostrarAlerta("ERROR", "Disculpa, por el momento no existe ningun Destino");
        }
    }


    public void buscarDestino() {
        try {
            String nombre = txtNombreDestino.getText();

            if (!nombre.isEmpty()) {
                Destino destino = Admin.obtenerInstancia().buscarDestino(nombre);

                if (destino != null) {
                    txtNombreDestino.setText(destino.getNombre());
                    txtDescripcionDestino.setText(destino.getCiudad());
                    txtCiudadDestino.setText(destino.getDescripcion());
                    climaSeleccionado.setText(destino.getClima());
                } else {
                    txtNombreDestino.clear();
                    txtDescripcionDestino.clear();
                    txtCiudadDestino.clear();
                    climaSeleccionado.setText("");


                    mostrarAlerta("ERROR", "Nombre incorrecta Ups, parece que has ingresado un nombre incorrecta");
                }
            } else {

                mostrarAlerta("ERROR", "Campo vacío Por favor, ingresa una nombre antes de buscar.");
            }
        } catch (Exception e) {

            mostrarAlerta("ERROR", "Error al buscar el Destino Ups, parece que ocurrió un error al buscar el Destino");
        }
    }

    public void seleccionarPrimavera(ActionEvent actionEvent) {
        climaSeleccionado.setText("Primavera");
    }

    public void seleccionarVerano(ActionEvent actionEvent) {
        climaSeleccionado.setText("Verano");
    }

    public void seleccionarOtono(ActionEvent actionEvent) {
        climaSeleccionado.setText("Otoño");
    }

    public void seleccionarInvierno(ActionEvent actionEvent) {
        climaSeleccionado.setText("Invierno");
    }

    private void limpiarCampos() {
        txtNombreDestino.clear();
        txtCiudadDestino.clear();
        txtDescripcionDestino.clear();
        climaSeleccionado.setText(""); // Limpiar el clima seleccionado
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void setMain(MainAgencia mainAgencia) {
        this.main = mainAgencia;
    }
    public void atras(ActionEvent actionEvent) throws IOException {
        main.abrirPanelAdmin();
    }
}
