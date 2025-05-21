package com.grupo7.application.entity;

import com.grupo7.application.repository.RepositorioDeSismografos;

import java.util.ArrayList;

// Representa un sismógrafo, con su información de adquisición, estado y las series temporales que ha registrado.
public class Sismografo {
    private int fechaAdquisicion;
    private int identificadorSismografo;
    private int numeroDeSerie;
    private Estado estadoActual;
    private EstacionSismologica estacionSismologica;
    private ArrayList<SerieTemporal> seriesTemporales;

    public Sismografo(int fechaAdquisicion, int identificadorSismografo, int numeroDeSerie) {
        this.fechaAdquisicion = fechaAdquisicion;
        this.identificadorSismografo = identificadorSismografo;
        this.numeroDeSerie = numeroDeSerie;
//        Agrega al atributo de clase de ReposotorioDeSismografos el sismografo recien creado
        RepositorioDeSismografos.agregar(this);
    }

    // esTuSerieTemporal: verifica si la serie temporal pertenece a este sismógrafo y retorna los datos de la estación asociada.
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
