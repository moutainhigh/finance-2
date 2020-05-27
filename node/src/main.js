import Vue from 'vue'
import store from './store'
import App from './App.vue'
import router from './router'
import * as d3 from 'd3'
import { http } from '@/common/lib/tools.js'
// import { Select,Pagination,message,Form,FormItem } from 'ant-design-vue';
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import "@/common/css/font.css";

Vue.config.productionTip = false
Vue.use(Antd);

// Vue.use(store);
// Vue.use(Select)
// Vue.use(Form)
// Vue.use(FormItem)
// Vue.use(Pagination)
Vue.prototype.$d3 = d3 ;
Vue.prototype.$http = http;
// Vue.prototype.$message = message;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
