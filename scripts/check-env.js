#!/usr/bin/env node

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

// Función para verificar la versión de Node.js
function checkNodeVersion() {
  try {
    const requiredVersion = fs.readFileSync('.nvmrc', 'utf8').trim();
    const currentVersion = process.version.replace('v', '');
    
    if (currentVersion === requiredVersion) {
      logSuccess(`Versión de Node.js: v${currentVersion} (requerida: v${requiredVersion})`);
      return true;
    } else {
      logWarning(`Versión de Node.js: v${currentVersion} (se recomienda v${requiredVersion})`);
      logInfo('  Ejecuta: nvm install');
      return false;
    }
  } catch (error) {
    logError('No se pudo verificar la versión de Node.js', error);
    return false;
  }
}

// Función para verificar la instalación de npm
function checkNpmInstallation() {
  try {
    const npmVersion = execSync('npm --version', { stdio: 'pipe' }).toString().trim();
    logSuccess(`Versión de npm: ${npmVersion}`);
    return true;
  } catch (error) {
    logError('npm no está instalado o no está en el PATH');
    return false;
  }
}

// Función para verificar la instalación de Vercel CLI
function checkVercelCli() {
  try {
    const vercelVersion = execSync('vercel --version', { stdio: 'pipe' }).toString().trim();
    logSuccess(`Versión de Vercel CLI: ${vercelVersion}`);
    return true;
  } catch (error) {
    logWarning('Vercel CLI no está instalado. Instálalo con: npm install -g vercel');
    return false;
  }
}

// Función para verificar la estructura del proyecto
function checkProjectStructure() {
  const requiredDirs = ['src', 'public'];
  const requiredFiles = ['package.json', 'vite.config.ts', 'tsconfig.json'];
  let allOk = true;

  logInfo('Verificando estructura del proyecto...');
  
  // Verificar directorios
  requiredDirs.forEach(dir => {
    const dirPath = path.join(process.cwd(), dir);
    if (fs.existsSync(dirPath) && fs.statSync(dirPath).isDirectory()) {
      logSuccess(`  ✓ Directorio encontrado: ${dir}/`);
    } else {
      logError(`  ✗ Directorio no encontrado: ${dir}/`);
      allOk = false;
    }
  });

  // Verificar archivos
  requiredFiles.forEach(file => {
    const filePath = path.join(process.cwd(), file);
    if (fs.existsSync(filePath) && fs.statSync(filePath).isFile()) {
      logSuccess(`  ✓ Archivo encontrado: ${file}`);
    } else {
      logError(`  ✗ Archivo no encontrado: ${file}`);
      allOk = false;
    }
  });

  return allOk;
}

// Función para verificar las dependencias
function checkDependencies() {
  try {
    logInfo('Verificando dependencias...');
    
    // Leer package.json
    const packageJson = JSON.parse(fs.readFileSync('package.json', 'utf8'));
    const { dependencies = {}, devDependencies = {} } = packageJson;
    
    // Verificar dependencias requeridas
    const requiredDeps = ['react', 'react-dom', 'react-router-dom'];
    const requiredDevDeps = ['vite', '@vitejs/plugin-react', 'typescript'];
    
    let allDepsOk = true;
    
    // Verificar dependencias de producción
    requiredDeps.forEach(dep => {
      if (dependencies[dep]) {
        logSuccess(`  ✓ Dependencia instalada: ${dep}@${dependencies[dep]}`);
      } else {
        logError(`  ✗ Dependencia faltante: ${dep}`);
        allDepsOk = false;
      }
    });
    
    // Verificar dependencias de desarrollo
    requiredDevDeps.forEach(dep => {
      if (devDependencies[dep]) {
        logSuccess(`  ✓ Dependencia de desarrollo instalada: ${dep}@${devDependencies[dep]}`);
      } else {
        logError(`  ✗ Dependencia de desarrollo faltante: ${dep}`);
        allDepsOk = false;
      }
    });
    
    return allDepsOk;
  } catch (error) {
    logError('Error al verificar las dependencias', error);
    return false;
  }
}

// Función principal
function main() {
  console.log(`\n${colors.cyan}${colors.bright}🔍 Verificando entorno de desarrollo...${colors.reset}\n`);
  
  const nodeOk = checkNodeVersion();
  const npmOk = checkNpmInstallation();
  const vercelOk = checkVercelCli();
  const structureOk = checkProjectStructure();
  const depsOk = checkDependencies();
  
  console.log('\n' + '='.repeat(60));
  
  if (nodeOk && npmOk && vercelOk && structureOk && depsOk) {
    logSuccess('✅ ¡Todo está listo para el desarrollo!');
    console.log('\nPuedes iniciar el servidor de desarrollo con: npm run dev\n');
    process.exit(0);
  } else {
    logError('❌ Se encontraron problemas en la configuración del entorno.');
    console.log('\nPor favor, corrige los problemas mostrados arriba antes de continuar.\n');
    process.exit(1);
  }
}

// Ejecutar verificación
if (require.main === module) {
  main();
}

module.exports = {
  checkNodeVersion,
  checkNpmInstallation,
  checkVercelCli,
  checkProjectStructure,
  checkDependencies,
};
