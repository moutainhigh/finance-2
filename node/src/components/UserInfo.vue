<template>
  <div class="user">
      <div class="home-userName" v-if="isHome">
          <span>{{userInfo.userName}}</span>
          <img src="/image/home/user-i.png" /> 
      </div>
      <div class="userName" v-if="!isHome">
         <span>{{userInfo.userName}}</span> 
         <img src="/image/home/user-circle.png" /> 
      </div>
  </div>
</template>

<script>
export default {
    name:'UserInfo',
    props:{
        userInfo:{type:Object,default:()=>{return {userName:'',token:'',sex:'',mobile:'',id:'',avatar:''}}},
        isHome:{type:Boolean,default:false}
    },
    data(){
        return {

        }
    },
    methods:{

    }
}
</script>
<style scoped>
.user .home-userName{width:464px;height:80px;background:url(/image/home/user.png) center no-repeat;
font-family: FZLTXHK;font-size: 20px;font-weight: normal;font-stretch: normal;line-height: 80px;letter-spacing: 0px;
text-align: right;padding:0 20px;
color: #9ee9ff;}
.user .home-userName img{width:21px;height:23px;margin-right:10px;margin-left:17px;vertical-align:middle;margin-top:-4px;}
.user .userName{width:464px;height:80px;color:#9ee9ff;font-size: 20px;font-weight: normal;font-stretch: normal;line-height: 80px;letter-spacing: 0px;
text-align: right;}
.user .userName img{width:21px;height:23px;margin-right:10px;margin-left:17px;vertical-align:middle;margin-top:-4px;}
</style>