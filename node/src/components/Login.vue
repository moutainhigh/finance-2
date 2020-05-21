<template>
    <div class="login">
        <div class="login-box" v-if="!isForm">
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
        <div class="login-box" v-if="isForm">
            <a-card title="成都市科技金融智能服务平台" style="width:504px;border-radius:15px;">
                <a slot="extra" href="#"><img src="/image/home/close.png" class="close" @click="$emit('close')" style="padding:0px;width:27px;"/></a>
                <a-form-model ref="ruleForm" :model="loginForm" :rules="rules" :label-col="{span:4}" :wrapper-col="{span:18}">
                    <a-form-model-item ref="mobile" label="帐号" prop="mobile">
                        <a-input v-model="loginForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            "
                        />
                        </a-form-model-item>
                    <a-form-model-item ref="password" label="密码" prop="password">
                        <a-input v-model="loginForm.password" @blur="
                            () => {
                                $refs.password.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item :wrapper-col="{ span: 20, offset: 2 }">
                        <a-button type="primary" @click="$emit('do-login',loginForm)" block size="large">
                            登录
                        </a-button>
                        <a-button style="margin-left: 10px;" @click="$emit('to-reg')" type="link">
                            没有帐号，去注册
                        </a-button>
                        <a-button style="margin-left: 10px;" @click="$emit('to-forget')" type="link">
                            忘记密码
                        </a-button>
                    </a-form-model-item>
                </a-form-model>
            </a-card>
        </div>
    </div>
</template>
<script>
export default {
    name:'Login',
    props:{
        isForm:{type:Boolean,default:false}
    },
    data(){
        return {
            loginForm:{
                mobile:'',
                password:''
            },
            rules: {
                mobile: [
                    { required: true, message: '请输入', trigger: 'blur' },
                    { min: 3, max: 5, message: 'Length should be 3 to 5', trigger: 'blur' },
                ],
                password: [
                    { required: true, message: '请输入', trigger: 'blur' },
                    { min: 3, max: 5, message: 'Length should be 3 to 5', trigger: 'blur' },
                ],
            }
        }
    },
    methods:{

    }
}
</script>
<style scoped>
.login{width:100%;min-height:1080px;position:absolute;top:0px;left:0px;}
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