<template>
    <div class="header">
        <div class="left">
            <!-- <div class="logo">CTG</div>
            <div>成都科技服务</div> -->
            <img src="/image/logo.png" alt="" @click="toPage('/')" style="width:320px;height:50px;cursor:pointer;">
        </div>
        <div class="nav">
            <div class="nav-item" @click="toPage('/')" :class="$route.path=='/'?'active':''">首页</div>
            <div class="nav-item" @click="toPage('/guquan')" :class="$route.path=='/guquan'?'active':''">股权融资</div>
            <div class="nav-item" @click="toPage('/zhaiquan')" :class="$route.path=='/zhaiquan'?'active':''">债券融资</div>
            <div class="nav-item" @click="toPage('/guquan')">一键匹配</div>
        </div>
        <div class="user">
            <div class="btn logo" @click="$emit('do-login')">登录</div>
            <div class="btn reg" @click="$emit('to-reg')">注册</div>
        </div>
    </div>
</template>
<script>
export default {
    name:'Header',
    data(){
        return {

        }
    },
    methods:{
        toPage(url){
            this.$router.push({path:url});
        }
    }

}
</script>
<style scoped>
.header{padding: 13px 125px;height: 66px;display:flex;justify-content:space-between;}
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
</style>