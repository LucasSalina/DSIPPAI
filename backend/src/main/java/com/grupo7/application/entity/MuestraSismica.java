package com.grupo7.application.entity;

import java.util.List;

public class MuestraSismica {
    int fechaHoraMuestra;
    List<DetalleMuestraSismica> detalleMuestraSismica;
    
    public MuestraSismica(int fechaHoraMuestra) {
        this.fechaHoraMuestra = fechaHoraMuestra;
    }

    public int getFechaHoraMuestra() {
        return fechaHoraMuestra;
    }

    public void crearDetalleMuestra() {
        
    }
    
    public void getDatos() {
        // anda a saber q va aca
    }
}
