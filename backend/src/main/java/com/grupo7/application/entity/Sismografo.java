package com.grupo7.application.entity;

import com.grupo7.application.repository.RepositorioDeSismografos;
import com.grupo7.application.service.SismografoService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Sismografo {
    private int fechaAdquisicion;
    private int identificadorSismografo;
    private int numeroDeSerie;
    private Estado estadoActual;
    private EstacionSismologica estacionSismologica;
    private ArrayList<SerieTemporal> seriesTemporales;
    private static SismografoService sismografoService;

    public Sismografo(int fechaAdquisicion, int identificadorSismografo, int numeroDeSerie) {
        this.fechaAdquisicion = fechaAdquisicion;
        this.identificadorSismografo = identificadorSismografo;
        this.numeroDeSerie = numeroDeSerie;
//        Agrega al atributo de clase de ReposotorioDeSismografos el sismografo recien creado
        RepositorioDeSismografos.agregar(this);
    }

    public ArrayList<String> esTuSerieTemporal(SerieTemporal serie) {
        if (seriesTemporales.contains(serie)) {
            return estacionSismologica.getDatosEstacion();
        }
//        SI ALGO SE ROMPE ES POR ESTO
        return null;
    }

    public void setEstadoActual(Estado estadoNuevo) {
        this.estadoActual = estadoNuevo;
    }
}
