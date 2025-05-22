package com.grupo7.application.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "serie_temporal")
public class SerieTemporal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int condicionAlarma;

    private LocalDateTime fechaHoraInicioRegistroMuestra;

    private LocalDateTime fechaHoraRegistro;

    private float frecuenciaMuestreo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "serie_temporal_id")
    private List<MuestraSismica> muestrasSismicas = new ArrayList<>();

    public SerieTemporal() {
        this.condicionAlarma = 0;
        this.fechaHoraInicioRegistroMuestra = LocalDateTime.now();
        this.fechaHoraRegistro = LocalDateTime.now();
        this.frecuenciaMuestreo = 0;
    }

    public SerieTemporal(int condicionAlarma, LocalDateTime fechaHoraInicioRegistroMuestra,
                         LocalDateTime fechaHoraRegistro, float frecuenciaMuestreo,
                         List<MuestraSismica> muestrasSismicas) {
        this.condicionAlarma = condicionAlarma;
        this.fechaHoraInicioRegistroMuestra = fechaHoraInicioRegistroMuestra;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.frecuenciaMuestreo = frecuenciaMuestreo;
        this.muestrasSismicas = muestrasSismicas;
    }

    public Long getId() {
        return id;
    }

    public int getCondicionAlarma() {
        return condicionAlarma;
    }

    public void setCondicionAlarma(int condicionAlarma) {
        this.condicionAlarma = condicionAlarma;
    }

    public LocalDateTime getFechaHoraInicioRegistroMuestra() {
        return fechaHoraInicioRegistroMuestra;
    }

    public void setFechaHoraInicioRegistroMuestra(LocalDateTime fechaHoraInicioRegistroMuestra) {
        this.fechaHoraInicioRegistroMuestra = fechaHoraInicioRegistroMuestra;
    }

    public LocalDateTime getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }

    public void setFechaHoraRegistro(LocalDateTime fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }

    public float getFrecuenciaMuestreo() {
        return frecuenciaMuestreo;
    }

    public void setFrecuenciaMuestreo(float frecuenciaMuestreo) {
        this.frecuenciaMuestreo = frecuenciaMuestreo;
    }

    public List<MuestraSismica> getMuestrasSismicas() {
        return muestrasSismicas;
    }

    public void setMuestrasSismicas(List<MuestraSismica> muestrasSismicas) {
        this.muestrasSismicas = muestrasSismicas;
    }

    public ArrayList<Object> getDatos() {
        ArrayList<Object> datos = new ArrayList<>();
        for (MuestraSismica muestra : muestrasSismicas) {
            datos.add(muestra.getDatos());
        }
        return datos;
    }
}
