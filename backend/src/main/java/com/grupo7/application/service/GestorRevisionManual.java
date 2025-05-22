package com.grupo7.application.service;

import org.springframework.stereotype.Service;
import com.grupo7.application.dto.DatosPrincipalesDTO;
import com.grupo7.application.dto.DatosRegistradosDTO;
import com.grupo7.application.entity.Empleado;
import com.grupo7.application.entity.EventoSismico;
import com.grupo7.application.entity.Estado;
import com.grupo7.application.repository.EmpleadoRepository;
import com.grupo7.application.repository.EventoSismicoRepository;
import com.grupo7.application.repository.EstadoRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class GestorRevisionManual {

    private final EventoSismicoRepository eventoSismicoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final ObjectMapper objectMapper;
    private final EstadoRepository estadoRepository;

    private List<DatosPrincipalesDTO> datosPrincipales;
    private Estado punteroAutodetectado;
    private Estado punteroPendienteRevision;
    private Estado punteroBloqueadoEnRevision;
    private Estado punteroRechazado;
    private LocalDateTime fechaHoraActual;
    private EventoSismico eventoSismicoSeleccionado;
    private Empleado empleadoActual; // This will hold the current employee for actions

    public GestorRevisionManual(EventoSismicoRepository eventoSismicoRepository, EmpleadoRepository empleadoRepository, ObjectMapper objectMapper, EstadoRepository estadoRepository) {
        this.eventoSismicoRepository = eventoSismicoRepository;
        this.empleadoRepository = empleadoRepository;
        this.objectMapper = objectMapper;
        this.estadoRepository = estadoRepository;

        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        this.datosPrincipales = new ArrayList<>();
        this.initializeStatePointers();
        this.empleadoActual = obtenerEmpleadoActual(); // Initialize empleadoActual here or when needed
    }

    private void initializeStatePointers() {
        this.punteroAutodetectado = estadoRepository.findByAmbitoAndNombreEstado("EventoSismico", "AutoDetectado")
                                            .orElseThrow(() -> new IllegalStateException("Estado 'Autodetectado' (EventoSismico) no encontrado en la base de datos."));
        this.punteroPendienteRevision = estadoRepository.findByAmbitoAndNombreEstado("EventoSismico", "PendienteRevision")
                                                .orElseThrow(() -> new IllegalStateException("Estado 'PendienteRevision' (EventoSismico) no encontrado en la base de datos."));
        this.punteroBloqueadoEnRevision = estadoRepository.findByAmbitoAndNombreEstado("EventoSismico", "BloqueadoEnRevision")
                                                    .orElseThrow(() -> new IllegalStateException("Estado 'BloqueadoEnRevision' (EventoSismico) no encontrado en la base de datos."));
        this.punteroRechazado = estadoRepository.findByAmbitoAndNombreEstado("EventoSismico", "Rechazado")
                                        .orElseThrow(() -> new IllegalStateException("Estado 'Rechazado' (EventoSismico) no encontrado en la base de datos."));
    }

    public void registrarRevisionManual() {
        buscarEventosSismicosNoRevisados();
    }

    public String buscarEventosSismicosNoRevisados() {
        List<EventoSismico> eventosFiltrados = eventoSismicoRepository.findByEstadoActualIn(
            Arrays.asList(punteroAutodetectado, punteroPendienteRevision)
        );

        this.datosPrincipales.clear();
        for (EventoSismico eve : eventosFiltrados) {
            this.datosPrincipales.add(new DatosPrincipalesDTO(
                eve.getId(),
                eve.getFechaHoraOcurrencia(),
                eve.getLatitudEpicentro(),
                eve.getLongitudEpicentro(),
                eve.getLatitudHipocentro(),
                eve.getLongitudHipocentro()
            ));
        }

        ordenarPorFechaHoraOcurrencia();

        try {
            return objectMapper.writeValueAsString(this.datosPrincipales);
        } catch (Exception e) {
            System.err.println("Error converting datosPrincipales to JSON: " + e.getMessage());
            e.printStackTrace();
            return "[]";
        }
    }

    public void ordenarPorFechaHoraOcurrencia() {
        datosPrincipales.sort(Comparator.comparing(DatosPrincipalesDTO::getFechaHoraOcurrencia));
    }
// Modified to operate on the internally selected event
    public void bloquearEventoSismicoSeleccionado() {
        fechaHoraActual = getFechaHoraActual();
        if (eventoSismicoSeleccionado != null && punteroBloqueadoEnRevision != null && empleadoActual != null) {
            eventoSismicoSeleccionado.bloquearPorRevision(punteroBloqueadoEnRevision, fechaHoraActual, empleadoActual);
            eventoSismicoRepository.save(eventoSismicoSeleccionado);
        } else {
            // Instead of just printing, throw an exception to signal the error
            String errorMessage = "Error: ";
            if (eventoSismicoSeleccionado == null) {
                errorMessage += "No hay evento sísmico seleccionado. ";
            }
            if (punteroBloqueadoEnRevision == null) {
                errorMessage += "El estado 'BloqueadoEnRevision' no está inicializado. ";
            }
            if (empleadoActual == null) {
                errorMessage += "No hay un empleado actual definido. ";
            }
            System.err.println(errorMessage + "Check initialization."); // Still good to log server-side
            throw new IllegalStateException(errorMessage.trim()); // Throw the exception
        }
    }

    public LocalDateTime getFechaHoraActual() {
        return LocalDateTime.now();
    }

    public DatosRegistradosDTO buscarDatosRegistrados() {
        if (eventoSismicoSeleccionado == null) return null;
        return eventoSismicoSeleccionado.buscarDatosRegistrados();
    }

    public void ordenarPorEstacionSismologica() {
        // Implementar lógica de ordenamiento por estación sismológica
    }

    // Modified to operate on the internally selected event
    public void actualizarEventoSismicoARechazado() {
        empleadoActual = obtenerEmpleadoActual();

        if (eventoSismicoSeleccionado != null && punteroRechazado != null && empleadoActual != null) {
            eventoSismicoSeleccionado.rechazarEventoSismico(getFechaHoraActual(), punteroRechazado, empleadoActual);
            eventoSismicoRepository.save(eventoSismicoSeleccionado);
        } else {
            System.err.println("Error: Evento Sismico Seleccionado, Rechazado state, or Empleado Actual is null. Check initialization.");
        }
    }

    private Empleado obtenerEmpleadoActual() {
        Optional<Empleado> employee = empleadoRepository.findById(1L);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            Empleado defaultEmployee = new Empleado("Doe", "john.doe@example.com", "John", 123456789L);
            return empleadoRepository.save(defaultEmployee);
        }
    }

    public void tomarSeleccionEventoSismico(Long eventId) {
        this.eventoSismicoSeleccionado = eventoSismicoRepository.findById(eventId)
                                            .orElse(null);
    }

    public EventoSismico getEventoSismicoSeleccionado() {
        return eventoSismicoSeleccionado;
    }

    public void tomarNoVisualizacion() {
        // TODO: implementar
    }

    public void tomarRechazoModificacion() {
        // TODO: implementar
    }

    public boolean validarDatosSismicos(String accion) {
        if (eventoSismicoSeleccionado == null) return false;
        if (eventoSismicoSeleccionado.getMagnitudRitcher() == null) return false;
        if (eventoSismicoSeleccionado.getAlcanceSismo() == null) return false;
        if (eventoSismicoSeleccionado.getOrigenDeGeneracion() == null) return false;
        if (accion == null) return false;
        accion = accion.trim().toLowerCase();
        return accion.equals("rechazar evento") || (accion.equals("aceptar evento"));
    }

    public static GestorRevisionManual crearGestorConEventosAleatorios(EventoSismicoRepository eventoSismicoRepository, EmpleadoRepository empleadoRepository, ObjectMapper objectMapper, EstadoRepository estadoRepository) {
        GestorRevisionManual gestor = new GestorRevisionManual(eventoSismicoRepository, empleadoRepository, objectMapper, estadoRepository);

        Estado autoDetectadoState = estadoRepository.findByAmbitoAndNombreEstado("EventoSismico", "AutoDetectado")
                                            .orElseThrow(() -> new IllegalStateException("Estado 'AutoDetectado' no encontrado en la base de datos para la generación de eventos aleatorios."));

        Empleado defaultEmployeeForRandomEvents = gestor.obtenerEmpleadoActual();

        for (int i = 0; i < 10; i++) {
            EventoSismico newEvent = new EventoSismico();
            newEvent.setFechaHoraOcurrencia(LocalDateTime.now().minusHours(i));
            newEvent.setEstadoActual(autoDetectadoState);
            eventoSismicoRepository.save(newEvent);
        }
        return gestor;
    }

    public List<EventoSismico> obtenerEventosEnRevision() {
        Estado pendienteRevision = estadoRepository.findByAmbitoAndNombreEstado("EventoSismico", "PendienteRevision")
                                            .orElseThrow(() -> new IllegalStateException("Estado 'PendienteRevision' no encontrado."));
        Estado bloqueadoEnRevision = estadoRepository.findByAmbitoAndNombreEstado("EventoSismico", "BloqueadoEnRevision")
                                                .orElseThrow(() -> new IllegalStateException("Estado 'BloqueadoEnRevision' no encontrado."));

        return eventoSismicoRepository.findByEstadoActualIn(Arrays.asList(pendienteRevision, bloqueadoEnRevision));
    }

    public List<EventoSismico> obtenerEventosRechazados() {
        Estado rechazado = estadoRepository.findByAmbitoAndNombreEstado("EventoSismico", "Rechazado")
                                .orElseThrow(() -> new IllegalStateException("Estado 'Rechazado' no encontrado."));

        return eventoSismicoRepository.findByEstadoActualIn(Arrays.asList(rechazado));
    }
}
