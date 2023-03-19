/*
 * @Author: chenyx
 * @Date: 2022-12-28 19:54:03
 * @LastEditors: Do not edit
 * @LastEditTime: 2023-03-19 17:13:02
 * @FilePath: /totd-cesium/vite.config.ts
 */
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import cesium from 'vite-plugin-cesium';
import path from 'path'



// https://vitejs.dev/config/
export default defineConfig({
  server: {
    port: 5173,
    open: true,
    proxy: {
      '/api': {
        // 线上地址
        target: "http://121.41.8.136:8080",
        // 本地地址
        // target: "http://127.0.0.1:8080",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  },
  plugins: [vue(),cesium()],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, "src"),
    }
  },
  build: {
    assetsDir: "./static",
    chunkSizeWarningLimit: 500,
    minify: "terser",
    cssCodeSplit: true, // 如果设置为false，整个项目中的所有 CSS 将被提取到一个 CSS 文件中
    terserOptions: {
      compress: {
        // warnings: false,
        drop_console: true, //打包时删除console
        drop_debugger: true, //打包时删除 debugger
        pure_funcs: ["console.log"],
      },
      output: {
        // 去掉注释内容
        comments: true,
      },
    },
    rollupOptions: {
      output: {
        chunkFileNames: "asstes/js/[name]-[hash].js",
        entryFileNames: "asstes/js/[name]-[hash].js",
        assetFileNames: "asstes/[ext]/[name]-[hash].[ext]",
        manualChunks: (id) => {
          // 使用函数形式时，每个解析的模块 id 都会传递给函数。
          // 如果返回字符串，则模块及其所有依赖项将添加到具有给定名称的手动块中。
          // 例如，这将创建一个vendor包含所有依赖项的块node_modules：
          if (id.includes("node_modules")) {
            return id
              .toString()
              .split("node_modules/")[1]
              .split("/")[0]
              .toString();
          }
        },
      },
    },
  },
})
