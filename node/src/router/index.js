import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    meta:{title:"成都科技金融平台"}
  },
  {
    path: '/guquan',
    name: 'Guquan',
    component: () => import(/* webpackChunkName: "about" */ '../views/Guquan.vue'),
    meta:{title:'股权融资'}
  },
  {
    path: '/zhaiquan',
    name: 'Zhaiquan',
    component: () => import(/* webpackChunkName: "about" */ '../views/Zhaiquan.vue'),
    meta:{title:'债券融资'}
  },
  {
    path: '/detail',
    name: 'Detail',
    component: () => import(/* webpackChunkName: "about" */ '../views/Detail.vue'),
    meta:{title:'详情'}
  },
  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // }
]

const router = new VueRouter({
  routes
})

// 处理重复路由异常
let Router = VueRouter.prototype.push
VueRouter.prototype.push = function push(to){
  return Router.call(this,to).catch((err)=>{})
}

// 添加title
router.beforeEach((to,from,next)=>{
  if(to.meta && to.meta.title){
    if(to.path!='/'){
      document.title = to.meta.title+' | 成都科技金融平台'
    }else{
      document.title = to.meta.title ;
    }
  }
  next();
})

export default router
