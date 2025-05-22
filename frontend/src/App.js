// src/App.js
import React, { useEffect, useState } from 'react';
import './App.css'; // Assuming you have some basic styling

function App() {
  const [helloMessage, setHelloMessage] = useState('');
  const [backendData, setBackendData] = useState(null);
  const [error, setError] = useState('');
  const [eventos, setEventos] = useState([]);
  const [loading, setLoading] = useState(false);
  const [mostrarMapa, setMostrarMapa] = useState(false);
  const [mostrarAcciones, setMostrarAcciones] = useState(false);

  // Effect to fetch the simple string message
  useEffect(() => {
    fetch('/api/EventosSismicosAutoDetectados')
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

  const iniciarPrograma = async () => {
    setLoading(true);
    // Llama al endpoint para iniciar el programa y obtener los eventos
    await fetch('/api/iniciar-programa', { method: 'POST' });
    // Luego obtiene los eventos sísmicos autodetectados (debería haber un endpoint para esto)
    const res = await fetch('/api/eventos-sismicos');
    const data = await res.json();
    setEventos(data);
    setLoading(false);
    setMostrarMapa(true); // Mostrar el cuadro de mapa después de cargar eventos
  };

  const cancelarVisualizacion = async () => {
    await fetch('/api/tomar-no-visualizacion', { method: 'POST' });
    setMostrarMapa(false);
    setMostrarAcciones(true); // Mostrar opciones de acción luego de cancelar visualización
  };

  const aceptarVisualizacion = () => {
    // Aquí podrías implementar la lógica para mostrar el mapa real
    alert('Funcionalidad de visualización de mapa no implementada.');
    setMostrarMapa(false);
  };

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

        <button onClick={iniciarPrograma} disabled={loading}>
          {loading ? 'Cargando...' : 'Iniciar programa'}
        </button>
        <table style={{ marginTop: 24, width: '100%', borderCollapse: 'collapse' }}>
          <thead>
            <tr>
              <th style={{ border: '1px solid #ccc' }}>Numero</th>
              <th style={{ border: '1px solid #ccc' }}>FechaHoraOcurrencia</th>
              <th style={{ border: '1px solid #ccc' }}>Ubicacion</th>
              <th style={{ border: '1px solid #ccc' }}>Magnitud</th>
            </tr>
          </thead>
          <tbody>
            {eventos.map((evento, idx) => (
              <tr key={evento.id || idx}>
                <td style={{ border: '1px solid #ccc', textAlign: 'center' }}>{idx + 1}</td>
                <td style={{ border: '1px solid #ccc', textAlign: 'center' }}>{evento.fechaHoraOcurrencia}</td>
                <td style={{ border: '1px solid #ccc', textAlign: 'center' }}>{evento.ubicacion}</td>
                <td style={{ border: '1px solid #ccc', textAlign: 'center' }}>{evento.magnitud}</td>
              </tr>
            ))}
          </tbody>
        </table>
        {mostrarMapa && (
          <div className="modal-mapa">
            <div className="modal-mapa-content">
              <p style={{ fontWeight: 'bold', fontSize: '1.2rem', marginBottom: 16 }}>Visualizar Mapa del Evento Sísmico</p>
              <div style={{ display: 'flex', gap: 16, justifyContent: 'center' }}>
                <button className="btn-cancelar" onClick={cancelarVisualizacion}>Cancelar</button>
                <button className="btn-aceptar" onClick={aceptarVisualizacion}>Aceptar</button>
              </div>
            </div>
          </div>
        )}
        {mostrarAcciones && (
          <div className="modal-mapa">
            <div className="modal-mapa-content">
              <p style={{ fontWeight: 'bold', fontSize: '1.2rem', marginBottom: 16 }}>Seleccione una acción</p>
              <div style={{ display: 'flex', gap: 16, justifyContent: 'center' }}>
                <button className="btn-aceptar" onClick={() => alert('Confirmar evento')}>Confirmar evento</button>
                <button className="btn-cancelar" onClick={() => alert('Rechazar evento')}>Rechazar evento</button>
                <button className="btn-experto" onClick={() => alert('Solicitar revisión a experto')}>Solicitar revisión experto</button>
              </div>
            </div>
          </div>
        )}
      </header>
    </div>
  );
}

export default App;