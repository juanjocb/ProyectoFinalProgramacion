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
public class Reserva implements Serializable {

    private Cliente cliente;
    private LocalDate fechaSolicitud;
    private LocalDate fechaPlaneacion;
    private int cantPersonas;
    private PaqueteTuristico paqueteTuristico;
    private ArrayList<GuiaTuristico> guiasTuristicos;
    private String estado;


}
