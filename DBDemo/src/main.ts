import "./assets/main.css";

import { createApp } from "vue";
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import App from "./App.vue";

import Router from "./router";

import axios from "axios";
import VueAxios from "vue-axios";

const app = createApp(App);

app.use(ElementPlus);
app.use(Router);
app.use(VueAxios, axios);
app.mount("#app");
