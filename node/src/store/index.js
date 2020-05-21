import Vue from 'vue'
import Vuex from 'vuex'


Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    userInfo:{userName:''}
  },
  mutations: {
    setUserInfo (state,userInfo) {
      state.userInfo = userInfo;
    }
  },
  actions:{
    setUserInfo(context,userinfo){
        context.commit('setUserInfo',userinfo);
    }
  }
});

export default store;