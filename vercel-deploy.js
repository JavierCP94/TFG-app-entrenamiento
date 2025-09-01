const { execSync } = require('child_process');
const fs = require('fs');
const path = require('path');

// Colores para la consola
const colors = {
  reset: '\x1b[0m',
  bright: '\x1b[1m',
  dim: '\x1b[2m',
  red: '\x1b[31m',
  green: '\x1b[32m',
  yellow: '\x1b[33m',
  blue: '\x1b[34m',
  magenta: '\x1b[35m',
  cyan: '\x1b[36m',
};

// Función para formatear mensajes de log
function logInfo(message) {
  console.log(`${colors.blue}${colors.bright}ℹ️  ${message}${colors.reset}`);
}

function logSuccess(message) {
  console.log(`${colors.green}${colors.bright}✅ ${message}${colors.reset}`);
}

function logWarning(message) {
  console.log(`${colors.yellow}${colors.bright}⚠️  ${message}${colors.reset}`);
}

function logError(message, error = null) {
  console.error(`${colors.red}${colors.bright}❌ ${message}${colors.reset}`);
  if (error) {
    console.error(`${colors.red}${error.message || error}${colors.reset}`);
  }
}

// Función para ejecutar comandos con manejo de errores
function runCommand(command, cwd = process.cwd()) {
  logInfo(`Ejecutando: ${command}`);
  try {
    const output = execSync(command, { 
      stdio: 'inherit', 
      cwd,
      env: { ...process.env, FORCE_COLOR: '1' }
    });
    return { success: true, output };
  } catch (error) {
    logError(`Error al ejecutar el comando: ${command}`, error);
    return { success: false, error };
  }
}

// Función principal de despliegue
async function deployToVercel() {
  console.log(`\n${colors.blue}${colors.bright}🚀 Iniciando despliegue en Vercel...${colors.reset}\n`);

  // 1. Verificar que estamos en el directorio correcto
  const rootDir = process.cwd();
  logInfo(`Directorio actual: ${rootDir}`);

  // 2. Verificar que el directorio es un repositorio git
  try {
    const gitCheck = execSync('git rev-parse --is-inside-work-tree', { stdio: 'pipe' });
    logSuccess('Directorio es un repositorio Git');
  } catch (error) {
    logWarning('El directorio no es un repositorio Git. Inicializando...');
    const gitInit = runCommand('git init');
    if (!gitInit.success) {
      logError('No se pudo inicializar el repositorio Git. Continuando de todos modos...');
    }
  }

  // 3. Instalar dependencias
  logInfo('Instalando dependencias...');
  const installResult = runCommand('npm install --legacy-peer-deps');
  if (!installResult.success) {
    logError('Error al instalar dependencias. Abortando despliegue.');
    process.exit(1);
  }
  logSuccess('Dependencias instaladas correctamente');

  // 4. Ejecutar el build
  logInfo('Construyendo la aplicación...');
  const buildResult = runCommand('npm run build');
  if (!buildResult.success) {
    logError('Error al construir la aplicación. Abortando despliegue.');
    process.exit(1);
  }
  logSuccess('Aplicación construida correctamente');

  // 5. Verificar que la carpeta dist existe
  const distPath = path.join(rootDir, 'dist');
  if (!fs.existsSync(distPath)) {
    logError('No se encontró la carpeta "dist". Asegúrate de que el build se completó correctamente.');
    process.exit(1);
  }

  // 6. Verificar que hay archivos en la carpeta dist
  const distFiles = fs.readdirSync(distPath);
  if (distFiles.length === 0) {
    logError('La carpeta "dist" está vacía. Asegúrate de que el build generó los archivos correctamente.');
    process.exit(1);
  }
  logSuccess(`Se encontraron ${distFiles.length} archivos en la carpeta dist`);

  // 7. Verificar si Vercel CLI está instalado
  try {
    execSync('vercel --version', { stdio: 'ignore' });
    logSuccess('Vercel CLI está instalado');
  } catch {
    logWarning('Vercel CLI no está instalado. Instalando...');
    const installVercel = runCommand('npm install -g vercel');
    if (!installVercel.success) {
      logError('No se pudo instalar Vercel CLI. Por favor, instálalo manualmente con: npm install -g vercel');
      process.exit(1);
    }
  }

  // 8. Iniciar sesión en Vercel (si no se ha iniciado sesión)
  logInfo('Verificando sesión de Vercel...');
  const vercelWhoAmI = runCommand('npx vercel whoami', { stdio: 'pipe' });
  if (!vercelWhoAmI.success) {
    logWarning('No hay una sesión activa de Vercel. Iniciando sesión...');
    const loginResult = runCommand('npx vercel login');
    if (!loginResult.success) {
      logError('Error al iniciar sesión en Vercel. Abortando despliegue.');
      process.exit(1);
    }
  } else {
    logSuccess('Sesión de Vercel activa');
  }

  // 9. Desplegar en Vercel
  logInfo('Iniciando despliegue en Vercel...');
  try {
    // Primero una previsualización para verificar la configuración
    logInfo('Verificando configuración con una previsualización...');
    const previewResult = runCommand('npx vercel --yes');
    
    if (!previewResult.success) {
      logError('Error en la previsualización del despliegue. Revisa los errores anteriores.');
      process.exit(1);
    }

    // Si la previsualización es exitosa, proceder con el despliegue en producción
    logInfo('Previsualización exitosa. Iniciando despliegue en producción...');
    const deployResult = runCommand('npx vercel --prod --confirm');
    
    if (deployResult.success) {
      logSuccess('✅ ¡Despliegue completado con éxito!');
      console.log(`\n${colors.green}${colors.bright}🎉 ¡La aplicación ha sido desplegada correctamente!${colors.reset}`);
      console.log(`${colors.green}Puedes acceder a tu aplicación en la URL proporcionada arriba.${colors.reset}\n`);
    } else {
      logError('Error al desplegar en producción. Revisa los errores anteriores.');
      process.exit(1);
    }
  } catch (error) {
    logError('Error inesperado durante el despliegue:', error);
    process.exit(1);
  }
}

// Ejecutar el despliegue
if (require.main === module) {
  deployToVercel().catch(error => {
    logError('Error inesperado:', error);
    process.exit(1);
  });
}

module.exports = { deployToVercel };
