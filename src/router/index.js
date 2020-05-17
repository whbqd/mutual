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
    // 嵌套路由
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
      }
    ]
  }
];

const router = new VueRouter({
  routes
});
// 挂载一个路由导航守卫
// 为路由对象，添加 beforeEach 导航守卫
router.beforeEach((to, from, next) => {
  // to 将要访问的路径
  // from 从哪个路径跳入
  // next 是一个函数 表示放行    next() 放行   next('/login') 强制跳转
  // 如果是除了主页外的页面直接放行
  if (
    to.path === "/login" ||
    to.path === "/checking" ||
    to.path === "/register" ||
    to.path === "/revise"
  )
    return next();
  // 获取token
  const token = window.sessionStorage.getItem("token");
  // 没有token则强制跳转到登录页面
  if (!token) return next("/login");
  // 有token就放行
  next();
});
export default router;
