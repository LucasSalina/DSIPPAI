package com.example.demo;

public class Estado {
  String ambito;
  String nombreEstado;

  public Estado(String ambito, String nombreEstado) {
    this.ambito = ambito;
    this.nombreEstado = nombreEstado;
  }

  public String getAmbito() {
    return ambito;
  }

  public String getNombreEstado() {
    return nombreEstado;
  }

  public String setNombreEstado(String nombreEstado) {
    this.nombreEstado = nombreEstado;
    return nombreEstado;
  }

  public String setAmbito(String ambito) {
    this.ambito = ambito;
    return ambito;
  }
}