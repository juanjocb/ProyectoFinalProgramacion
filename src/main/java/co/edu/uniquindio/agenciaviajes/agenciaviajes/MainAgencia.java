package co.edu.uniquindio.agenciaviajes.agenciaviajes;

import co.edu.uniquindio.agenciaviajes.agenciaviajes.controlador.*;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo.Agencia;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.persistencia.Persistencia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MainAgencia extends Application {

    ModelFactoryController domain = ModelFactoryController.getInstance();
    private Stage stage;
    private Agencia agencia;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        agencia = Persistencia.cargarRecursoHostalBinario();

        if (agencia == null) {
            agencia = Agencia.obtenerInstancia();
        }

        stage.setOnCloseRequest(this::handleCloseRequest);
        iniciarLogin();
    }



    public void iniciarLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainAgencia.class.getResource("LoginFX.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        LoginController controller = fxmlLoader.getController();
        controller.setMain(this);  // Asegúrate de que este método se llame para establecer la referencia 'main'
        controller.inicializarAgencia(agencia);  // Asegúrate de que este método se llame para inicializar 'agencia'
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
        Persistencia.guardarRecursoDomainBinarioAgencia(agencia);
    }

    public void abrirPanelGestionGuia() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainAgencia.class.getResource("GestionGuiaTFX.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GuiaTController controller = fxmlLoader.getController();
        controller.setMain(this);
        stage.setTitle("Panel Gestion Guia Turistico");
        stage.setScene(scene);
        stage.show();
        Persistencia.guardarRecursoDomainBinarioAgencia(agencia);
    }

    public void abrirPanelGestionPaquete() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainAgencia.class.getResource("GestionPaqueteTFX.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        PaqueteController controller = fxmlLoader.getController();
        controller.setMain(this);
        stage.setTitle("Panel Gestion Paquete Turistico");
        stage.setScene(scene);
        stage.show();
        Persistencia.guardarRecursoDomainBinarioAgencia(agencia);
    }

    public void abrirPanelGestionDestino() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainAgencia.class.getResource("GestionDestinoFX.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        DestinoController controller = fxmlLoader.getController();
        controller.setMain(this);
        stage.setTitle("Panel Gestion Destino");
        stage.setScene(scene);
        stage.show();
        Persistencia.guardarRecursoDomainBinarioAgencia(agencia);
    }

    public void abrirPanelRegistroCliente() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainAgencia.class.getResource("RegistroClienteFX.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        RegistroClienteController controller = fxmlLoader.getController();
        controller.setMain(this);
        stage.setTitle("Panel Registro Cliente");
        stage.setScene(scene);
        stage.show();
        Persistencia.guardarRecursoDomainBinarioAgencia(agencia);
    }

    public void abrirPanelCliente() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainAgencia.class.getResource("PanelCliente.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ClienteController controller = fxmlLoader.getController();
        controller.setMain(this);
        stage.setTitle("Panel Registro Cliente");
        stage.setScene(scene);
        stage.show();
        Persistencia.guardarRecursoDomainBinarioAgencia(agencia);
    }

    public void abrirPanelVerDestinos() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainAgencia.class.getResource("VerDestino.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        VerDestinoController controller = fxmlLoader.getController();
        controller.setMain(this);
        stage.setTitle("Panel VerDestinos");
        stage.setScene(scene);
        stage.show();
        Persistencia.guardarRecursoDomainBinarioAgencia(agencia);
    }

    // Métodos adicionales para la serialización automática
    public void serializarAgencia() {
        System.out.println("SERIALIZADO");
        agencia.serializar("C:\\Users\\Juan Jose\\OneDrive\\Escritorio\\Workspace java\\AgenciaViajes\\src\\main\\java\\co\\edu\\uniquindio\\agenciaviajes\\agenciaviajes\\archivo\\model.txt");
    }

    private void handleCloseRequest(WindowEvent event) {
        Persistencia.guardarRecursoDomainBinarioAgencia(agencia);
    }




}