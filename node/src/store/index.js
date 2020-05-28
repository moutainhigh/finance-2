import Vue from 'vue'
import Vuex from 'vuex'
import { getStorage } from "@/common/lib/tools.js";


Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    userInfo:localStorage.getItem('userInfo')?JSON.parse(localStorage.getItem('userInfo')):{userName:''},
    token:getStorage('cdjr_token')?getStorage('cdjr_token'):''
  },
  mutations: {
    setUserInfo (state,userInfo) {
      state.userInfo = userInfo;
    },
    setToken (state,userInfo) {
      state.token = userInfo;
    },
  },
  actions:{
    setUserInfo(context,userinfo){
        context.commit('setUserInfo',userinfo);
    },
    setToken(context,token){
        context.commit('setToken',token);
    },
    clearUserInfo(context){
      context.commit('setUserInfo',{userName:''});
      context.commit('setToken','');
      localStorage.removeItem('userInfo');
      localStorage.removeItem('cdjr_token');
    }
  }
});

export default store;