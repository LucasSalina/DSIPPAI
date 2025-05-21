package com.grupo7.application.entity;

import com.grupo7.application.repository.RepositorioDeSismografos;

import java.util.ArrayList;

// Una serie temporal representa una secuencia de muestras sísmicas tomadas por un sismógrafo en un periodo.
public class SerieTemporal {
    private int condicionAlarma;
    private int fechaHoraInicioRegistroMuestra;
    private int fechaHoraRegistro;
    private int frecuenciaMuestreo;
    private ArrayList<MuestraSismica> muestrasSismicas;

    public SerieTemporal(int condicionAlarma, int fechaHoraInicioRegistroMuestra, int fechaHoraRegistro,
                         int frecuenciaMuestreo, ArrayList<MuestraSismica> muestrasSismicas) {
        this.condicionAlarma = condicionAlarma;
        this.fechaHoraInicioRegistroMuestra = fechaHoraInicioRegistroMuestra;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.frecuenciaMuestreo = frecuenciaMuestreo;
        this.muestrasSismicas = muestrasSismicas;

    }
    
    // Primer loop | evento tiene varias series
    // getDatos: retorna una estructura con los datos de las muestras y la información de la estación asociada.
    public ArrayList<Object> getDatos() {
        ArrayList<Object> datos = new ArrayList<>();
        // Agregar los datos de cada muestra sismica
        for (MuestraSismica muestraSismica : muestrasSismicas) {
            datos.add(muestraSismica.getDatos());
        }
        // Buscar la estación sismológica asociada a esta serie temporal
        for (Sismografo sismografo : RepositorioDeSismografos.obtenerTodos()) {
            ArrayList<String> datosEstacion = sismografo.esTuSerieTemporal(this);
            if (datosEstacion != null) {
                datos.add(datosEstacion); // Se agrega la info de la estación
                break; // Solo una estación por serie temporal
            }
        }
        return datos;
    }
}
