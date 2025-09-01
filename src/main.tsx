import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import App from './App';
import './index.css';

// Asegurarse de que el elemento root exista
const rootElement = document.getElementById('root');

if (!rootElement) {
  throw new Error('No se encontró el elemento con id "root"');
}

// Limpiar cualquier contenido existente
rootElement.innerHTML = '';

// Crear la raíz de React
try {
  const root = createRoot(rootElement);
  
  root.render(
    <StrictMode>
      <App />
    </StrictMode>
  );
} catch (error) {
  console.error('Error al renderizar la aplicación:', error);
  rootElement.innerHTML = `
    <div style="padding: 20px; color: #721c24; background-color: #f8d7da; border: 1px solid #f5c6cb; border-radius: 4px;">
      <h2>Error al cargar la aplicación</h2>
      <p>Por favor, recarga la página o contacta al soporte si el problema persiste.</p>
      <p>Detalles: ${error instanceof Error ? error.message : String(error)}</p>
    </div>
  `;
}
