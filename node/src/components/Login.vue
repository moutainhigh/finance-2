<template>
    <div :class="isForm?'loginx':'login'">
        <div class="login-box" v-if="!isForm">
            <div class="l-title">
                <div>成都市科技金融智能服务平台</div>
                <img src="/image/home/close.png" class="close" @click="$emit('close')"/>
            </div>
            <div class="l-input">
                <div>帐号</div>
                <div>
                    <input type="text" v-model.trim="loginForm.mobile" placeholder="请输入手机号或统一社会信用代码"/>
                </div>
            </div>
            <div class="l-input">
                <div>密码</div>
                <div>
                    <input type="password" v-model.trim="loginForm.password" placeholder="请输入密码"/>
                </div>
            </div>
            <div class="l-btn" @click="doLogin">登录</div>
            <div class="reg"  >
                <span style="color:#fff;" @click="$emit('to-reg')">没有账号？</span>
                <span @click="$emit('to-reg')">去注册</span>
                <span style="margin-left:1.5vw;color:#fff;" @click="$emit('to-forget')">忘记密码</span>
            </div>
        </div>
        <div class="login-box" v-if="isForm" style="background:none;">
            <a-card title="成都市科技金融智能服务平台" style="width:504px;border-radius:15px;">
                <a slot="extra" href="#"><img src="/image/home/close.png" class="close" @click="$emit('close')" style="padding:0px;width:27px;"/></a>
                <a-form-model ref="ruleForm" :model="loginForm" :rules="rules" :label-col="{span:4}" :wrapper-col="{span:18}">
                    <a-form-model-item ref="mobile" label="帐号" prop="mobile" class="input-item">
                        <a-input v-model="loginForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            "
                        />
                        </a-form-model-item>
                    <a-form-model-item ref="password" label="密码" prop="password" class="input-item">
                        <a-input v-model="loginForm.password" type="password" @blur="
                            () => {
                                $refs.password.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item :wrapper-col="{ span: 20, offset: 2 }">
                        <a-button type="primary" @click="doLogin" block size="large">
                            登录
                        </a-button>
                        <a-button style="margin-left: 10px;color:#7edcff;" @click="$emit('to-reg')" type="link">
                            没有帐号，去注册
                        </a-button>
                        <a-button style="margin-left: 10px;margin-left:1vw;" @click="$emit('to-forget')" type="link">
                            忘记密码
                        </a-button>
                    </a-form-model-item>
                </a-form-model>
            </a-card>
        </div>
    </div>
</template>
<script>
import {storage} from '@/common/lib/tools.js'
import {mapActions} from 'vuex';
export default {
    name:'Login',
    props:{
        isForm:{type:Boolean,default:false}
    },
    data(){
        return {
            loginForm:{
                mobile:'',
                password:'',
                loginType:0
            },
            rules: {
                mobile: [
                    { required: true, message: '请输入手机号或统一社会信用代码', trigger: 'blur' },
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 8, max: 8, message: '请输入8位密码，包含字母、数字', trigger: 'blur' },
                ],
            }
        }
    },
    methods:{
        ...mapActions(['setUserInfo','setToken']),
        doLogin(){
            if(this.isForm){
                this.$refs.ruleForm.validate(valid => {
                    if (valid) {
                        // 如果是手机号码，处理手机号码是否正确
                        if(String(this.loginForm.mobile).length==11 && !/^1[3-9]{1}[0-9]{9}/.test(this.loginForm.mobile) ){
                            this.$message.error("请输入手机号或统一社会信用代码");
                            return ;
                        }
                        this.login(this.loginForm);
                    } else {
                        return false;
                    }
                });
            }else{
                if(!this.loginForm.mobile || (String(this.loginForm.mobile).length==11 && !/^1[3-9]{1}[0-9]{9}/.test(this.loginForm.mobile)) ){
                    this.$message.error("请输入手机号或统一社会信用代码");
                    return ;
                }
                if(!this.loginForm.password || String(this.loginForm.password).length != 8 ){
                    this.$message.error("请输入8位密码，包含字母、数字");
                    return ;
                }
                this.login(this.loginForm);

            }
            // this.$emit('do-reg',regForm);
        },
        login(loginForm){
            this.$message.destroy();
            this.$message.loading('登录中',0)
            this.$http.post('/finance/userInfo/login',loginForm).then(res=>{
                this.$message.destroy();
                // 判断服务器端数据是否有误
                if(res.data.code!=0){
                    this.$message.error(res.data.msg);
                    return ;
                }
                // 登录成功存储用户信息
                storage('cdjr_token',res.data.content.token,2*60);
                this.setToken(res.data.content.token)
                // localStorage.setItem('cdjr_token',res.data.content.token);
                this.setUserInfo(res.data.content.userInfo);
                localStorage.setItem('userInfo',JSON.stringify(res.data.content.userInfo));
                this.$message.loading('登录成功',1).then(()=>{
                    // 处理完后关闭登录窗口
                    this.$emit('do-login',this.loginForm)
                });
            });
        }
    }
}
</script>
<style scoped>
*{padding:0px;margin:0px;}
.login{position:absolute;top:0px;left:0px;}
/* .loginx{width:100%;min-height:100vh;position:absolute;top:0px;left:0px;} */
.login-box{position:fixed;top:50%;left:50%;transform:translate(-50%,-50%);width:28vw;height:17vw;background:url(/image/home/login.png) center no-repeat;z-index:9;background-size: cover;}
.login-box >>> .input-item{margin-bottom:20px !important;}
.login:after{content:'';width:100%;height:100%;position:absolute;top:0px;left:0px;z-index:8;background:#000;opacity:0.5;}
.login-box .l-title{display:flex;justify-content:space-between;align-items:center;margin:2.5vw 1.5vw 0.5vw;font-family: FZLTXHK;
	font-size: 1vw;
	font-weight: normal;
	font-stretch: normal;
	line-height: 1vw;
	letter-spacing: 0px;
	color: #b7efff;}
.login-box .l-title .close{cursor:pointer;width:2vw;height:2vw}
.login-box .l-input{display:flex;justify-content:center;align-items:center;margin-bottom:1vw;}
.login-box .l-input div{font-family: PingFangSC-Regular;
	font-size: 1vw;
	font-weight: normal;
	font-stretch: normal;
	letter-spacing: 0px;
	color: #7edcff;}
.login-box .l-input div:nth-child(1){text-align:right;margin-right: 1.5vw;}
.login-box .l-input div:nth-child(2) input{border:none;outline:none;width:16vw;height:2.1vw;
    background:url(/image/home/l-input.png) bottom no-repeat,url(/image/home/l-input.png) top no-repeat;
    font-size: 0.8vw;
    padding:0px 1vw;background-size:cover;}
.login-box .l-input div:nth-child(2) input:-webkit-input-placeholder{font-family: PingFangSC-Regular;
	font-size: 0.8vw;
	font-weight: normal;
	font-stretch: normal;
	letter-spacing: 0px;
	color: #43575e;}
.login-box .l-btn{margin:1vw auto 0px;width:13vw;height:2.3vw;background:url(/image/home/l-btn.png) center no-repeat;background-size:cover;text-align:center;font-family: PingFangSC-Regular;
	font-size: 1vw;cursor:pointer;line-height:2.3vw;
	font-weight: normal;
	font-stretch: normal;
	letter-spacing: 0vw;
	color: #7edcff;}
.login-box .reg{font-family: PingFangSC-Regular;margin:0.5vw auto 0vw;text-align:center;
	font-size: 1vw;
	font-weight: normal;
	font-stretch: normal;
	letter-spacing: 0vw;
	color: #7edcff;}
.login-box .reg{display:table;cursor:pointer;font-family: PingFangSC-Regular;
	font-size: 0.63vw;
	font-weight: normal;
	font-stretch: normal;
	letter-spacing: 0vw;
	color: #7edcff;}
</style>