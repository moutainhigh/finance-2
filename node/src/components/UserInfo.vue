<template>
  <div class="user">
      <div class="home-userName" v-if="isHome">
          <span>{{userInfo.companyName}}</span>
          <img src="/image/home/user-i.png" /> 
      </div>
      <div class="userName" v-if="!isHome">
         <span>{{userInfo.companyName}}</span> 
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
<style lang="scss" scoped>
.user .home-userName{max-width:22.66vw;height:2.6vw;
    background:url(/image/home/user-center.png) center center repeat;
               /* url(/image/home/user-left.png) left center no-repeat,
               url(/image/home/user-right.png) right center no-repeat; */
    /* background-size: cover; */
    font-family: FZLTXHK;font-size: 1.04vw;font-weight: normal;font-stretch: normal;line-height: 3vw;letter-spacing: 0px;
    text-align: right;padding:0 1vw;
    position: relative;
color: #9ee9ff;}
.user .home-userName::before{
    content:'';
    width:4vw;
    height:2.6vw;
    position: absolute;
    top:0vw;
    left:-1.2vw;
    background:url(/image/home/user.png) left center no-repeat;
}
.user .home-userName::after{
    content:'';
    width:2vw;
    height:2.6vw;
    position: absolute;
    top:0vw;
    right:-1.1vw;
    background:url(/image/home/user.png) right center no-repeat;
}
.user{
    .home-userName{
        display:flex;
        justify-content:center;
        align-items: center;
        span{
            max-width:22.6vw;
            text-overflow: ellipsis;
            overflow: hidden;
            white-space: nowrap;
            display:inline-block;
        }
    }
}
.user .home-userName img{width:1.09vw;height:1.02vw;margin-right:0.99vw;margin-left:0.89vw;vertical-align:middle;margin-top:-0.73px;}
.user .userName{width:22.66vw;height:2.6vw;color:#9ee9ff;font-size:1vw;font-weight: normal;font-stretch: normal;line-height:2.6vw;letter-spacing: 0px;
text-align: right;}
.user .userName img{width:1.09vw;height:1.02vw;margin-right:0.99vw;margin-left:0.89vw;vertical-align:middle;margin-top:-0.3vw;}
</style>