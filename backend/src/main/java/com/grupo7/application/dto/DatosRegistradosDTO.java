package com.grupo7.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grupo7.application.dto.SerieTemporalDTO;
import java.time.LocalDateTime;
import java.util.List;

// DTO que agrupa los datos registrados de un evento sísmico.
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Prevents Hibernate proxy issues
public class DatosRegistradosDTO {
    private LocalDateTime fechaHoraOcurrencia; 
    private Double valorMagnitud;             
    private String alcanceSismoNombre;        
    private String clasificacionSismoNombre;  
    private String origenDeGeneracionNombre;  
    private List<SerieTemporalDTO> seriesTemporales; 

    // Constructor actualizado
    public DatosRegistradosDTO(LocalDateTime fechaHoraOcurrencia, Double valorMagnitud,
                               String alcanceSismoNombre, String clasificacionSismoNombre,
                               String origenDeGeneracionNombre, List<SerieTemporalDTO> seriesTemporales) {
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
        this.valorMagnitud = valorMagnitud;
        this.alcanceSismoNombre = alcanceSismoNombre;
        this.clasificacionSismoNombre = clasificacionSismoNombre;
        this.origenDeGeneracionNombre = origenDeGeneracionNombre;
        this.seriesTemporales = seriesTemporales;
    }

    // Getters
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

    public List<SerieTemporalDTO> getSeriesTemporales() {
        return seriesTemporales;
    }
}
