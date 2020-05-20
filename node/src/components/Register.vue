<template>
    <div class="login">
        <div class="login-box">
            <img src="/image/home/login.png" class="reg-img" />
            <div class="l-title">
                <div>注册</div>
                <img src="/image/home/close.png" class="close"  @click="$emit('close')"/>
            </div>
            <div class="l-input">
                <div>* 企业名称</div>
                <div>
                    <input type="text" name="username" placeholder="请输入企业工商名称"/>
                </div>
            </div>
            <div class="l-input">
                <div>* 统一社会信用代码</div>
                <div>
                    <input type="text" name="pwd" placeholder="请输入统一社会信用代码"/>
                </div>
            </div>
            <div class="l-input">
                <div>* 联系人</div>
                <div>
                    <input type="text" name="pwd" placeholder="请输入联系人"/>
                </div>
            </div>
            <div class="l-input">
                <div>* 联系电话</div>
                <div>
                    <input type="text" name="pwd" placeholder="请输入联系人电话"/>
                </div>
            </div>
            <div class="l-input">
                <div>* 密码</div>
                <div>
                    <input type="password" name="pwd" placeholder="请输入密码"/>
                </div>
            </div>
            <div class="l-input">
                <div>* 确认密码</div>
                <div>
                    <input type="password" name="pwd" placeholder="请确认密码"/>
                </div>
            </div>
            <div class="l-input l-input-code">
                <div>* 验证码</div>
                <div>
                    <input type="text" name="pwd" placeholder="请输入验证码"/>
                </div>
                <div class="code">获取验证码</div>
            </div>
            <div class="l-btns">
                <div class="l-btn" @click="$emit('do-reg',regForm)">确认注册</div>
                <div class="l-btn" @click="$emit('to-login')">返回登录</div>
            </div>
            
        </div>
    </div>
</template>
<script>
export default {
    name:'Re',
    data(){
        return {
            regForm:{
                
            }
        }
    },
    methods:{

    }
}
</script>
<style scoped>
.login{width:100%;min-height:1080px;position:absolute;top:0px;left:0px;}
.login-box{position:absolute;top:50%;left:50%;transform:translate(-50%,-50%);width:590px;height:523px;z-index:9;}
.login-box .reg-img{position:absolute;top:0px;left:0px;width:100%;height:600px;z-index:-1;}
.login:after{content:'';width:100%;height:100%;position:absolute;top:0px;left:0px;z-index:8;background:#000;opacity:0.5;}
.login .login-box .l-title{display:flex;justify-content:space-between;align-items:center;margin:85px 26px 16px 49px;font-family: FZLTXHK;
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
.login .login-box .l-input div:nth-child(1){text-align:right;margin-right: 40px;width: 124px;}
.login .login-box .l-input div:nth-child(2) input{border:none;outline:none;width:240px;height:32px;background:url(/image/home/l-input.png) center no-repeat;padding:0px 3px;}
.login .login-box .l-btn{margin:20px auto 0px;width:187px;height:32px;background:url(/image/home/l-btn.png) center no-repeat;text-align:center;font-family: PingFangSC-Regular;
	font-size: 14px;cursor:pointer;line-height:32px;
	font-weight: normal;
	font-stretch: normal;
	letter-spacing: 0px;
	color: #7edcff;}
.login .login-box .l-input-code div:nth-child(2) input{width:136px;height:32px;background:url(/image/home/r-input.png) center no-repeat;}
.login .login-box .code{width: 92px;
	height: 28px;line-height:28px;text-align:center;background-size:contain;
	background:url(/image/home/code.png) center no-repeat;
	border-radius: 2px;color:#7edcff;
	opacity: 1;margin-left:9px;}
.login .login-box .l-btns{display:flex;justify-content:center;}
.login .login-box .l-btns .l-btn:nth-child(2){margin-left:-45px;}
</style>