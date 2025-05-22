package com.grupo7.application.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.grupo7.application.service.SismografoService;
import org.springframework.stereotype.Component;

@Component
public class SerieTemporal {
    private int condicionAlarma;
    private LocalDateTime fechaHoraInicioRegistroMuestra;
    private LocalDateTime fechaHoraRegistro;
    private float frecuenciaMuestreo;
    private ArrayList<MuestraSismica> muestrasSismicas;

    public SerieTemporal() {
        this.condicionAlarma = 0;
        this.fechaHoraInicioRegistroMuestra = LocalDateTime.now();
        this.fechaHoraRegistro = LocalDateTime.now();
        this.frecuenciaMuestreo = 0;
        this.muestrasSismicas = new ArrayList<MuestraSismica>();
    }

    public SerieTemporal(int condicionAlarma, LocalDateTime fechaHoraInicioRegistroMuestra, LocalDateTime fechaHoraRegistro,
    float frecuenciaMuestreo, ArrayList<MuestraSismica> muestrasSismicas) {
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
        for (Sismografo sismografo : SismografoService.findAll()) {
            ArrayList<String> datosEstacion = sismografo.esTuSerieTemporal(this);
            if (datosEstacion != null) {
                datos.add(datosEstacion); // Se agrega la info de la estación
                break; // Solo una estación por serie temporal
            }
        }
        return datos;
    }
}
