package com.example.demo;
import java.time.LocalTime;
import java.util.ArrayList;

public class GestorRevisionManual {

    private ArrayList<EventoSismico> eventoSismico;
    private ArrayList<Estado> estadosSistema;

    public GestorRevisionManual(EventoSismico eventoSismico, Estado estadosSistema) {
        this.eventoSismico = new ArrayList<>();
        this.estadosSistema = new ArrayList<>();
    }

    public void registrarRevisionManual() {

    }

    public ArrayList<EventoSismico> buscarEventosSismicosNoRevisados() {
        ArrayList<EventoSismico> eventosNoRevisados = new ArrayList<>();
        for (EventoSismico eve : eventoSismico) {
            if (eve.esAutoDetectadoOPendienteRevision() == true) {
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

}
