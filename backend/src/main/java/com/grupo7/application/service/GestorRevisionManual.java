package com.grupo7.application.service;

import org.springframework.stereotype.Service;
import com.grupo7.application.dto.DatosPrincipalesDTO;
import com.grupo7.application.dto.DatosRegistradosDTO;
import com.grupo7.application.entity.Empleado;
import com.grupo7.application.entity.EventoSismico;
import com.grupo7.application.entity.Estado;
import com.grupo7.application.repository.EmpleadoRepository;
import com.grupo7.application.repository.EventoSismicoRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors; // Added for .stream().collect(Collectors.toList())

@Service
public class GestorRevisionManual {

    private final EventoSismicoRepository eventoSismicoRepository;
    private final EmpleadoRepository empleadoRepository;

    private List<DatosPrincipalesDTO> datosPrincipales;
    private Estado punteroAutodetectado;
    private Estado punteroPendienteRevision;
    private Estado punteroBloqueadoEnRevision;
    private Estado punteroRechazado;
    private LocalDateTime fechaHoraActual;
    private EventoSismico eventoSismicoSeleccionado;
    private Empleado empleadoActual;

    public GestorRevisionManual(EventoSismicoRepository eventoSismicoRepository, EmpleadoRepository empleadoRepository) {
        this.eventoSismicoRepository = eventoSismicoRepository;
        this.empleadoRepository = empleadoRepository;
        this.datosPrincipales = new ArrayList<>();
        // Initialize state pointers here if you want them immediately available
        this.buscarEstadoNoRevisadosOAutodetectado(); // Call it once during initialization
        this.obtenerPunteroRechazado(); // Initialize rechazado state
        // You might also want to initialize punteroBloqueadoEnRevision here
        for (Estado estado : Estado.getTodos()) {
            if (estado.sosBloqueadoEnRevision()) {
                this.punteroBloqueadoEnRevision = estado;
                break;
            }
        }
    }

    public void registrarRevisionManual() {
        buscarEventosSismicosNoRevisados();
    }

    public void buscarEventosSismicosNoRevisados() {
        // Ensure state pointers are initialized
        if (punteroAutodetectado == null || punteroPendienteRevision == null) {
            System.err.println("Error: Autodetected or Pending Review states are not initialized. Attempting to re-initialize.");
            this.buscarEstadoNoRevisadosOAutodetectado(); // Try to re-initialize if null
            if (punteroAutodetectado == null || punteroPendienteRevision == null) {
                System.err.println("Error: States still null after re-initialization. Cannot proceed.");
                return;
            }
        }

        List<EventoSismico> eventosFiltrados = eventoSismicoRepository.findByEstadoActualIn( // CHANGED HERE
            Arrays.asList(punteroAutodetectado, punteroPendienteRevision)
        );

        this.datosPrincipales.clear();
        for (EventoSismico eve : eventosFiltrados) {
            // This method `obtenerDatosPrincipales()` must exist in EventoSismico entity
            this.datosPrincipales.add(eve.obtenerDatosPrincipales());
        }
    }

    public void ordenarPorFechaHoraOcurrencia() {
        datosPrincipales.sort(Comparator.comparing(DatosPrincipalesDTO::getFechaHora));
    }

    public void bloquearEventoSismicoSeleccionado() {
        fechaHoraActual = getFechaHoraActual();
        // Ensure punteroBloqueadoEnRevision is initialized
        if (punteroBloqueadoEnRevision == null) {
            System.err.println("Error: BloqueadoEnRevision state not initialized. Attempting to initialize.");
            for (Estado estado : Estado.getTodos()) {
                if (estado.sosBloqueadoEnRevision()) {
                    punteroBloqueadoEnRevision = estado;
                    break;
                }
            }
            if (punteroBloqueadoEnRevision == null) {
                System.err.println("Error: BloqueadoEnRevision state still null after initialization. Cannot proceed.");
                return;
            }
        }

        if (eventoSismicoSeleccionado != null && punteroBloqueadoEnRevision != null) {
            // This method `bloquearPorRevision()` must exist in EventoSismico entity
            eventoSismicoSeleccionado.bloquearPorRevision(punteroBloqueadoEnRevision, fechaHoraActual);
            eventoSismicoRepository.save(eventoSismicoSeleccionado);
        } else {
            System.err.println("Error: Evento Sismico Seleccionado or BloqueadoEnRevision state is null.");
        }
    }

    public LocalDateTime getFechaHoraActual() {
        return LocalDateTime.now();
    }

    public DatosRegistradosDTO buscarDatosRegistrados() {
        if (eventoSismicoSeleccionado == null) return null;
        // This method `buscarDatosRegistrados()` must exist in EventoSismico entity
        return eventoSismicoSeleccionado.buscarDatosRegistrados();
    }

    public void ordenarPorEstacionSismologica() {
        // Implementar lógica de ordenamiento por estación sismológica
    }

    public void actualizarEventoSismicoARechazado() {
        // Ensure punteroRechazado is initialized
        if (punteroRechazado == null) {
            System.err.println("Error: Rechazado state not initialized. Attempting to initialize.");
            this.obtenerPunteroRechazado(); // Try to re-initialize
            if (punteroRechazado == null) {
                System.err.println("Error: Rechazado state still null after initialization. Cannot proceed.");
                return;
            }
        }

        empleadoActual = obtenerEmpleadoActual(); // Call the helper method

        if (eventoSismicoSeleccionado != null && punteroRechazado != null && empleadoActual != null) {
            // This method `rechazarEventoSismico()` must exist in EventoSismico entity
            eventoSismicoSeleccionado.rechazarEventoSismico(getFechaHoraActual(), punteroRechazado, empleadoActual);
            eventoSismicoRepository.save(eventoSismicoSeleccionado);
        } else {
            System.err.println("Error: Evento Sismico Seleccionado, Rechazado state, or Empleado Actual is null.");
        }
    }

