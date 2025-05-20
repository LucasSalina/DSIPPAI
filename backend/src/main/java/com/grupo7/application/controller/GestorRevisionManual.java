package com.grupo7.application.controller;

// Dependencies

import com.grupo7.application.entity.EventoSismico;
import com.grupo7.application.entity.Estado;

import java.time.LocalTime;
import java.util.ArrayList;

public class GestorRevisionManual {

    private ArrayList<EventoSismico> eventoSismico;
    // private ArrayList<Estado> estadosSistema;

    public GestorRevisionManual(EventoSismico eventoSismico, Estado estadosSistema) {
        this.eventoSismico = new ArrayList<>();
        // this.estadosSistema = new ArrayList<>();
    }

    public void registrarRevisionManual() {

    }

    public ArrayList<EventoSismico> buscarEventosSismicosNoRevisados() {
        ArrayList<EventoSismico> eventosNoRevisados = new ArrayList<>();
        for (EventoSismico eve : eventoSismico) {
            if (eve.esAutoDetectadoOPendienteRevision()) {
                eventosNoRevisados.add(eve);
            }
        }
        return eventosNoRevisados;
    }

    public void ordenarPorFechaHoraOcurrencia() {

    }

    public void bloquearEventoSismicoSeleccionado() {

    }

    public LocalTime getFechaHoraActual() {
        return LocalTime.now();
    }

    public void buscarDatosRegistrados() {

    }

    public void ordenarPorEstacionSismologica() {

    }

    public void validarDatosSismicos() {

    }

    public void actualizarEventoSismicoARechazado() {

    }

    // Métodos faltantes agregados:

    public void buscarEstadoNoRevisadosOAutodetectado() {
        // TODO: implementar
    }

    public void tomarSeleccionEventoSismico() {
        // TODO: implementar
    }

    public void tomarNoVisualizacion() {
        // TODO: implementar
    }

    public void tomarRechazoModificacion() {
        // TODO: implementar
    }
}
