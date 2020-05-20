<template>
    <div class="login">
        <div class="login-box">
            <div class="l-title">
                <div>成都市科技金融智能服务平台</div>
                <img src="/image/home/close.png" class="close" @click="$emit('close')"/>
            </div>
            <div class="l-input">
                <div>帐号</div>
                <div>
                    <input type="text" v-model.number="loginForm.mobile" placeholder="请输入手机号或统一社会信用代码"/>
                </div>
            </div>
            <div class="l-input">
                <div>密码</div>
                <div>
                    <input type="password" v-model.trim="loginForm.password" placeholder="请输入密码"/>
                </div>
            </div>
            <div class="l-btn" @click="$emit('do-login',loginForm)">登录</div>
            <div class="reg" @click="$emit('to-reg')">没有账号？去注册</div>
            
        </div>
    </div>
</template>
<script>
export default {
    name:'Login',
    data(){
        return {
            loginForm:{
                mobile:'',
                password:''
            }
        }
    },
    methods:{

    }
}
</script>
<style scoped>
.login{min-width:1920px;min-height:1080px;position:absolute;top:0px;left:0px;}
.login-box{position:absolute;top:50%;left:50%;transform:translate(-50%,-50%);width:504px;height:290px;background:url(/image/home/login.png) center no-repeat;z-index:9;background-size: cover;}
.login:after{content:'';width:100%;height:100%;position:absolute;top:0px;left:0px;z-index:8;background:#000;opacity:0.5;}
.login .login-box .l-title{display:flex;justify-content:space-between;align-items:center;margin:38px 26px 16px 30px;font-family: FZLTXHK;
	font-size: 20px;
	font-weight: normal;
	font-stretch: normal;
	line-height: 30px;
	letter-spacing: 0px;
	color: #b7efff;}
.login .login-box .l-title .close{cursor:pointer;}
.login .login-box .l-input{display:flex;justify-content:center;align-items:center;margin-bottom:15px;}
.login .login-box .l-input div{font-family: PingFangSC-Regular;
	font-size: 14px;
	font-weight: normal;
	font-stretch: normal;
	letter-spacing: 0px;
	color: #7edcff;}
.login .login-box .l-input div:nth-child(1){text-align:right;margin-right: 40px;}
.login .login-box .l-input div:nth-child(2) input{border:none;outline:none;width:240px;height:32px;background:url(/image/home/l-input.png) center no-repeat;padding:0px 3px;}
.login .login-box .l-input div:nth-child(2) input:-webkit-input-placeholder{font-family: PingFangSC-Regular;
	font-size: 12px;
	font-weight: normal;
	font-stretch: normal;
	letter-spacing: 0px;
	color: #43575e;}
.login .login-box .l-btn{margin:20px auto 0px;width:187px;height:32px;background:url(/image/home/l-btn.png) center no-repeat;text-align:center;font-family: PingFangSC-Regular;
	font-size: 14px;cursor:pointer;line-height:32px;
	font-weight: normal;
	font-stretch: normal;
	letter-spacing: 0px;
	color: #7edcff;}
.login .login-box .reg{font-family: PingFangSC-Regular;margin:10px auto 0px;text-align:center;
	font-size: 12px;
	font-weight: normal;
	font-stretch: normal;
	letter-spacing: 0px;
	color: #7edcff;}
.login .login-box .reg{display:table;cursor:pointer;}
</style>