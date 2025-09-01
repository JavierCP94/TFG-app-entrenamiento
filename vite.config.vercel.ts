import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import path from 'path';

export default defineConfig({
  root: __dirname,
  plugins: [react()],
  base: '/',
  publicDir: 'public',
  server: {
    port: 3000,
    host: true,
    strictPort: true,
    headers: {
      'Content-Type': 'application/javascript',
    },
  },
  preview: {
    port: 3000,
    host: true,
    strictPort: true,
    headers: {
      'Content-Type': 'application/javascript',
    },
  },
  resolve: {
    alias: [
      {
        find: '@',
        replacement: path.resolve(__dirname, 'src'),
      },
    ],
  },
  build: {
    outDir: 'dist',
    assetsDir: 'assets',
    sourcemap: false,
    emptyOutDir: true,
    rollupOptions: {
      input: {
        main: path.resolve(__dirname, 'index.html'),
      },
      output: {
        entryFileNames: 'assets/[name].[hash].js',
        chunkFileNames: 'assets/[name].[hash].js',
        assetFileNames: 'assets/[name].[hash][extname]',
        manualChunks: {
          react: ['react', 'react-dom', 'react-router-dom'],
          vendor: ['lucide-react'],
        },
      },
    },
    chunkSizeWarningLimit: 1000,
    manifest: true,
  },
});
