import React, { useState } from "react";

function App() {
  // State to manage which "page" is currently active.
  // 'home' is the initial page.
  const [currentPage, setCurrentPage] = useState("home");

  // State to store fetched data or error messages
  const [apiResponse, setApiResponse] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  // Function to handle the button click and change the page.
  const handleButtonClick = async () => {
    // Added 'async' keyword
    // Change the current page to 'manualRevision' when the button is clicked.
    setCurrentPage("manualRevision");

    setIsLoading(true); // Set loading state to true
    setError(null); // Clear any previous errors
    setApiResponse(null); // Clear any previous API response

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
      setApiResponse(data); // Store the fetched data in state
      console.log("Fetched data:", data); // Log the data to the console for debugging
    } catch (error) {
      // Catch any errors during the fetch operation
      console.error("Error fetching seismic events:", error);
      setError(error.message); // Store the error message in state
    } finally {
      setIsLoading(false); // Set loading state to false, regardless of success or failure
    }
  };

  // Function to go back to the home page.
  const handleGoBack = () => {
    setCurrentPage("home");
    setApiResponse(null); // Clear API response when going back
    setError(null); // Clear error when going back
  };

  // Conditional rendering based on the currentPage state.
  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-100 p-4 font-sans">
      {currentPage === "home" && (
        <div className="flex flex-col items-center justify-center p-8 bg-white rounded-xl shadow-lg">
          <h1 className="text-3xl font-bold text-gray-800 mb-6">
            Herramienta analisis de sismos
          </h1>
          <button
            onClick={handleButtonClick}
            className="px-8 py-4 bg-blue-600 text-white font-semibold rounded-xl shadow-md hover:bg-blue-700 focus:outline-none focus:ring-4 focus:ring-blue-500 focus:ring-opacity-75 transition duration-300 ease-in-out transform hover:scale-105 active:scale-95"
          >
            Opcion registrar revision manual
          </button>
        </div>
      )}

      {currentPage === "manualRevision" && (
        <div className="flex flex-col items-center justify-center p-8 bg-white rounded-xl shadow-lg">
          <h1 className="text-3xl font-bold text-gray-800 mb-6">
            Registrar revision manul
          </h1>
          {isLoading && (
            <p className="text-lg text-blue-600 mb-4">
              Cargando eventos sismicos...
            </p>
          )}
          {error && (
            <p className="text-lg text-red-600 mb-4">
              Error: {error}. Please try again.
            </p>
          )}
          {apiResponse && (
            <div className="text-lg text-gray-700 mb-8 text-center max-w-md">
              <p className="font-semibold">Fetched Data:</p>
              {/* You can render the fetched data here. For demonstration, we'll stringify it. */}
              <pre className="bg-gray-50 p-4 rounded-md text-sm text-left overflow-auto">
                {JSON.stringify(apiResponse, null, 2)}
              </pre>
            </div>
          )}
          {!isLoading && !error && !apiResponse && (
            <p className="text-lg text-gray-600 mb-8 text-center max-w-md">
              Click "Opcion registrar revision manual" on the home page to fetch
              data.
            </p>
          )}
          <button
            onClick={handleGoBack}
            className="px-8 py-4 bg-purple-600 text-white font-semibold rounded-xl shadow-md hover:bg-purple-700 focus:outline-none focus:ring-4 focus:ring-purple-500 focus:ring-opacity-75 transition duration-300 ease-in-out transform hover:scale-105 active:scale-95"
          >
            Go Back to Home
          </button>
        </div>
      )}
    </div>
  );
}

export default App;
