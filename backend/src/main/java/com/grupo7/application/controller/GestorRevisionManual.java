package com.grupo7.application.controller;

// Dependencies

import com.grupo7.application.dto.DatosPrincipalesDTO;
import com.grupo7.application.entity.EventoSismico;
import com.grupo7.application.entity.Estado;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

public class GestorRevisionManual {

    private ArrayList<EventoSismico> eventoSismico;
    // private ArrayList<Estado> estadosSistema;
    private ArrayList<DatosPrincipalesDTO> datosPrincipales;
    private Estado punteroAutodetectado;
    private Estado punteroPendienteRevision;
    private Estado punteroBloqueadoEnRevision;
    private LocalTime fechaHoraActual;

    private EventoSismico eventoSismicoSeleccionado;

    public GestorRevisionManual(EventoSismico eventoSismico, Estado estadosSistema) {
        this.eventoSismico = new ArrayList<>();
        // this.estadosSistema = new ArrayList<>();
    }

    public void registrarRevisionManual() {

    }

    public void buscarEventosSismicosNoRevisados() {
        this.buscarEstadoNoRevisadosOAutodetectado();

        for (EventoSismico eve : eventoSismico) {
            if (eve.esAutoDetectadoOPendienteRevision()) {
                this.datosPrincipales.add(eve.obtenerDatosPrincipales());
            }
        }
    }

    public void ordenarPorFechaHoraOcurrencia() {
        datosPrincipales.sort(Comparator.comparing(DatosPrincipalesDTO::getFechaHora));
    }

    public void bloquearEventoSismicoSeleccionado() {
        fechaHoraActual = getFechaHoraActual();
        for (Estado estado : Estado.getTodos()) {
            if (estado.sosBloqueadoEnRevision()) {
                punteroBloqueadoEnRevision = estado;
//              podria hacer eventoSeleccionado.bloquear(estado) y me ahorro un atributo
            }
        }
        eventoSismicoSeleccionado.bloquearPorRevision(punteroBloqueadoEnRevision, fechaHoraActual);

    }

    public LocalTime getFechaHoraActual() {
        return LocalTime.now();
    }

    public void buscarDatosRegistrados() {
        this.eventoSismicoSeleccionado.buscarDatosRegistrados();
    }

    public void ordenarPorEstacionSismologica() {

    }

    public void validarDatosSismicos() {

    }

    public void actualizarEventoSismicoARechazado() {

    }

    // Métodos faltantes agregados:

    public void buscarEstadoNoRevisadosOAutodetectado() {
        for (Estado estado : Estado.getTodos()) {
            if (estado.esAmbitoEventoSismico() & estado.sosAutoDetectado()) {
                this.punteroAutodetectado = estado;
            }
            if (estado.esAmbitoEventoSismico() & estado.sosPendienteRevision()) {
                this.punteroPendienteRevision = estado;
            }
        }

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
