package com.grupo7.application.service;

// Dependencies

import org.springframework.stereotype.Service;

import com.grupo7.application.dto.DatosPrincipalesDTO;
import com.grupo7.application.dto.DatosRegistradosDTO;
import com.grupo7.application.entity.Empleado;
import com.grupo7.application.entity.EventoSismico;
import com.grupo7.application.entity.Estado;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

@Service
public class GestorRevisionManual {
    // Lista de eventos sísmicos a gestionar
    private ArrayList<EventoSismico> eventoSismico;
    // Lista de datos principales de los eventos sísmicos
    private ArrayList<DatosPrincipalesDTO> datosPrincipales;
    // Punteros a los diferentes estados posibles de un evento sísmico
    private Estado punteroAutodetectado;
    private Estado punteroPendienteRevision;
    private Estado punteroBloqueadoEnRevision;
    private Estado punteroRechazado;
    // Fecha y hora actual utilizada en los procesos
    private LocalDateTime fechaHoraActual;
    // Evento sísmico seleccionado para revisión
    private EventoSismico eventoSismicoSeleccionado;
    private Empleado empleadoActual;

    // Constructor: inicializa la lista de eventos sísmicos
    public GestorRevisionManual(EventoSismico eventoSismico, Estado estadosSistema) {
        this.eventoSismico = new ArrayList<>();
        // this.estadosSistema = new ArrayList<>();
    }

    // Método para registrar la revisión manual de un evento sísmico
    public void registrarRevisionManual() {
        // Implementar lógica de registro de revisión manual
    }

    // Busca eventos sísmicos que no han sido revisados
    public void buscarEventosSismicosNoRevisados() {
        this.buscarEstadoNoRevisadosOAutodetectado();
        // Itera sobre los eventos sísmicos y filtra los que están autodetectados o pendientes de revisión
        for (EventoSismico eve : eventoSismico) {
            if (eve.esAutoDetectadoOPendienteRevision()) {
                this.datosPrincipales.add(eve.obtenerDatosPrincipales());
            }
        }
    }

    // Ordena la lista de datos principales por fecha y hora de ocurrencia
    public void ordenarPorFechaHoraOcurrencia() {
        datosPrincipales.sort(Comparator.comparing(DatosPrincipalesDTO::getFechaHora));
    }

    // Bloquea el evento sísmico seleccionado para su revisión
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

    // Obtiene la fecha y hora actual del sistema
    public LocalDateTime getFechaHoraActual() {
        return LocalDateTime.now();
    }

    // Busca los datos registrados del evento sísmico seleccionado
    public DatosRegistradosDTO buscarDatosRegistrados() {
        if (eventoSismicoSeleccionado == null) return null;
        return eventoSismicoSeleccionado.buscarDatosRegistrados();
    }

    // Ordena los eventos sísmicos por estación sismológica (a implementar)
    public void ordenarPorEstacionSismologica() {
         // Implementar lógica de ordenamiento por estación sismológica
    }

    // Valida los datos sísmicos (a implementar)
    public void validarDatosSismicos() {
        // Implementar lógica de validación de datos sísmicos
    }

    // Actualiza el evento sísmico a estado rechazado (a implementar)
    public void actualizarEventoSismicoARechazado() {
        obtenerPunteroRechazado();
        obtenerEmpleadoActual();
        eventoSismicoSeleccionado.rechazarEventoSismico(getFechaHoraActual(), punteroRechazado, empleadoActual);
    }

    private void obtenerEmpleadoActual() {
    }

    // Busca los estados que no han sido revisados o que están autodetectados
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

    public void obtenerPunteroRechazado() {
        for (Estado estado : Estado.getTodos()) {
            if (estado.esAmbitoEventoSismico() & estado.esRechazado()) {
                punteroRechazado = estado;
            }
        }
    }

    // Toma la selección de un evento sísmico (a implementar)
    public void tomarSeleccionEventoSismico() {
        // TODO: implementar
    }

    // Toma la acción de no visualizar (a implementar)
    public void tomarNoVisualizacion() {
        // TODO: implementar
    }

    // Toma el rechazo de la modificación (a implementar)
    public void tomarRechazoModificacion() {
        // TODO: implementar
    }

    // Valida que el evento seleccionado tenga magnitud, alcance y origen de generación, y que se haya seleccionado una acción válida.
    // La acción debe ser una de: "confirmar evento", "rechazar evento" o "solicitar evaluacion de experto".
    public boolean validarDatosSismicos(String accion) {
        if (eventoSismicoSeleccionado == null) return false;
        // Validar magnitud
        if (eventoSismicoSeleccionado.getMagnitudRitcher() == null) return false;
        // Validar alcance
        if (eventoSismicoSeleccionado.getAlcanceSismo() == null) return false;
        // Validar origen de generación
        if (eventoSismicoSeleccionado.getOrigenDeGeneracion() == null) return false;
        // Validar acción
        if (accion == null) return false;
        accion = accion.trim().toLowerCase();
        if (!(accion.equals("rechazar evento"))) {
            return false;
        }
        return true;
    }
}
