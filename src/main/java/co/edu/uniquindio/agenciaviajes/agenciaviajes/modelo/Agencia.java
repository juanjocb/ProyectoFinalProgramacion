package co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo;
import java.io.*;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Agencia implements Serializable {

    private static Agencia instancia;
    private ArrayList<Cliente> clientes;
    private ArrayList<Reserva> reservas;
    private Admin administrador;

    private Agencia(ArrayList<Cliente> clientes, ArrayList<Destino> destinos, ArrayList<GuiaTuristico> guiaTuristicos, ArrayList<PaqueteTuristico> paqueteTuristicos, ArrayList<Reserva> reservas, Admin administrador) {
        this.clientes = clientes;
        this.reservas = reservas;
        this.administrador = administrador != null ? administrador : Admin.obtenerInstancia();
    }

    public static Agencia obtenerInstancia() {
        if (instancia == null) {
            instancia = new Agencia();
        }
        return instancia;
    }

    public void serializar(String nombreArchivo) {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            salida.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No serializo");
        }
    }

    public void registrarCliente(Cliente cliente) throws Exception {
        if (clientes.contains(cliente)) {
            throw new Exception("Ups, disculpa. Ya alguien se encuentra registrado con estos datos");
        } else {
            clientes.add(cliente);
            System.out.println("Se ha guardado al cliente: " + cliente.getContrasenia() + " " + cliente.getIdentificacion());
        }
    }

    public static Agencia deserializar(String nombreArchivo) {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            Agencia agencia = (Agencia) entrada.readObject();
            System.out.println("Deserialización exitosa.");
            return agencia;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Metodo que garantiza que durante el proceso de deserializar se obtenga la misma unica instancia
    private Object readResolve() throws ObjectStreamException {
        return obtenerInstancia();
    }


    public Cliente iniciarSesionCliente(String identificacion, String contrasenia) {
        System.out.println("Voy a comparar: " + identificacion + " " + contrasenia);
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificacion().equalsIgnoreCase(identificacion) && cliente.getContrasenia().equalsIgnoreCase(contrasenia)) {
                System.out.println("Si existe el cliente igual");
                System.out.println(identificacion + " " + contrasenia);
                return cliente;
            }
        }
        mostrarAlertaError("No se encontraron los datos");
        return null;
    }

    private void mostrarAlertaError (String mensaje){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
