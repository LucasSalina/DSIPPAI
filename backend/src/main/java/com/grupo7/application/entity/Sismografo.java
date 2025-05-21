package com.grupo7.application.entity;

import com.grupo7.application.repository.RepositorioDeSismografos;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Sismografo {
    private LocalDateTime fechaAdquisicion;
    private int identificadorSismografo;
    private int numeroDeSerie;
    private Estado estadoActual;
    private EstacionSismologica estacionSismologica;
    private ArrayList<SerieTemporal> seriesTemporales;

    public Sismografo() {
        this.fechaAdquisicion = LocalDateTime.now();
        this.identificadorSismografo = 0;
        this.numeroDeSerie = 0;
        this.estadoActual = new Estado();
        this.estacionSismologica = new EstacionSismologica();
        this.seriesTemporales = new ArrayList<SerieTemporal>();

    }

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
