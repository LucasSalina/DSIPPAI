package com.example.demo;

public class EstacionSismologica {
    int codigoEstacion;
    String documentoCertificaionAdq;
    int fechaSolicitudCertificacion;
    int latitud;
    int longitud;
    String Nombre;
    int nroCertificacionAdquisicion;

    public EstacionSismologica(int codigoEstacion, String documentoCertificaionAdq, int fechaSolicitudCertificacion, int latitud, int longitud, String nombre, int nroCertificacionAdquisicion) {
        this.codigoEstacion = codigoEstacion;
        this.documentoCertificaionAdq = documentoCertificaionAdq;
        this.fechaSolicitudCertificacion = fechaSolicitudCertificacion;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
