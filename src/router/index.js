import Vue from "vue";
import VueRouter from "vue-router";
import login from "../components/login";
import register from "../components/register";

Vue.use(VueRouter);
const routes = [
  {
    path: "/",
    component: login
  },
  {
    path: "/register",
    component: register
  }
];

const router = new VueRouter({
  routes
});

export default router;
