/*
 * @Author: chenyx
 * @Date: 2022-12-28 19:54:03
 * @LastEditors: Do not edit
 * @LastEditTime: 2023-03-18 23:15:34
 * @FilePath: /totd-cesium/src/main.ts
 */
import { createApp } from "vue";
import { createPinia } from 'pinia'
import "./style.css";

import router from "./router";
import App from "./App.vue";
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import * as ElementPlusIconsVue from "@element-plus/icons-vue";


const app = createApp(App);
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component);
}
app.use(createPinia())
app.use(ElementPlus);
app.use(router);
app.mount("#app");
