package co.edu.uniquindio.agenciaviajes.agenciaviajes.controlador;

import co.edu.uniquindio.agenciaviajes.agenciaviajes.MainAgencia;
import javafx.event.ActionEvent;

import java.io.IOException;

public class ClienteController {
    MainAgencia main;

    public void setMain(MainAgencia main) {
        this.main = main;
    }

    public void verDestino(ActionEvent actionEvent) throws IOException {
        main.abrirPanelVerDestinos();
    }

    public void hacerReserva(ActionEvent actionEvent) {
    }

    public void actualizarInfo(ActionEvent actionEvent) {
    }

    public void salir(ActionEvent actionEvent) throws IOException {
        main.iniciarLogin();
    }
}
