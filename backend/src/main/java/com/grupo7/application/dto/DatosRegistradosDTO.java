package com.grupo7.application.dto;

import com.grupo7.application.entity.AlcanceSismo; // You can remove this if only using String names
import com.grupo7.application.entity.ClasificacionSismo; // You can remove this if only using String names
import com.grupo7.application.entity.MagnitudRitcher; // Import MagnitudRitcher
import com.grupo7.application.entity.OrigenDeGeneracion; // You can remove this if only using String names
import com.grupo7.application.entity.SerieTemporal;

import java.time.LocalDateTime; // Import LocalDateTime
import java.util.ArrayList;

// DTO que agrupa los datos registrados de un evento sísmico.
public class DatosRegistradosDTO {
    private LocalDateTime fechaHoraOcurrencia; // Added for the date/time of occurrence
    private Double valorMagnitud;             // Changed to Double for magnitude value
    private String alcanceSismoNombre;        // Renamed to clarify it's the name
    private String clasificacionSismoNombre;  // Added for classification name
    private String origenDeGeneracionNombre;  // Renamed to clarify it's the name
    private ArrayList<Object> seriesTemporales; // Keep as ArrayList<Object> for now

    // Constructor actualizado para reflejar los datos registrados completos
    public DatosRegistradosDTO(LocalDateTime fechaHoraOcurrencia, Double valorMagnitud,
                               String alcanceSismoNombre, String clasificacionSismoNombre,
                               String origenDeGeneracionNombre, ArrayList<Object> seriesTemporales) {
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
        this.valorMagnitud = valorMagnitud;
        this.alcanceSismoNombre = alcanceSismoNombre;
        this.clasificacionSismoNombre = clasificacionSismoNombre;
        this.origenDeGeneracionNombre = origenDeGeneracionNombre;
        this.seriesTemporales = seriesTemporales;
    }

    // --- Getters for the DTO fields (add these if you need to access the data) ---
    public LocalDateTime getFechaHoraOcurrencia() {
        return fechaHoraOcurrencia;
    }

    public Double getValorMagnitud() {
        return valorMagnitud;
    }

    public String getAlcanceSismoNombre() {
        return alcanceSismoNombre;
    }

    public String getClasificacionSismoNombre() {
        return clasificacionSismoNombre;
    }

    public String getOrigenDeGeneracionNombre() {
        return origenDeGeneracionNombre;
    }

    public ArrayList<Object> getSeriesTemporales() {
        return seriesTemporales;
    }
}