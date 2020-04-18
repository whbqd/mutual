import Vue from 'vue'
import App from './App.vue'
Vue.config.productionTip = false
//导入路由
import VueRouter from 'vue-router'
//use
Vue.use(VueRouter)
//导入需要用到的组件
import discovery from './view/03.discovery.vue'
import playlists from './view/04.playlists'
import songs from './view/05.songs'
import mvs from './view/06.mvs'
import result from './view/07.result'
import Mvdetails from './view/08.Mvdetails'
import vocal from './view/09.vocal'
//创建路由
let router = new VueRouter({
  routes:[
    {
      path: '/discovery',
      component: discovery
    },
    {
      path: '/',
      component: discovery
    },
    {
      path: '/playlists',
      component: playlists
    },
    {
      path: '/songs',
      component: songs
    },
    {
      path: '/mvs',
      component: mvs
    },
    {
      path:'/result',
      component: result
    },
    {
      path:'/Mvdetails',
      component:Mvdetails
    },
    {
      path:'/vocal',
      component:vocal
    }

  ]
})
//导入elemntUI
import  ElmentUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElmentUI);

new Vue({
  render: h => h(App),
  //挂载到Vue实例上
  router
}).$mount('#app')

