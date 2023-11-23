package co.edu.uniquindio.agenciaviajes.agenciaviajes.modelo;

import javafx.beans.value.ObservableValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

//Averiguar si es un usuario para registrar o lo gestiona el Admin
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuiaTuristico implements Serializable {

    private String identificacion;
    private String nombre;
    private String lenguajes;
    private int experienciaAnios;

}
