import React, { useState } from "react";

function App() {
  // State to manage which "page" is currently active.
  // 'home' is the initial page.
  const [currentPage, setCurrentPage] = useState("home");

  // State to store fetched data (list of seismic events)
  const [seismicEvents, setSeismicEvents] = useState([]);
  // State to store the currently selected seismic event object
  const [selectedEvent, setSelectedEvent] = useState(null);
  // State to manage loading status for fetching events
  const [isLoadingEvents, setIsLoadingEvents] = useState(false);
  // State to manage loading status for confirming selection
  const [isConfirming, setIsConfirming] = useState(false);
  // State to store error messages for fetching events
  const [fetchError, setFetchError] = useState(null);
  // State to store error messages for confirming selection
  const [confirmError, setConfirmError] = useState(null);
  // State to store success message after confirming selection
  const [confirmSuccess, setConfirmSuccess] = useState(null);

  // Function to handle the button click and change the page, also fetches data.
  const handleButtonClick = async () => {
    setCurrentPage("manualRevision"); // Change to the manual revision page

    setIsLoadingEvents(true); // Set loading state to true for events fetch
    setFetchError(null); // Clear any previous fetch errors
    setSeismicEvents([]); // Clear any previous seismic events
    setSelectedEvent(null); // Clear any previous selection
    setConfirmError(null); // Clear any previous confirmation errors
    setConfirmSuccess(null); // Clear any previous confirmation success messages

    try {
      // Make the fetch call to the Spring endpoint with the full URL
      const response = await fetch(
        "http://localhost:8080/api/revision-manual/eventos-no-revisados"
      );

      // Check if the response was successful (status code 200-299)
      if (!response.ok) {
        // If not successful, throw an error with the status
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      // Parse the JSON response
      const data = await response.json();

      // Assuming the API returns an array of objects
      if (Array.isArray(data)) {
        // --- MODIFICATION HERE: Removed _tempId as 'id' is now provided by backend ---
        setSeismicEvents(data); // Store the fetched array of events directly
        // ------------------------------------------------------------------
      } else {
        // If the API returns a single object or unexpected format, handle it
        console.warn("API response is not an array:", data);
        setSeismicEvents([]); // Ensure it's an empty array if not an array
        setFetchError("Formato de datos inesperado del servidor.");
      }

      console.log("Fetched data:", data); // Log the data to the console for debugging
    } catch (error) {
      // Catch any errors during the fetch operation
      console.error("Error fetching seismic events:", error);
      setFetchError(
        `Error al cargar eventos sísmicos: ${error.message}. Por favor, intente de nuevo.`
      );
    } finally {
      setIsLoadingEvents(false); // Set loading state to false, regardless of success or failure
    }
  };

  // Function to handle selecting a seismic event from the list
  const handleSelectEvent = (event) => {
    setSelectedEvent(event);
    setConfirmError(null); // Clear confirmation error on new selection
    setConfirmSuccess(null); // Clear confirmation success on new selection
  };

  // Function to send the selected event data to the backend
  const handleConfirmSelection = async () => {
    // Ensure an event is selected and it has an ID to send to the backend.
    if (
      !selectedEvent ||
      selectedEvent.id === undefined ||
      selectedEvent.id === null
    ) {
      setConfirmError(
        "Por favor, seleccione un evento sísmico válido (ID no disponible para selección)."
      );
      return;
    }

    setIsConfirming(true); // Set loading state for confirmation
    setConfirmError(null); // Clear previous confirmation errors
    setConfirmSuccess(null); // Clear previous success messages

    try {
      // --- MODIFIED HERE: Use the /seleccionar-evento/{eventId} endpoint ---
      const response = await fetch(
        `http://localhost:8080/api/revision-manual/seleccionar-evento/${selectedEvent.id}`, // Dynamic URL with eventId
        {
          method: "POST", // Method is POST as per your backend controller
          headers: {
            "Content-Type": "application/json", // Still good practice, but no body is sent
          },
          // No 'body' is sent here, as the ID is in the URL path.
        }
      );

      if (!response.ok) {
        const errorText = await response.text(); // Get error message from backend
        throw new Error(
          `HTTP error! status: ${response.status} - ${errorText}`
        );
      }

      const result = await response.json(); // Assuming backend returns a success message or updated object
      console.log("Selection successful:", result);
      setConfirmSuccess("Evento sísmico seleccionado exitosamente!"); // Updated message
      // Optionally, you might want to refresh the list or go back to home after success
      // handleGoBack(); // Uncomment to go back to home after successful selection
    } catch (error) {
      console.error("Error selecting seismic event:", error);
      setConfirmError(`Error al seleccionar el evento: ${error.message}.`); // Updated message
    } finally {
      setIsConfirming(false); // Set loading state to false
    }
  };

  // Function to go back to the home page.
  const handleGoBack = () => {
    setCurrentPage("home");
    setSeismicEvents([]); // Clear events when going back
    setSelectedEvent(null); // Clear selection when going back
    setIsLoadingEvents(false); // Reset loading states
    setIsConfirming(false);
    setFetchError(null); // Clear all errors
    setConfirmError(null);
    setConfirmSuccess(null); // Clear success message
  };

  // Conditional rendering based on the currentPage state.
  return (
    <div>
      {currentPage === "home" && (
        <div>
          <h1>Herramienta análisis de sismos</h1>
          <button onClick={handleButtonClick}>
            Opción registrar revisión manual
          </button>
        </div>
      )}

      {currentPage === "manualRevision" && (
        <div>
          <h1>Registrar revisión manual</h1>

          {isLoadingEvents && <p>Cargando eventos sísmicos...</p>}

          {fetchError && <p>Error: {fetchError}</p>}

          {!isLoadingEvents && !fetchError && seismicEvents.length === 0 && (
            <p>No hay eventos sísmicos no revisados para mostrar.</p>
          )}

          {!isLoadingEvents && !fetchError && seismicEvents.length > 0 && (
            <div>
              <h2>Seleccione un evento sísmico:</h2>
              <div>
                {seismicEvents.map(
                  (
                    event // Removed 'index' as key is 'event.id'
                  ) => (
                    <div
                      key={event.id} // <--- MODIFIED: Use event.id directly for key
                      onClick={() => handleSelectEvent(event)}
                      style={{
                        border: "1px solid gray",
                        padding: "10px",
                        margin: "5px",
                        cursor: "pointer",
                        backgroundColor:
                          selectedEvent && selectedEvent.id === event.id // <--- MODIFIED: Use event.id for highlighting
                            ? "lightblue"
                            : "white",
                      }}
                    >
                      <p>
                        Fecha y hora ocurrencia:{" "}
                        {event.fechaHoraOcurrencia // <--- MODIFIED: Use fechaHoraOcurrencia
                          ? new Date(event.fechaHoraOcurrencia).toLocaleString()
                          : "N/A"}
                      </p>
                      <p>
                        Epicentro: Lat{" "}
                        {event.latitudEpicentro !== undefined
                          ? event.latitudEpicentro.toFixed(4)
                          : "N/A"}
                        , Lon{" "}
                        {event.longitudEpicentro !== undefined
                          ? event.longitudEpicentro.toFixed(4)
                          : "N/A"}
                      </p>
                      <p>
                        Hipocentro: Lat{" "}
                        {event.latitudHipocentro !== undefined
                          ? event.latitudHipocentro.toFixed(4)
                          : "N/A"}
                        , Lon{" "}
                        {event.longitudHipocentro !== undefined
                          ? event.longitudHipocentro.toFixed(4)
                          : "N/A"}
                      </p>
                    </div>
                  )
                )}
              </div>

              {selectedEvent && (
                <div>
                  <h2>Detalles del Evento Seleccionado</h2>
                  <pre
                    style={{
                      border: "1px solid lightgray",
                      padding: "10px",
                      backgroundColor: "#f0f0f0",
                      overflow: "auto",
                    }}
                  >
                    {JSON.stringify(selectedEvent, null, 2)}
                  </pre>

                  {/* Confirmation button */}
                  <button
                    onClick={handleConfirmSelection}
                    disabled={isConfirming}
                    style={{
                      padding: "10px 20px",
                      fontSize: "16px",
                      backgroundColor: isConfirming ? "gray" : "green",
                      color: "white",
                      border: "none",
                      cursor: isConfirming ? "not-allowed" : "pointer",
                      marginTop: "10px",
                    }}
                  >
                    {isConfirming ? "Confirmando..." : "Confirmar Selección"}
                  </button>

                  {confirmSuccess && (
                    <p style={{ color: "green", marginTop: "10px" }}>
                      {confirmSuccess}
                    </p>
                  )}
                  {confirmError && (
                    <p style={{ color: "red", marginTop: "10px" }}>
                      Error: {confirmError}
                    </p>
                  )}
                </div>
              )}
            </div>
          )}

          <button
            onClick={handleGoBack}
            style={{
              padding: "10px 20px",
              fontSize: "16px",
              backgroundColor: "purple",
              color: "white",
              border: "none",
              cursor: "pointer",
              marginTop: "20px",
            }}
          >
            Volver atrás
          </button>
        </div>
      )}
    </div>
  );
}

export default App;
