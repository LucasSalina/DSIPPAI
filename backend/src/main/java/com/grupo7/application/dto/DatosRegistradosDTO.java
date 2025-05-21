package com.grupo7.application.dto;

import com.grupo7.application.entity.AlcanceSismo;
import com.grupo7.application.entity.ClasificacionSismo;
import com.grupo7.application.entity.OrigenDeGeneracion;
import com.grupo7.application.entity.SerieTemporal;

import java.util.ArrayList;

public class DatosRegistradosDTO {
    private AlcanceSismo alcanceSismo;
    private ClasificacionSismo clasificacionSismo;
    private OrigenDeGeneracion origenDeGeneracion;
    private ArrayList<SerieTemporal> seriesTemporales;
}
