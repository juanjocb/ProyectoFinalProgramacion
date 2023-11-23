package co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Destino implements Serializable {

    private String nombre;
    private String ciudad;
    private String descripcion;
    private ArrayList<Image> imagenes;
    private String clima;

    public Destino(String nombre, String ciudad, String descripcion, String clima) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.descripcion = descripcion;
        this.clima = clima;
    }
}
