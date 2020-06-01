<template>
  <div class="user">
      <div class="home-userName" v-if="isHome">
          <img src="/image/home/user-left.png" class="user-left" alt="">
          <div class="u-info">
            <span>{{userInfo.companyName}}</span>
            <span @click="islogout=true" class="logout">[退出]</span>
            <img src="/image/home/user-i.png" class="u-icon" /> 
          </div>
          <img src="/image/home/user-right.png" class="user-right" alt="">
      </div>
      <div class="userName" v-if="!isHome">
         <span>{{userInfo.companyName}}</span>
         <span @click="islogout=true" class="logout">[退出]</span> 
         <img src="/image/home/user-circle.png" /> 
      </div>
      <a-modal v-model="islogout" title="温馨提示" centered  width="24vw">
        <a-spin :spinning="spinning"> 
        <p style="text-align:center;">{{msg}}</p>
        </a-spin> 
        <div slot="footer" style="text-align:center;">
            <a-button @click="islogout=false">取消</a-button>
            <a-button type="primary" @click="dologout">确定</a-button>
        </div>
      </a-modal>
  </div>
</template>

<script>
import {mapActions} from 'vuex'
export default {
    name:'UserInfo',
    props:{
        // userInfo:{type:Object,default:()=>{return {userName:'',token:'',sex:'',mobile:'',id:'',avatar:''}}},
        isHome:{type:Boolean,default:false}
    },
    data(){
        return {
            // userInfo:{userName:'',token:'',sex:'',mobile:'',id:'',avatar:''},
            islogout:false,
            msg:'确定要现在退出系统吗？',
            spinning:false
        }
    },
    computed:{
        userInfo(){
            return this.$store.state.userInfo
        }
    },
    methods:{
        ...mapActions(['clearUserInfo']),
        dologout(){
            this.spinning = true;
            this.$http.postWithAuth("/finance/userInfo/logout").then(res=>{
                this.spinning = false;
                if(!res || res.data.code==0){
                    this.clearUserInfo();
                    this.msg = '已退出，正在跳转到首页';
                    setTimeout(()=>{
                        this.islogout=false;
                        this.$router.push({path:'/'})
                    },1200)
                }else{
                    this.$message.error(res.data.msg)
                }
            })
        }
    }
}
</script>
<style lang="scss" scoped>
.user .home-userName{max-width:22.66vw;height:2.6vw;
    // background:url(/image/home/user-center.png) center center repeat;
               /* url(/image/home/user-left.png) left center no-repeat,
               url(/image/home/user-right.png) right center no-repeat; */
    /* background-size: cover; */
    font-family: FZLTXHK;font-size: 1.04vw;font-weight: normal;font-stretch: normal;line-height: 3vw;letter-spacing: 0px;
    text-align: right;padding:0 1vw;
    position: relative;
color: #9ee9ff;}
// .user .home-userName::before{
//     content:'';
//     width:4vw;
//     height:2.6vw;
//     position: absolute;
//     top:0vw;
//     left:-1.2vw;
//     background:url(/image/home/user.png) left center no-repeat;
// }
// .user .home-userName::after{
//     content:'';
//     width:2vw;
//     height:2.6vw;
//     position: absolute;
//     top:0vw;
//     right:-1.1vw;
//     background:url(/image/home/user.png) right center no-repeat;
// }
.user{
    .home-userName{
        display:flex;
        justify-content:center;
        align-items: center;
        .user-left{
            width:3vw;
            height:3.2vw;
        }
        .user-right{
            width: 2vw;
            height:3.2vw;
        }
        .u-info{
            display:flex;
            justify-content:center;
            align-items: center;
            // background:url(/image/home/user-center.png) center center repeat;
            // background-size:cover;
            height: 1.9vw;
            border-top: 1px solid rgba(80, 169, 211, 0.5);
            border-bottom: 1px solid rgba(80, 169, 211, 0.5);
            margin-top: 0.1vw;
            span{
                max-width:22.6vw;
                text-overflow: ellipsis;
                overflow: hidden;
                white-space: nowrap;
                display:inline-block;
            }
            .u-icon{
                width:1.09vw;
                height:1.02vw;
                margin-left:0.89vw;
                vertical-align:middle;
                margin-top:-0.73px;
            }
            .logout{
                margin-left:0.8vw;
                cursor: pointer;
            }
        }
    }
    .userName{
        .logout{
            margin-left:0.8vw;
            cursor: pointer;
        }
    }
}
// .user .home-userName img{width:1.09vw;height:1.02vw;margin-right:0.99vw;margin-left:0.89vw;vertical-align:middle;margin-top:-0.73px;}
.user .userName{width:22.66vw;height:2.6vw;color:#9ee9ff;font-size:1vw;font-weight: normal;font-stretch: normal;line-height:2.6vw;letter-spacing: 0px;
text-align: right;}
.user .userName img{width:1.09vw;height:1.02vw;margin-right:0.99vw;margin-left:0.89vw;vertical-align:middle;margin-top:-0.3vw;}
</style>