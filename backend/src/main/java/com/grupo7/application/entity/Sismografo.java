package com.grupo7.application.entity;

import java.util.ArrayList;

public class Sismografo {
    private int fechaAdquisicion;
    private int identificadorSismografo;
    private int numeroDeSerie;
    private Estado estadoActual;
    private EstacionSismologica estacionSismologica;
    private ArrayList<SerieTemporal> seriesTemporales;
    private static ArrayList<Sismografo> sismografoRepository = new ArrayList<>();

    public Sismografo(int fechaAdquisicion, int identificadorSismografo, int numeroDeSerie) {
        this.fechaAdquisicion = fechaAdquisicion;
        this.identificadorSismografo = identificadorSismografo;
        this.numeroDeSerie = numeroDeSerie;
        sismografoRepository.add(this);
    }

    public static ArrayList<Sismografo> getSismografoRepository() {
        return sismografoRepository;
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
