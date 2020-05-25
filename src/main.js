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

//导入ViewUi
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
Vue.use(ViewUI);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
