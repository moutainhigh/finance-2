import Vue from 'vue'
import App from './App.vue'
import router from './router'
import * as d3 from 'd3'
import axios from 'axios'
import { Select,Pagination } from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import "@/common/css/font.css";

Vue.config.productionTip = false

axios.defaults.baseURL = process.env.NODE_ENV=="development"?location.origin+'/api':location.origin  //本地  ;
axios.defaults.headers = {
  'Content-Type': 'application/json;charset=utf-8',
}

Vue.use(Select)
Vue.use(Pagination)
Vue.prototype.$d3 = d3 ;
Vue.prototype.$http = axios;

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
