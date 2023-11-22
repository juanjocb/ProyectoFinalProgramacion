package co.edu.uniquindio.agenciaviajes.agenciaviajes.controlador;

import co.edu.uniquindio.agenciaviajes.agenciaviajes.MainAgencia;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo.Admin;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo.Agencia;
import co.edu.uniquindio.agenciaviajes.agenciaviajes.persistencia.Persistencia;

import java.io.IOException;
import java.util.ArrayList;

public class ModelFactoryController {

    private static ModelFactoryController instancia;
    Agencia agencia;

    private ModelFactoryController() {
        inicializarDatosIniciales();
    }



    //------------------------------  Singleton ------------------------------------------------
    // Clase estatica oculta. Tan solo se instanciara el singleton una vez
    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aqu� al ser protected
        private final static ModelFactoryController eINSTANCE;
        static {
            eINSTANCE = new ModelFactoryController();
        }
    }

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    private void inicializarDatosIniciales() {

        agencia = crearAgencia();

        Admin admin = Admin.obtenerInstancia();
        admin.setUsuario("administrador");
        admin.setContrasenia("soyeladmin");
    }

    public Agencia getAgencia() {
        return agencia;
    }


    private Agencia crearAgencia() {
        Agencia agencia = Agencia.obtenerInstancia();
        agencia.setClientes(new ArrayList<>());
        agencia.setReservas(new ArrayList<>());

        return agencia;
    }




}
