<template>
    <div class="header" :class="isBg?'headerbg':''">
        <div class="left">
            <!-- <div class="logo">CTG</div>
            <div>成都科技服务</div> -->
            <img src="/image/logo.png" alt="" @click="toPage('/')" style="width:320px;height:50px;cursor:pointer;">
        </div>
        <div class="nav">
            <div class="nav-item" @click="toPage('/')" :class="$route.path=='/'?'active':''">首页</div>
            <div class="nav-item" @click="toPage('/guquan')" :class="$route.path=='/guquan'?'active':''">股权融资</div>
            <div class="nav-item" @click="toPage('/zhaiquan')" :class="$route.path=='/zhaiquan'?'active':''">债券融资</div>
            <div class="nav-item match" @click="toPage('/match')" :class="$route.path=='/match'?'active':''">一键匹配</div>
        </div>
        <div class="user">
            <div class="btn logo" @click="toPage('/','do-login')" v-show="!$store.state.userInfo.userName">登录</div>
            <div class="btn reg" @click="toPage('/','to-reg')" v-show="!$store.state.userInfo.userName">注册</div>
            <UserInfo :userInfo="$store.state.userInfo" v-show="$store.state.userInfo.userName"></UserInfo>
        </div>
    </div>
</template>
<script>
export default {
    name:'Header',
    props:{
        isBg:{type:Boolean,default:false}
    },
    data(){
        return {

        }
    },
    methods:{
        toPage(url,flag){
            if(flag=='do-login'){
                this.$router.push({name:'Home',params:{islogin:1}});
            }
            if(flag=='to-reg'){
                this.$router.push({name:'Home',params:{isreg:1}});
            }
            this.$router.push({path:url})
            // $emit('do-login')
            // $emit('to-reg')
        }
    },
    components:{
        UserInfo:()=>import("@/components/UserInfo.vue"),
    }

}
</script>
<style lang='scss' scoped>
.header{padding: 13px 125px;height: 66px;display:flex;justify-content:space-between;}
.headerbg{background:#06162c;}
.header .left{display:flex;align-items:center;font-size:28px;color:#fff;}
.header .left .logo{padding-top:10px;margin-right:15px;}
.header .nav{display:flex;justify-content:flex-start;align-items:center;flex: 1;margin-left:104px;}
.header .nav .nav-item{padding:0px 11px;width:78px;font-size:14px;position: relative;cursor: pointer;color:#fff;text-align:center;}
.header .nav .nav-item:hover::after{content:'';display:block;width:26px;height:2px;background:#9ee9ff;position: absolute;bottom:-4px;left:50%;transform:translate(-50%);}
.header .nav .nav-item:last-child:hover::after{content:'';display:none;width:26px;height:2px;background:#9ee9ff;position: absolute;bottom:-4px;left:50%;transform:translate(-50%);}
.header .nav .active::after{content:'';display:block;width:26px;height:2px;background:#9ee9ff;position: absolute;bottom:-4px;left:50%;transform:translate(-50%);}
.header .user{display:flex;justify-content:flex-start;align-items: center;}
.header .user .btn{padding:5px 15px;border-radius:50px;font-size:12px;letter-spacing:1px;margin-left:15px;color:#fff;}
.header .user .logo{padding:5px 15px;border-radius:50px;background:#9ee9ff;font-size:12px;letter-spacing:1px;margin-left:15px;color:#fff;cursor: pointer;}
.header .user .reg{padding:5px 15px;border-radius:50px;border:1px solid #fff;font-size:12px;letter-spacing:1px;margin-left:15px;color:#fff;cursor: pointer;}
.header .user .btn:hover{background:#9ee9ff;}
</style>