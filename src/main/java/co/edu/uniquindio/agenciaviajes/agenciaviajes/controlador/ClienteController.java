package co.edu.uniquindio.agenciaviajes.agenciaviajes.controlador;

import co.edu.uniquindio.agenciaviajes.agenciaviajes.MainAgencia;
import javafx.event.ActionEvent;

public class ClienteController {
    MainAgencia main;

    public void setMain(MainAgencia main) {
        this.main = main;
    }

    public void verDestino(ActionEvent actionEvent) {
        main.abrirPanelVerDestinos();
    }

    public void hacerReserva(ActionEvent actionEvent) {
    }

    public void actualizarInfo(ActionEvent actionEvent) {
    }

    public void salir(ActionEvent actionEvent) {
    }
}
