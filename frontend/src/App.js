// src/App.js
import React, { useEffect, useState } from 'react';
import './App.css'; // Assuming you have some basic styling

function App() {
  const [helloMessage, setHelloMessage] = useState('');
  const [backendData, setBackendData] = useState(null);
  const [error, setError] = useState('');

  // Effect to fetch the simple string message
  useEffect(() => {
    fetch('/api/hello') // This will be proxied to http://localhost:8080/api/hello
      .then(response => {
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.text(); // Expecting plain text
      })
      .then(message => setHelloMessage(message))
      .catch(e => {
        console.error('Error fetching hello message:', e);
        setError(`Failed to fetch hello: ${e.message}`);
      });

    // Effect to fetch JSON data
    fetch('/api/data') // This will be proxied to http://localhost:8080/api/data
      .then(response => {
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json(); // Expecting JSON
      })
      .then(data => setBackendData(data))
      .catch(e => {
        console.error('Error fetching data:', e);
        setError(`Failed to fetch data: ${e.message}`);
      });
  }, []); // Empty dependency array means these effects run once on mount

  return (
    <div className="App">
      <header className="App-header">
        <h1>Spring Boot & React Integration (with Fetch)</h1>
        {error && <p style={{ color: 'red' }}>Error: {error}</p>}

        <h2>Messages from Backend:</h2>
        <p>Hello Message: {helloMessage || 'Loading...'}</p>
        <p>Data Message: {backendData ? backendData.message : 'Loading...'}</p>
        <p>Data Timestamp: {backendData ? backendData.timestamp : 'Loading...'}</p>

        <p>
          Both applications need to be running simultaneously.
        </p>
      </header>
    </div>
  );
}

export default App;