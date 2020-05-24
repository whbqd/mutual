import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
//导入全局css
import "./assets/css/global.scss";
//导入ElmentUI
import ElmentUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
Vue.config.productionTip = false;
Vue.use(ElmentUI);
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
