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
import Homepage from "../views/Homepage";
import commodity from "../views/commodity"
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
    // Ƕ��·��
    children: [
      {
        path: "/index",
        component: Homepage
      },
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
      },
      {
        path: "/index/Homepage",
        component: Homepage
      },
      {
        path: "/index/commodity",
        component: commodity
      }
    ]
  }
];

const router = new VueRouter({
  routes
});
// ����һ��·�ɵ�������
// Ϊ·�ɶ������ beforeEach ��������
router.beforeEach((to, from, next) => {
  // to ��Ҫ���ʵ�·��
  // from ���ĸ�·������
  // next ��һ������ ��ʾ����    next() ����   next('/login') ǿ����ת
  // ����ǳ�����ҳ���ҳ��ֱ�ӷ���
  if (
    to.path === "/login" ||
    to.path === "/checking" ||
    to.path === "/register" ||
    to.path === "/revise"
  )
    return next();
  // ��ȡtoken
  const token = window.localStorage.getItem("token");
  // û��token��ǿ����ת����¼ҳ��
  if (!token) return next("/login");
  // ��token�ͷ���
  next();
});
export default router;
