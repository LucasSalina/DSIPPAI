package com.grupo7.application.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MuestraSismica {
    LocalDateTime fechaHoraMuestra;
    private List<DetalleMuestraSismica> detallesMuestraSismica;

    public MuestraSismica() {
        this.fechaHoraMuestra = LocalDateTime.now();;
        this.detallesMuestraSismica = new ArrayList<>();
    }

    public MuestraSismica(LocalDateTime fechaHoraMuestra) {
        this.fechaHoraMuestra = fechaHoraMuestra;
    }

    public LocalDateTime getFechaHoraMuestra() {
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
