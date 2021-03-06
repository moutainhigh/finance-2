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
    component: () => import('../views/Guquan.vue'),
    meta:{title:'股权融资'}
  },
  {
    path: '/zhaiquan',
    name: 'Zhaiquan',
    component: () => import('../views/Zhaiquan.vue'),
    meta:{title:'债权融资'}
  },
  {
    path: '/detail',
    name: 'Detail',
    component: () => import('../views/Detail.vue'),
    meta:{title:'股权详情'}
  },
  {
    path: '/zdetail',
    name: 'ZDetail',
    component: () => import('../views/ZDetail.vue'),
    meta:{title:'债权详情'}
  },
  {
    path: '/match',
    name: 'Match',
    component: () => import('../views/Match.vue'),
    meta:{title:'一键匹配'}
  },
  {
    path: '/result',
    name: 'Result',
    component: () => import('../views/Result.vue'),
    meta:{title:'返回到首页'}
  },
  {
    path: '/usercenter',
    name: 'UserCenter',
    component: () => import('../views/UserCenter.vue'),
    meta:{
      title:'个人中心',
      auth:true
    }
  },
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
