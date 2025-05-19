package com.example.demo;

public class SerieTemporal {
    int condicionAlarma;
    int fechaHoraInicioRegistroMuestra;
    int fechaHoraRegistro;
    int frecuenciaMuestreo;

    public SerieTemporal(int condicionAlarma, int fechaHoraInicioRegistroMuestra, int fechaHoraRegistro, int frecuenciaMuestreo) {
        this.condicionAlarma = condicionAlarma;
        this.fechaHoraInicioRegistroMuestra = fechaHoraInicioRegistroMuestra;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.frecuenciaMuestreo = frecuenciaMuestreo;
    }
    
    public void getDatos() {
        // anda a saber q va aca
    }
}
