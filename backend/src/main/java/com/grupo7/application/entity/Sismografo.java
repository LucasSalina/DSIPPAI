package com.grupo7.application.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sismografo")
public class Sismografo {

    @Id
    @Column(name = "identificador")
    private String identificador;

    @Column(name = "id_adquisicion")
    private Integer idAdquisicion;

    @Column(name = "nro_serie")
    private String nroSerie;

    @Column(name = "codigo_estacion")
    private String codigoEstacion;

    public Sismografo() {}

    // Getters y setters

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Integer getIdAdquisicion() {
        return idAdquisicion;
    }

    public void setIdAdquisicion(Integer idAdquisicion) {
        this.idAdquisicion = idAdquisicion;
    }

    public String getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }

    public String getCodigoEstacion() {
        return codigoEstacion;
    }

    public void setCodigoEstacion(String codigoEstacion) {
        this.codigoEstacion = codigoEstacion;
    }
}
