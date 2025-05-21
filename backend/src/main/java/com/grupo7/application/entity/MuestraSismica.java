package com.grupo7.application.entity;

import java.util.ArrayList;
import java.util.List;

// Representa una muestra tomada por un sismógrafo en un instante, compuesta por varios detalles.
public class MuestraSismica {
    int fechaHoraMuestra;
    private List<DetalleMuestraSismica> detallesMuestraSismica;
    
    public MuestraSismica(int fechaHoraMuestra) {
        this.fechaHoraMuestra = fechaHoraMuestra;
    }

    public int getFechaHoraMuestra() {
        return fechaHoraMuestra;
    }

    public void crearDetalleMuestra() {
        
    }
    
    // getDatos: recorre los detalles y retorna solo los que contienen información relevante.
    public ArrayList<Object> getDatos() {
        ArrayList<Object> detallesDeMuestras = new ArrayList<>();
        for (DetalleMuestraSismica detalleMuestraSismica : detallesMuestraSismica) {
            ArrayList<Object> datos = detalleMuestraSismica.getDatos();
            if (!datos.isEmpty()) {
                detallesDeMuestras.add(datos);
            }
        }
        return detallesDeMuestras;
    }
}
