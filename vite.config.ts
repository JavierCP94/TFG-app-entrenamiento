import react from "@vitejs/plugin-react";
import path from "path";
import { defineConfig } from "vite";

// Configuración específica para Vercel
const isVercel = process.env.VERCEL === "1";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  base: isVercel ? "/" : "/",
  publicDir: "public",
  server: {
    port: 3000,
    host: true,
    strictPort: true,
    headers: {
      "Content-Type": "application/javascript",
    },
  },
  preview: {
    port: 3000,
    host: true,
    strictPort: true,
  },
  resolve: {
    alias: [
      {
        find: "@",
        replacement: path.resolve(__dirname, "src"),
      },
    ],
  },
  optimizeDeps: {
    exclude: ["lucide-react"],
  },
  build: {
    target: "esnext",
    minify: "terser",
    sourcemap: !isVercel,
    outDir: "dist",
    assetsDir: "assets",
    emptyOutDir: true,
    reportCompressedSize: !isVercel,

    // rollupOptions eliminado para evitar dependencias nativas
    chunkSizeWarningLimit: 1000,
    manifest: true,
  },
});
