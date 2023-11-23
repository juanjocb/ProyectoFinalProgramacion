package co.edu.uniquindio.agenciaviajes.agenciaviajes.controlador;

import co.edu.uniquindio.agenciaviajes.agenciaviajes.MainAgencia;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo.Admin;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo.Destino;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

public class VerDestinoController {

    public MenuButton buttonMenuDestinos;
    public TextField nombreDestino;
    public TextField ciudadDestino;
    public TextField climaDestino;
    public TextArea descripcionDestino;
    public ToggleGroup destinoToggleGroup;
    public Button buttonBuscas;
    public Button atras;
    MainAgencia main;
    public void setMain(MainAgencia mainAgencia) {
        this.main = mainAgencia;
    }

    public void initialize() {
        // Inicializa el ToggleGroup
        destinoToggleGroup = new ToggleGroup();
    }

    public void verDestinos(MouseEvent actionEvent) {

        try {
            buttonMenuDestinos.getItems().clear();
            ArrayList<Destino> destinos = Admin.obtenerInstancia().getDestinos();

            for (Destino destino : destinos) {
                RadioMenuItem menuItem = new RadioMenuItem(destino.getNombre());
                menuItem.setToggleGroup(destinoToggleGroup);

                menuItem.setOnAction(event -> {
                    buttonMenuDestinos.setText(destino.getNombre());
                });

                buttonMenuDestinos.getItems().add(menuItem);
            }
        } catch (Exception e) {
            mostrarAlertaError("Error al intentar mostrar los destinos.");
        }
    }

    public void buscarDestino(ActionEvent actionEvent) {
        try {
            String nombreDestinoSeleccionado = buttonMenuDestinos.getText();
            ArrayList<Destino> destinos = Admin.obtenerInstancia().getDestinos();

            Destino destinoSeleccionado = null;  // Inicializar a null

            for (Destino destino : destinos) {
                if (destino.getNombre().equalsIgnoreCase(nombreDestinoSeleccionado)) {
                    destinoSeleccionado = destino;
                    break;
                }
            }

            if (destinoSeleccionado != null) {
                // Establecer los campos en la interfaz gráfica con los valores del objeto Destino
                nombreDestino.setText(destinoSeleccionado.getNombre());
                ciudadDestino.setText(destinoSeleccionado.getCiudad());
                climaDestino.setText(destinoSeleccionado.getClima());
                descripcionDestino.setText(destinoSeleccionado.getDescripcion());
            } else {
                mostrarAlertaError("Destino no encontrado.");
            }
        } catch (Exception e) {
            mostrarAlertaError("Error al buscar el destino.");
        }
    }


    private void mostrarAlertaError(String mensaje){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void atras(ActionEvent actionEvent) throws IOException {
        main.abrirPanelCliente();
    }
}
