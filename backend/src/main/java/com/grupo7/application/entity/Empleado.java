package com.grupo7.application.entity;

import org.springframework.stereotype.Component;

@Component
public class Empleado {
    private String apellido;
    private String mail;
    private String nombre;
    private Long telefono;

    public Empleado() {
        this.apellido = "";
        this.mail = "";
        this.nombre = "";
        this.telefono = 0L;
    }

    public Empleado(String apellido, String mail, String nombre, Long telefono) {
        this.apellido = apellido;
        this.mail = mail;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
}
