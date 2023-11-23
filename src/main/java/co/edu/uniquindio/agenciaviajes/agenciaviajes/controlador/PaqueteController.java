package co.edu.uniquindio.agenciaviajes.agenciaviajes.controlador;

import co.edu.uniquindio.agenciaviajes.agenciaviajes.MainAgencia;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo.Admin;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo.Destino;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo.PaqueteTuristico;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PaqueteController {
    public ToggleGroup destinoToggleGroup;
    public Button buttonGuardarPaquete;
    public Button buttonBorrarPaquete;
    public TextField nombrePaquete;
    public TextField duracionPaquete;
    public TextArea servAdicionalesPaquete;
    public TextField precioPaquete;
    public DatePicker fechaInicio;
    public DatePicker fechaFinal;
    public Button buttonActualizarPaquete;
    public Button buttonCancelar;
    public MenuButton menuButtonDestinos;
    public TextField cupoMaximo;
    public Button buttonBuscar;

    private MainAgencia main;
    Admin admin;

    public void initialize() {
        // Inicializa el ToggleGroup
        destinoToggleGroup = new ToggleGroup();
    }

    public void setMain(MainAgencia mainAgencia) {
        this.main = mainAgencia;
    }

    public void guardarPaquete(ActionEvent actionEvent) throws IOException {
            try {

                // Obtener el destino seleccionado
                RadioMenuItem selectedItem = (RadioMenuItem) destinoToggleGroup.getSelectedToggle();
                if (selectedItem == null) {
                    mostrarAlertaError("Selecciona un destino para el paquete.");
                    return;
                }

                String nombreDestino = selectedItem.getText();
                String nombre = nombrePaquete.getText();
                String duracionStr = duracionPaquete.getText();
                String servAdicionales = servAdicionalesPaquete.getText();
                String precioStr = precioPaquete.getText();
                LocalDate fechaInicioValue = fechaInicio.getValue();
                String cupoStr = cupoMaximo.getText();
                LocalDate fechaFinalValue = fechaFinal.getValue();


                // Validar que los campos obligatorios no estén vacíos
                if (nombreDestino.isEmpty()) {
                    mostrarAlertaError("Selecciona un destino para el paquete.");
                    return;
                }

                // Convertir y validar valores numéricos
                int duracion = Integer.parseInt(duracionStr);
                double precio = Double.parseDouble(precioStr);
                int cupo = Integer.parseInt(cupoStr);

                // Validar que la fecha final sea posterior a la fecha de inicio
                if (fechaFinalValue.isBefore(fechaInicioValue)) {
                    mostrarAlertaError("La fecha final debe ser posterior a la fecha de inicio.");
                    return;
                }

                Destino destino = Admin.obtenerInstancia().buscarDestino(nombreDestino);

                if (destino == null) {
                    mostrarAlertaError("No se encontró el destino seleccionado.");
                    return;
                }

                // Crear el objeto PaqueteTuristico
                PaqueteTuristico paquete = new PaqueteTuristico(destino, nombre, duracion, servAdicionales, precio, fechaInicioValue, fechaFinalValue, cupo);

                // Agregar el paquete a la lista
                Admin.obtenerInstancia().agregarPaquete(paquete);

                // Mostrar mensaje de éxito
                mostrarAlertaInfo("Paquete turístico guardado correctamente.");

                // Limpiar los campos
                limpiarCamposPaquete();
            } catch (NumberFormatException e) {
                mostrarAlertaError("Error en el formato de números.");
            } catch (Exception e) {
                mostrarAlertaError("Error al intentar guardar el paquete turístico.");
            }
        }


        private void limpiarCamposPaquete () {
            nombrePaquete.clear();
            duracionPaquete.clear();
            servAdicionalesPaquete.clear();
            precioPaquete.clear();
            fechaInicio.setValue(null);
            fechaFinal.setValue(null);
            cupoMaximo.clear();
        }

        private void mostrarAlertaError (String mensaje){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(mensaje);
            alert.showAndWait();
        }

        private void mostrarAlertaInfo (String mensaje){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText(mensaje);
            alert.showAndWait();
        }

    public void borrarPaquete(ActionEvent actionEvent) {
        try {

            String nombre = nombrePaquete.getText();

            // Lógica para borrar el paquete con el nombre proporcionado
            boolean paqueteBorrado = Admin.obtenerInstancia().borrarPaquete(nombre);
            limpiarCamposPaquete();
            if (paqueteBorrado) {
                mostrarAlertaInfo("Paquete turístico borrado correctamente.");
            } else {
                mostrarAlertaError("No se encontró el paquete seleccionado.");
            }
        } catch (Exception e) {
            mostrarAlertaError("Error al intentar borrar el paquete turístico.");
        }
    }

    public void actualizarPaquete(ActionEvent actionEvent) {
        try {

            String nombre = nombrePaquete.getText();

            // Lógica para obtener y actualizar el paquete con el nombre proporcionado
            PaqueteTuristico paqueteExistente = Admin.obtenerInstancia().buscarPaquete(nombre);

            if (paqueteExistente != null) {
                Admin.obtenerInstancia().actualizarPaquete(paqueteExistente);
                limpiarCamposPaquete();
                mostrarAlertaInfo("Paquete turístico actualizado correctamente.");
            } else {
                mostrarAlertaError("No se encontró el paquete seleccionado.");
            }
        } catch (Exception e) {
            mostrarAlertaError("Error al intentar actualizar el paquete turístico.");
        }
    }

        public void cancelarPeticion (ActionEvent actionEvent){
        limpiarCamposPaquete();
        }


        public void atras (ActionEvent actionEvent) throws IOException {
            main.abrirPanelAdmin();
        }


    public void actualizarDestinos(MouseEvent actionEvent) {

        try {
            menuButtonDestinos.getItems().clear();
            ArrayList<Destino> destinos = Admin.obtenerInstancia().getDestinos();

            for (Destino destino : destinos) {
                RadioMenuItem menuItem = new RadioMenuItem(destino.getNombre());
                menuItem.setToggleGroup(destinoToggleGroup);

                // Manejar el evento de selección del destino
                menuItem.setOnAction(event -> {
                    menuButtonDestinos.setText(destino.getNombre());
                });

                menuButtonDestinos.getItems().add(menuItem);
            }
        } catch (Exception e) {
            mostrarAlertaError("Error al intentar actualizar los destinos.");
        }
    }

    public void buscarPaquete(ActionEvent actionEvent) {
        try {
            // Obtener el nombre del paquete a buscar
            String nombrePaqueteBuscar = nombrePaquete.getText();

            // Validar que el nombre del paquete no esté vacío
            if (nombrePaqueteBuscar.isEmpty()) {
                mostrarAlertaError("Ingresa el nombre del paquete a buscar.");
                return;
            }

            // Llamar al método buscarPaquete en la instancia de Admin
            PaqueteTuristico paqueteEncontrado = Admin.obtenerInstancia().buscarPaquete(nombrePaqueteBuscar);

            // Verificar si se encontró el paquete
            if (paqueteEncontrado != null) {
                String nombre = paqueteEncontrado.getNombre();
                int duracionStr = paqueteEncontrado.getDuracion();
                String servAdicionales = paqueteEncontrado.getServiciosAdi();
                double precioStr = paqueteEncontrado.getPrecio();
                LocalDate fechaInicioValue = paqueteEncontrado.getFechaInicio();
                int cupoStr = paqueteEncontrado.getCupoMaximo();
                LocalDate fechaFinalValue = paqueteEncontrado.getFechaFinal();

                nombrePaquete.setText(nombre);
                duracionPaquete.setText(String.valueOf(duracionStr));
                servAdicionalesPaquete.setText(servAdicionales);
                precioPaquete.setText(String.valueOf(precioStr));
                fechaInicio.setValue(fechaInicioValue);
                fechaFinal.setValue(fechaFinalValue);
                cupoMaximo.setText(String.valueOf(cupoStr));
            } else {
                mostrarAlertaInfo("No se encontró el paquete con el nombre: " + nombrePaqueteBuscar);
            }

        } catch (Exception e) {
            mostrarAlertaError("Error al intentar buscar el paquete turístico.");
        }
    }
}

