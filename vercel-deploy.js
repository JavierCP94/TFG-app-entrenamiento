const { execSync } = require('child_process');
const fs = require('fs');
const path = require('path');

console.log('🚀 Iniciando proceso de despliegue...');

// 1. Limpiar instalación previa
try {
  console.log('🧹 Limpiando instalación previa...');
  if (fs.existsSync('node_modules')) {
    fs.rmSync('node_modules', { recursive: true, force: true });
  }
  if (fs.existsSync('dist')) {
    fs.rmSync('dist', { recursive: true, force: true });
  }
  console.log('✅ Limpieza completada');
} catch (error) {
  console.error('❌ Error al limpiar:', error.message);
  process.exit(1);
}

// 2. Instalar dependencias
try {
  console.log('📦 Instalando dependencias...');
  execSync('npm install', { stdio: 'inherit' });
  console.log('✅ Dependencias instaladas');
} catch (error) {
  console.error('❌ Error al instalar dependencias:', error.message);
  process.exit(1);
}

// 3. Construir la aplicación
try {
  console.log('🔨 Construyendo la aplicación...');
  execSync('npm run build', { stdio: 'inherit' });
  
  // Verificar que la carpeta dist se creó correctamente
  if (!fs.existsSync('dist') || !fs.existsSync('dist/index.html')) {
    throw new Error('La carpeta dist no se creó correctamente');
  }
  
  console.log('✅ Aplicación construida correctamente');
} catch (error) {
  console.error('❌ Error al construir la aplicación:', error.message);
  process.exit(1);
}

// 4. Desplegar a Vercel
try {
  console.log('🚀 Desplegando en Vercel...');
  execSync('npx vercel --prod --force', { stdio: 'inherit' });
  console.log('✅ ¡Despliegue completado con éxito!');
} catch (error) {
  console.error('❌ Error al desplegar en Vercel:', error.message);
  process.exit(1);
}
