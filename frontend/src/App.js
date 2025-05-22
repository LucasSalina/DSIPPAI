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
        "http://localhost:8080/api/buscarEventosSismicosNoRevisados"
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
        setSeismicEvents(data); // Store the fetched array of events
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
    if (!selectedEvent) {
      setConfirmError(
        "Por favor, seleccione un evento sísmico para confirmar."
      );
      return;
    }

    setIsConfirming(true); // Set loading state for confirmation
    setConfirmError(null); // Clear previous confirmation errors
    setConfirmSuccess(null); // Clear previous success messages

    try {
      const response = await fetch(
        "http://localhost:8080/api/confirmarEventoSismico",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(selectedEvent), // Send the selected event object as JSON
        }
      );

      if (!response.ok) {
        const errorText = await response.text(); // Get error message from backend
        throw new Error(
          `HTTP error! status: ${response.status} - ${errorText}`
        );
      }

      const result = await response.json(); // Assuming backend returns a success message or updated object
      console.log("Confirmation successful:", result);
      setConfirmSuccess("Evento sísmico confirmado exitosamente!");
      // Optionally, you might want to refresh the list or go back to home after success
      // handleGoBack(); // Uncomment to go back to home after successful confirmation
    } catch (error) {
      console.error("Error confirming seismic event:", error);
      setConfirmError(`Error al confirmar el evento: ${error.message}.`);
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
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-100 p-4 font-sans">
      {currentPage === "home" && (
        <div className="flex flex-col items-center justify-center p-8 bg-white rounded-xl shadow-lg">
          <h1 className="text-3xl font-bold text-gray-800 mb-6">
            Herramienta análisis de sismos
          </h1>
          <button
            onClick={handleButtonClick}
            className="px-8 py-4 bg-blue-600 text-white font-semibold rounded-xl shadow-md hover:bg-blue-700 focus:outline-none focus:ring-4 focus:ring-blue-500 focus:ring-opacity-75 transition duration-300 ease-in-out transform hover:scale-105 active:scale-95"
          >
            Opción registrar revisión manual
          </button>
        </div>
      )}

      {currentPage === "manualRevision" && (
        <div className="flex flex-col items-center p-8 bg-white rounded-xl shadow-lg w-full max-w-2xl mx-auto">
          <h1 className="text-3xl font-bold text-gray-800 mb-6">
            Registrar revisión manual
          </h1>

          {isLoadingEvents && (
            <p className="text-lg text-blue-600 mb-4">
              Cargando eventos sísmicos...
            </p>
          )}

          {fetchError && (
            <p className="text-lg text-red-600 mb-4">Error: {fetchError}</p>
          )}

          {!isLoadingEvents && !fetchError && seismicEvents.length === 0 && (
            <p className="text-lg text-gray-600 mb-8 text-center max-w-md">
              No hay eventos sísmicos no revisados para mostrar.
            </p>
          )}

          {!isLoadingEvents && !fetchError && seismicEvents.length > 0 && (
            <div className="w-full mb-8">
              <h2 className="text-2xl font-semibold text-gray-700 mb-4 text-center">
                Seleccione un evento sísmico:
              </h2>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4 max-h-80 overflow-y-auto p-2 border border-gray-200 rounded-lg">
                {seismicEvents.map((event, index) => (
                  <div
                    key={event.id || index} // Use event.id if available, otherwise index
                    className={`p-4 border rounded-lg cursor-pointer transition-all duration-200
                                ${
                                  selectedEvent && selectedEvent.id === event.id
                                    ? "bg-blue-100 border-blue-500 shadow-md"
                                    : "bg-gray-50 border-gray-200 hover:bg-gray-100"
                                }`}
                    onClick={() => handleSelectEvent(event)}
                  >
                    <p className="font-semibold text-gray-800">
                      Ubicación: {event.ubicacion || "N/A"}
                    </p>
                    <p className="text-sm text-gray-600">
                      Fecha/Hora:{" "}
                      {event.fechaHoraOcurrencia
                        ? new Date(event.fechaHoraOcurrencia).toLocaleString()
                        : "N/A"}
                    </p>
                    <p className="text-sm text-gray-600">
                      Magnitud: {event.magnitudRitcher || "N/A"}
                    </p>
                  </div>
                ))}
              </div>

              {selectedEvent && (
                <div className="mt-8 p-6 bg-blue-50 rounded-xl border border-blue-200 shadow-inner">
                  <h2 className="text-2xl font-semibold text-blue-800 mb-4 text-center">
                    Detalles del Evento Seleccionado
                  </h2>
                  <pre className="bg-blue-100 p-4 rounded-md text-sm text-left overflow-auto text-blue-900">
                    {JSON.stringify(selectedEvent, null, 2)}
                  </pre>

                  {/* Confirmation button */}
                  <button
                    onClick={handleConfirmSelection}
                    disabled={isConfirming} // Disable button while confirming
                    className={`mt-4 px-8 py-4 w-full text-white font-semibold rounded-xl shadow-md
                               ${
                                 isConfirming
                                   ? "bg-gray-400 cursor-not-allowed"
                                   : "bg-green-600 hover:bg-green-700 focus:outline-none focus:ring-4 focus:ring-green-500 focus:ring-opacity-75 transition duration-300 ease-in-out transform hover:scale-105 active:scale-95"
                               }`}
                  >
                    {isConfirming ? "Confirmando..." : "Confirmar Selección"}
                  </button>

                  {confirmSuccess && (
                    <p className="text-lg text-green-600 mt-4 text-center">
                      {confirmSuccess}
                    </p>
                  )}
                  {confirmError && (
                    <p className="text-lg text-red-600 mt-4 text-center">
                      Error: {confirmError}
                    </p>
                  )}
                </div>
              )}
            </div>
          )}

          <button
            onClick={handleGoBack}
            className="mt-8 px-8 py-4 bg-purple-600 text-white font-semibold rounded-xl shadow-md hover:bg-purple-700 focus:outline-none focus:ring-4 focus:ring-purple-500 focus:ring-opacity-75 transition duration-300 ease-in-out transform hover:scale-105 active:scale-95"
          >
            Volver atrás
          </button>
        </div>
      )}
    </div>
  );
}

export default App;
