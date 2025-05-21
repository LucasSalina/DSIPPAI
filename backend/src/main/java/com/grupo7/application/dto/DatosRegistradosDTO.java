package com.grupo7.application.dto;

import com.grupo7.application.entity.AlcanceSismo;
import com.grupo7.application.entity.ClasificacionSismo;
import com.grupo7.application.entity.OrigenDeGeneracion;
import com.grupo7.application.entity.SerieTemporal;

import java.util.ArrayList;

// DTO que agrupa los datos registrados de un evento sísmico, incluyendo alcance, clasificación, origen y series temporales.
public class DatosRegistradosDTO {
    private String alcanceSismo;
    private String clasificacionSismo;
    private String origenDeGeneracion;
    private ArrayList<Object> seriesTemporales;

    // Constructor simple, sin lógica adicional.
    public DatosRegistradosDTO(String alcanceSismo, String clasificacionSismo,
                               String origenDeGeneracion, ArrayList<Object> seriesTemporales) {
        this.alcanceSismo = alcanceSismo;
        this.clasificacionSismo = clasificacionSismo;
        this.origenDeGeneracion = origenDeGeneracion;
        this.seriesTemporales = seriesTemporales;
    }

}
