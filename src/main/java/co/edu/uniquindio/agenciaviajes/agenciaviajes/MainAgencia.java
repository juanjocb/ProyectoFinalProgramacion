package co.edu.uniquindio.agenciaviajes.agenciaviajes;

import co.edu.uniquindio.agenciaviajes.agenciaviajes.controlador.*;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo.Agencia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;

public class MainAgencia extends Application {

    ModelFactoryController domain = ModelFactoryController.getInstance();
    private Stage stage;
    private Agencia agencia;

    private void handleCloseRequest(WindowEvent event) {
        serializarAgencia();
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        agencia = Agencia.deserializar("C:\\Users\\Juan Jose\\OneDrive\\Escritorio\\Workspace java\\AgenciaViajes\\src\\main\\java\\co\\edu\\uniquindio\\agenciaviajes\\agenciaviajes\\archivo\\model.txt");  // Intentar cargar la instancia de Agencia desde el archivo

        if (agencia == null) {
            // Si falla la deserialización o el archivo no existe, crear una nueva instancia
            agencia = Agencia.obtenerInstancia();
        }

        stage.setOnCloseRequest(this::handleCloseRequest);
        iniciarLogin();
    }



    private void iniciarLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainAgencia.class.getResource("LoginFX.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        LoginController controller = fxmlLoader.getController();
        controller.setMain(this);  // Asegúrate de que este método se llame para establecer la referencia 'main'
        stage.setTitle("Bienvenido a SinFronteras");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void abrirPanelAdmin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainAgencia.class.getResource("PanelAdministrador.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AdminController controller = fxmlLoader.getController();
        controller.setMain(this);
        stage.setTitle("Panel Administrador");
        stage.setScene(scene);
        stage.show();
//        serializarAgencia();
    }

    public void abrirPanelGestionGuia() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainAgencia.class.getResource("GestionGuiaTFX.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GuiaTController controller = fxmlLoader.getController();
        controller.setMain(this);
        stage.setTitle("Panel Gestion Guia Turistico");
        stage.setScene(scene);
        stage.show();
        serializarAgencia();
    }

    public void abrirPanelGestionPaquete() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainAgencia.class.getResource("GestionPaqueteTFX.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        PaqueteController controller = fxmlLoader.getController();
        controller.setMain(this);
        stage.setTitle("Panel Gestion Paquete Turistico");
        stage.setScene(scene);
        stage.show();
        serializarAgencia();
    }

    public void abrirPanelGestionDestino() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainAgencia.class.getResource("GestionDestinoFX.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        DestinoController controller = fxmlLoader.getController();
        controller.setMain(this);
        stage.setTitle("Panel Gestion Destino");
        stage.setScene(scene);
        stage.show();
        serializarAgencia();
    }

    // Métodos adicionales para la serialización automática
    public void serializarAgencia() {
        agencia.serializar("C:\\Users\\Juan Jose\\OneDrive\\Escritorio\\Workspace java\\AgenciaViajes\\src\\main\\java\\co\\edu\\uniquindio\\agenciaviajes\\agenciaviajes\\archivo\\model.txt");
    }
}