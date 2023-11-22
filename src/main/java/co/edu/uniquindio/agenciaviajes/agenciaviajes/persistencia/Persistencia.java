package co.edu.uniquindio.agenciaviajes.agenciaviajes.persistencia;

import co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo.Agencia;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Persistencia {

    public static final String RUTA_ARCHIVO_MODELO_AGENCIA_BINARIO = "C:\\Users\\Juan Jose\\OneDrive\\Escritorio\\Workspace java\\AgenciaViajes\\src\\main\\java\\co\\edu\\uniquindio\\agenciaviajes\\agenciaviajes\\archivo\\model.txt";

    //SERIALIZACION Y XML

    //DESEREALIZAR
    public static Agencia cargarRecursoHostalBinario() {

        Agencia agencia = null;
        try {
            agencia = (Agencia) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_AGENCIA_BINARIO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return agencia;
    }

    //SERIALIZAR
    public static void guardarRecursoDomainBinarioAgencia(Agencia agencia) {

        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_AGENCIA_BINARIO, agencia);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }








}
