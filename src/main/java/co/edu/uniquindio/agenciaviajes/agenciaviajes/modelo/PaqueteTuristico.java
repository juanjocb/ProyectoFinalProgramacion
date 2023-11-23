package co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaqueteTuristico implements Serializable {

    private Destino destino;
    private String nombre;
    private int duracion;
    private String serviciosAdi;
    private double precio;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private int cupoMaximo;

}
