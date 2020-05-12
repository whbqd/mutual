import Vue from "vue";
import VueRouter from "vue-router";
import login from "../components/login";
import register from "../components/register";
import checking from "../components/checking";
import revise from "../components/revise";
import UserAll from "../components/UserAll";
import index from "../components/index";
import Table from "../views/Table";
import From from "../views/From";
import List from "../views/List";
Vue.use(VueRouter);
const routes = [
  {
    path: "/",
    component: login
  },
  {
    path: "/login",
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
    path: "/UserAll",
    component: UserAll
  },
  {
    path: "/index",
    component: index,
    // Ç¶Ì×Â·ÓÉ
    children: [
      {
        path: "/index/Table",
        component: Table
      },
      {
        path: "/index/From",
        component: From
      },
      {
        path: "/index/List",
        component: List
      }
    ]
  }
];

const router = new VueRouter({
  routes
});

export default router;
