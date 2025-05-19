package com.example.demo;

public class Sismografo {
    int fechaAdquisicion;
    int identificadorSismografo;
    int numeroDeSerie;
    Estado estadoActual;
    EstacionSismologica estacionSismologica;

    public Sismografo(int fechaAdquisicion, int identificadorSismografo, int numeroDeSerie) {
        this.fechaAdquisicion = fechaAdquisicion;
        this.identificadorSismografo = identificadorSismografo;
        this.numeroDeSerie = numeroDeSerie;
    }

    public void setEstadoActual(Estado estadoNuevo) {
        this.estadoActual = estadoNuevo;
    }
}
