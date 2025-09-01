# Aplicación de Entrenamiento - Frontend

Aplicación web moderna desarrollada con React, TypeScript y Vite, diseñada para ofrecer una experiencia de usuario fluida y responsiva.

## 🚀 Características

- ⚡ **Rendimiento óptimo** con Vite y React 18
- 🎨 **Diseño moderno** con Tailwind CSS
- 🛣️ **Enrutamiento** con React Router v7
- 🔄 **Carga rápida** con división de código
- 🛠️ **Desarrollo eficiente** con HMR (Hot Module Replacement)
- 📱 **Diseño responsive** que se adapta a cualquier dispositivo

## 📋 Requisitos del sistema

- Node.js 18.17.1 o superior
- npm 9.0.0 o superior
- Vercel CLI (opcional, para despliegue)

## 🛠️ Configuración del entorno

1. **Clonar el repositorio**
   ```bash
   git clone [URL_DEL_REPOSITORIO]
   cd [NOMBRE_DEL_PROYECTO]
   ```

2. **Instalar dependencias**
   ```bash
   npm install
   ```

3. **Verificar el entorno**
   ```bash
   npm run check-env
   ```
   Este comando verificará que todo esté configurado correctamente.

## 🚀 Comandos disponibles

- `npm run dev` - Inicia el servidor de desarrollo en modo desarrollo
- `npm run build` - Construye la aplicación para producción
- `npm run preview` - Previsualiza la versión de producción localmente
- `npm run lint` - Ejecuta el linter para verificar el código
- `npm run check-env` - Verifica que el entorno esté configurado correctamente
- `npm run deploy` - Despliega la aplicación en Vercel

## 🏗️ Estructura del proyecto

```
src/
├── assets/           # Recursos estáticos (imágenes, fuentes, etc.)
├── components/       # Componentes reutilizables
├── pages/            # Páginas de la aplicación
├── styles/           # Estilos globales
├── App.tsx           # Componente raíz de la aplicación
└── main.tsx          # Punto de entrada de la aplicación
```

## 🔧 Configuración

### Variables de entorno

Crea un archivo `.env` en la raíz del proyecto con las siguientes variables:

```env
VITE_APP_TITLE="Mi Aplicación de Entrenamiento"
VITE_API_URL=https://api.ejemplo.com
```

### Configuración de Vite

El proyecto utiliza Vite con las siguientes configuraciones:

- **Desarrollo rápido** con HMR (Hot Module Replacement)
- **Optimizaciones** para producción
- **División de código** automática
- **Soporte para TypeScript**
- **Alias de importación** (@/ para src/)

## 🚀 Despliegue

### Desplegar en Vercel

1. Asegúrate de tener instalado Vercel CLI:
   ```bash
   npm install -g vercel
   ```

2. Inicia sesión en Vercel:
   ```bash
   vercel login
   ```

3. Despliega la aplicación:
   ```bash
   npm run deploy
   ```

O simplemente conecta tu repositorio a Vercel para despliegues automáticos.

## 🛠️ Desarrollo

### Convenciones de código

- **Componentes**: Usar PascalCase (ej: `MiComponente.tsx`)
- **Hooks**: Usar prefijo `use` (ej: `useCustomHook.ts`)
- **Utilidades**: Usar camelCase (ej: `formatDate.ts`)
- **Tipos/Interfaces**: Usar sufijo `Type` (ej: `UserType.ts`)

### Estructura de commits

Usamos [Conventional Commits](https://www.conventionalcommits.org/):

- `feat:` Nueva característica
- `fix:` Corrección de errores
- `docs:` Cambios en la documentación
- `style:` Cambios de formato (punto y coma, sangría, etc.)
- `refactor:` Cambios que no corrigen errores ni agregan características
- `perf:` Cambios que mejoran el rendimiento
- `test:` Agregar o corregir pruebas
- `chore:` Cambios en el proceso de construcción o herramientas auxiliares

## 🤝 Contribución

1. Haz un fork del proyecto
2. Crea una rama para tu característica (`git checkout -b feature/AmazingFeature`)
3. Haz commit de tus cambios (`git commit -m 'feat: Add some AmazingFeature'`)
4. Haz push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más información.

## 🙏 Agradecimientos

- [Vite](https://vitejs.dev/) - Por el entorno de desarrollo increíblemente rápido
- [React](https://reactjs.org/) - Por hacer que la construcción de interfaces de usuario sea sencilla
- [TypeScript](https://www.typescriptlang.org/) - Por el tipado estático
- [Vercel](https://vercel.com/) - Por el hosting y despliegue sin configuración
   - MongoDB for VS Code

2. Open the project root directory in VSCode to work with both frontend and backend

3. Use the integrated terminal to run commands for each part of the application

## Communication Between Frontend and Backend

- The frontend is configured to communicate with the backend at `http://localhost:8080/api`
- CORS is configured on the backend to allow requests from the frontend origin

## Additional Documentation

- For more details on the frontend, see the [frontend README](/frontend/README.md)
- For more details on the backend, see the [backend README](/backend/README.md)