    private Empleado obtenerEmpleadoActual() {
        // In a real application, you'd get the currently logged-in employee,
        // likely from a security context or by querying the repository with a known ID/username.
        // For this example, we'll try to find an employee by a dummy ID or create one.
        Optional<Empleado> employee = empleadoRepository.findById(1L);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            // Create a dummy employee if not found, and save it to the database
            // Ensure Empleado constructor matches (String apellido, String email, String nombre, Long telefono)
            Empleado defaultEmployee = new Empleado("Doe", "john.doe@example.com", "John", 123456789L);
            return empleadoRepository.save(defaultEmployee);
        }
    }

    // Helper method to find and set state pointers
    public void buscarEstadoNoRevisadosOAutodetectado() {
        List<Estado> allStates = Estado.getTodos();
        for (Estado estado : allStates) {
            if (estado.esAmbitoEventoSismico()) {
                if (estado.sosAutoDetectado()) {
                    this.punteroAutodetectado = estado;
                }
                if (estado.sosPendienteRevision()) {
                    this.punteroPendienteRevision = estado;
                }
            }
        }
    }

    // Helper method to find and set the 'Rechazado' state pointer
    public void obtenerPunteroRechazado() {
        List<Estado> allStates = Estado.getTodos();
        for (Estado estado : allStates) {
            if (estado.esAmbitoEventoSismico() && estado.esRechazado()) {
                punteroRechazado = estado;
                break; // Found it, no need to continue
            }
        }
    }

    public void tomarSeleccionEventoSismico(Long eventId) {
        this.eventoSismicoSeleccionado = eventoSismicoRepository.findById(eventId)
                                            .orElse(null);
    }

    public void tomarNoVisualizacion() {
        // TODO: implementar
    }

    public void tomarRechazoModificacion() {
        // TODO: implementar
    }

    public boolean validarDatosSismicos(String accion) {
        if (eventoSismicoSeleccionado == null) return false;
        // These getters must exist in EventoSismico entity
        if (eventoSismicoSeleccionado.getMagnitudRitcher() == null) return false;
        if (eventoSismicoSeleccionado.getAlcanceSismo() == null) return false;
        if (eventoSismicoSeleccionado.getOrigenDeGeneracion() == null) return false;
        if (accion == null) return false;
        accion = accion.trim().toLowerCase();
        return accion.equals("rechazar evento") || (accion.equals("aceptar evento"));
    }

    public static GestorRevisionManual crearGestorConEventosAleatorios(EventoSismicoRepository eventoSismicoRepository, EmpleadoRepository empleadoRepository) {
        GestorRevisionManual gestor = new GestorRevisionManual(eventoSismicoRepository, empleadoRepository);
        // It's crucial to ensure Estado.getTodos() can properly retrieve or create states
        // before calling this method if you intend to set states for new events.
        Estado autoDetectadoState = null;
        for (Estado estado : Estado.getTodos()) {
            if (estado.sosAutoDetectado()) {
                autoDetectadoState = estado;
                break;
            }
        }

        for (int i = 0; i < 10; i++) {
            EventoSismico newEvent = new EventoSismico();
            newEvent.setFechaHoraOcurrencia(LocalDateTime.now().minusHours(i)); // Changed from setFechaHora to setFechaHoraOcurrencia
            
            // Set an initial state for the new event
            if (autoDetectadoState != null) {
                newEvent.setEstadoActual(autoDetectadoState); // Assuming EventoSismico has setEstadoActual
            } else {
                System.err.println("Warning: AutoDetectado state not found. New events might not have an initial state.");
            }
            // You might need to set other default values for magnitude, alcance, etc.,
            // to avoid NullPointerExceptions later if they are expected by DTOs or other logic.
            // For example:
            // newEvent.setValorMagnitud( (float) (5.0 + Math.random() * 2.0));
            // newEvent.setLatitudEpicentro((float) (Math.random() * 180 - 90));
            // newEvent.setLongitudEpicentro((float) (Math.random() * 360 - 180));
            
            eventoSismicoRepository.save(newEvent);
        }
        return gestor;
    }

    // --- New helper methods for use cases (matching previous answers) ---
    public List<EventoSismico> obtenerEventosEnRevision() {
        List<Estado> estadosFiltrados = Estado.getTodos().stream()
                .filter(Estado::esAmbitoEventoSismico)
                .collect(Collectors.toList());

        List<Estado> estadosParaBuscar = estadosFiltrados.stream()
                .filter(estado -> estado.sosPendienteRevision() || estado.sosBloqueadoEnRevision())
                .collect(Collectors.toList());

        return eventoSismicoRepository.findByEstadoActualIn(estadosParaBuscar); // CHANGED HERE
    }

    public List<EventoSismico> obtenerEventosRechazados() {
        List<Estado> estadosFiltrados = Estado.getTodos().stream()
                .filter(Estado::esAmbitoEventoSismico)
                .collect(Collectors.toList());

        List<Estado> estadosParaBuscar = estadosFiltrados.stream()
                .filter(Estado::esRechazado)
                .collect(Collectors.toList());

        return eventoSismicoRepository.findByEstadoActualIn(estadosParaBuscar); // CHANGED HERE
    }
}