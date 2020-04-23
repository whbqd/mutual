import Vue from "vue";
import VueRouter from "vue-router";
import login from "../components/login";
import register from "../components/register";
import checking from "../components/checking";
import revise from "../components/revise";
import UserAll from "../components/UserAll";

Vue.use(VueRouter);
const routes = [
  {
    path: "/",
    component: login
  },
  {
    path: "/register",
    component: register
  },
  {
    path: "/revise",
    component: revise
  },
  {
    path: "/checking",
    component: checking
  },
  {
    path: '/UserAll',
    component: UserAll
  }
];

const router = new VueRouter({
  routes
});

export default router;
