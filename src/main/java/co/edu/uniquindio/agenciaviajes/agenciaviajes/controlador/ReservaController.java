package co.edu.uniquindio.agenciaviajes.agenciaviajes.controlador;

import co.edu.uniquindio.agenciaviajes.agenciaviajes.MainAgencia;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.Date;

public class ReservaController {
    public Button buttonActualizar;
    public Button buttonBorrar;
    public DatePicker fechaSolicitud;
    public DatePicker fechaPlanificada;
    public TextField clienteInvolucrado;
    public TextField cantPersonas;
    public MenuButton paqueteTuristico;
    public MenuButton guiaTuristico;
    public MenuButton estadoReserva;
    public Button buttonGuardarReserva;
    MainAgencia main;  // Asegúrate de tener una referencia a MainAgencia

    public void setMain(MainAgencia mainAgencia) {
        this.main = mainAgencia;
    }

    public void actualizarReserva(ActionEvent actionEvent) {
    }

    public void borrarReserva(ActionEvent actionEvent) {
    }

    public void mostrarPaquetes(MouseEvent mouseEvent) {
    }

    public void mostrarGuias(MouseEvent mouseEvent) {
    }

    public void mostrarEstadoReserva(MouseEvent mouseEvent) {
    }


    public void guardarReserva(ActionEvent actionEvent) {
        try {
            // Obtener los valores de la interfaz gráfica
            Date fechaSolicitud = Date.from(fechaSolicitud.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date fechaPlanificada = Date.from(fechaPlanificada.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            String clienteInvolucrado = clienteInvolucrado.getText();
            int cantPersonas = Integer.parseInt(cantPersonas.getText());
            // Aquí deberías obtener los valores para paqueteTuristico, guiaTuristico y estadoReserva

            // Crear la reserva
            Reserva reserva = new Reserva();
            reserva.setFechaSolicitud(fechaSolicitud);
            reserva.setFechaPlaneacion(fechaPlanificada);
            // Asignar los demás valores a la reserva

            // Validar y agregar la reserva a la agencia
            main.getAgencia().agregarReserva(reserva);

            // Limpiar los campos de la interfaz gráfica o realizar otras acciones necesarias
            limpiarCampos();

            // Mostrar mensaje de éxito
            mostrarMensaje("Reserva guardada correctamente.");
        } catch (NumberFormatException e) {
            mostrarAlertaError("Error al ingresar la cantidad de personas. Debe ser un número entero.");
        } catch (Exception e) {
            mostrarAlertaError("Error al guardar la reserva: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        fechaSolicitud.setValue(null);
        fechaPlanificada.setValue(null);
        clienteInvolucrado.clear();
        cantPersonas.clear();
    }

    private void mostrarMensaje(String mensaje) {
        // Puedes mostrar un mensaje en la interfaz gráfica, por ejemplo:
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarAlertaError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
