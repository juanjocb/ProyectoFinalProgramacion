package co.edu.uniquindio.agenciaviajes.agenciaviajes.controlador;

import co.edu.uniquindio.agenciaviajes.agenciaviajes.MainAgencia;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.errores.GuiaNotFoundException;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo.Admin;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo.GuiaTuristico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;
import java.io.IOException;

public class GuiaTController {
    @FXML
    public Button buttonActualizarGuia;
    @FXML
    public Button buttonEliminarGuia;
    @FXML
    public TextField identificacionGuia;
    @FXML
    public TextField nombreGuia;
    @FXML
    public TextField lenguajesGuia;
    @FXML
    public TextField expGuia;
    @FXML
    public Button buttonGuardarGuia;
    @FXML
    public Button buttonBuscarGuia;
    public Button buttonCancelar;
    public Button buttonAtras;
    @FXML
    private MainAgencia main;

    public void setMain(MainAgencia mainAgencia) {
        this.main = mainAgencia;
    }

    @FXML
    public void guardarGuia(ActionEvent actionEvent) throws IOException {
        try {
            String identificacion = identificacionGuia.getText();
            String nombre = nombreGuia.getText();
            String lenguajes = lenguajesGuia.getText();

            int exp;
            try {
                exp = Integer.parseInt(expGuia.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ups, error. El dato que ingresaste no es un numero");
                return;
            }

            if (identificacion.isEmpty() || nombre.isEmpty() || lenguajes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ups, no puedes agregar un guia sin su informacion");
                return;
            }

            GuiaTuristico guia = new GuiaTuristico(identificacion, nombre, lenguajes, exp);

            Admin.obtenerInstancia().agregarGuia(guia);

            identificacionGuia.setText("");
            nombreGuia.setText("");
            lenguajesGuia.setText("");
            expGuia.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tuvimos un error al intentar guardar el guia, intentalo nuevamente");
        }
    }

    @FXML
    public void guardarDatosActualizados(ActionEvent actionEvent) {
        try {
            String identificacion = identificacionGuia.getText();
            String nombre = nombreGuia.getText();
            String lenguajes = lenguajesGuia.getText();

            int exp;
            try {
                exp = Integer.parseInt(expGuia.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ups, error. El dato que ingresaste no es un numero");
                return;
            }

            if (identificacion.isEmpty() || nombre.isEmpty() || lenguajes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ups, no puedes agregar un guia sin su informacion");
                return;
            }

            GuiaTuristico guia = Admin.obtenerInstancia().buscarGuia(identificacion);

            if (guia != null) {
                guia.setNombre(nombre);
                guia.setLenguajes(lenguajes);
                guia.setExperienciaAnios(exp);

                Admin.obtenerInstancia().actualizarGuia(guia);

                identificacionGuia.setText("");
                nombreGuia.setText("");
                lenguajesGuia.setText("");
                expGuia.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Tuvimos un error al intentar buscar el guia, intentalo nuevamente");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tuvimos un error al intentar actualizar la informacion del guia, intentalo nuevamente");
        }
    }

    @FXML
    public void borrarGuia() throws GuiaNotFoundException {
        String identificacion = identificacionGuia.getText();

        try {
            GuiaTuristico guia = Admin.obtenerInstancia().buscarGuia(identificacion);

            if (guia != null) {
                Admin.obtenerInstancia().eliminarGuia(guia);

                identificacionGuia.setText("");
                nombreGuia.setText("");
                lenguajesGuia.setText("");
                expGuia.setText("");
            } else {
                throw new GuiaNotFoundException("No se encontró un guía con la identificación proporcionada.");
            }
        } catch (GuiaNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Disculpa, por el momento no existe ningun guia");
        }
    }
    @FXML
    public void buscarGuia(ActionEvent actionEvent) {
        try {
            String identificacion = identificacionGuia.getText();

            if (!identificacion.isEmpty()) {
                GuiaTuristico guia = Admin.obtenerInstancia().buscarGuia(identificacion);

                if (guia != null) {
                    nombreGuia.setText(guia.getNombre());
                    lenguajesGuia.setText(guia.getLenguajes());
                    expGuia.setText(Integer.toString(guia.getExperienciaAnios()));
                } else {
                    nombreGuia.clear();
                    lenguajesGuia.clear();
                    expGuia.clear();

                    mostrarAlerta("Identificación incorrecta", "Ups, parece que has ingresado una identificación incorrecta.");
                }
            } else {
                mostrarAlerta("Campo vacío", "Por favor, ingresa una identificación antes de buscar.");
            }
        } catch (Exception e) {
            mostrarAlerta("Error al buscar guía", "Ups, parece que ocurrió un error al buscar el guía.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void cancelarAccion(ActionEvent actionEvent) {
        String identificacion = identificacionGuia.getText();
        String nombre = nombreGuia.getText();
        String lenguajes = lenguajesGuia.getText();

        try {
            int exp = expGuia.getText().isEmpty() ? 0 : Integer.parseInt(expGuia.getText());

            identificacionGuia.setText("");
            nombreGuia.setText("");
            lenguajesGuia.setText("");
            expGuia.setText("");
        } catch (NumberFormatException e) {
        }
    }

    public void atras(ActionEvent actionEvent) throws IOException {
        main.abrirPanelAdmin();
    }
}
