<template>
    <div class="login">
        <div class="login-box" v-if='!isForm'>
            <img src="/image/home/login.png" class="reg-img" />
            <div class="input-box">
                <div class="l-title">
                    <div>注册</div>
                    <img src="/image/home/close.png" class="close"  @click="$emit('close')"/>
                </div>
                <div class="l-input">
                    <div>* 企业名称</div>
                    <div>
                        <input type="text" v-model="regForm.companyName" placeholder="请输入企业工商名称"/>
                    </div>
                </div>
                <div class="l-input">
                    <div>* 统一社会信用代码</div>
                    <div>
                        <input type="text" v-model="regForm.creditCode" placeholder="请输入统一社会信用代码"/>
                    </div>
                </div>
                <div class="l-input">
                    <div>* 联系人</div>
                    <div>
                        <input type="text" v-model="regForm.userName" placeholder="请输入联系人"/>
                    </div>
                </div>
                <div class="l-input">
                    <div>* 联系电话</div>
                    <div>
                        <input type="text" v-model.number="regForm.mobile" placeholder="请输入联系人电话"/>
                    </div>
                </div>
                <div class="l-input">
                    <div>* 密码</div>
                    <div>
                        <input type="password" v-model="regForm.password" placeholder="请输入密码"/>
                    </div>
                </div>
                <div class="l-input">
                    <div>* 确认密码</div>
                    <div>
                        <input type="password" v-model="regForm.repassword" placeholder="请确认密码"/>
                    </div>
                </div>
                <div class="l-input l-input-code">
                    <div>* 验证码</div>
                    <div>
                        <input type="text" v-model="regForm.code" placeholder="请输入验证码"/>
                        <div class="code" @click="sendSms">{{codeStr}}</div>
                    </div>
                </div>
                <div class="l-btns">
                    <div class="l-btn" @click="doReg">确认注册</div>
                    <div class="l-btn" @click="$emit('to-login')">返回登录</div>
                </div>
            </div>
        </div>
        <div class="login-box" v-if='isForm' style="background:none;">
            <a-card title="注册" style="width:31vw;border-radius:1vw;">
                <a slot="extra" href="#"><img src="/image/home/close.png" class="close" @click="$emit('close')" style="padding:0px;width:27px;"/></a>
                <a-form-model ref="ruleForm" :model="regForm" :rules="rules" :label-col="{span:6}" :wrapper-col="{span:16}">
                    <a-form-model-item ref="companyName" label="企业名称" prop="companyName">
                        <a-input v-model="regForm.companyName" @blur="
                            () => {
                                $refs.companyName.onFieldBlur();
                            }
                            "
                        />
                        </a-form-model-item>
                    <a-form-model-item ref="creditCode" label="统一社会信用代码" prop="creditCode">
                        <a-input v-model="regForm.creditCode" @blur="
                            () => {
                                $refs.creditCode.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="userName" label="联系人" prop="userName">
                        <a-input v-model="regForm.userName" @blur="
                            () => {
                                $refs.userName.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="联系电话" prop="mobile">
                        <a-input v-model="regForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="password" label="密码" prop="password">
                        <a-input v-model="regForm.password" type="password" @blur="
                            () => {
                                $refs.password.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="repassword" label="确认密码" prop="repassword">
                        <a-input v-model="regForm.repassword" type="password" @blur="
                            () => {
                                $refs.repassword.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="code" label="验证码" prop="code">
                        <a-input v-model="regForm.code" @blur="
                            () => {
                                $refs.code.onFieldBlur();
                            }
                            "
                        >
                        <a-button type="link" slot="suffix" @click="sendSms">{{codeStr}}</a-button>
                        </a-input>
                    </a-form-model-item>
                    <a-form-model-item :wrapper-col="{ span: 20, offset: 2 }">
                        <a-button type="primary" @click="doReg" size="large" style="width:45%;">
                            确认注册
                        </a-button>
                        <a-button type="info" @click="$emit('to-login')" size="large" style="margin-left:10%;width:45%;">
                            返回登录
                        </a-button>
                    </a-form-model-item>
                </a-form-model>
            </a-card>
        </div>
    </div>
</template>
<script>
import { sendSms,checkSmsCode } from "@/common/lib/tools"
export default {
    name:'Reg',
    props:{
        isForm:{type:Boolean,default:false}
    },
    data(){
        let validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入确认密码'));
            } else if (value !== this.regForm.password) {
                callback(new Error("密码和确认密码不一致"));
            } else if(String(value).length!=8) {
                callback(new Error("请输入8位密码，包含字母、数字"));
            } else{
                callback();
            }
        };
        return {
            codeStr:'获取验证码',
            time:0,
            regForm:{
                userName:'',
                mobile:'',
                password:'',
                companyName:'',
                creditCode:'',
                code:'',
                repassword:''
            },
            rules: {
                userName: [
                    { required: true, message: '请输入联系人', trigger: 'blur' },
                ],
                mobile: [
                    { required: true, message: '请输入联系人电话', trigger: 'blur' },
                    { min: 11, max: 11, message: '请输入正确联系人电话', trigger: 'blur' },
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 8, max: 8, message: '请输入8位密码，包含字母、数字', trigger: 'blur' },
                ],
                repassword: [
                    { required: true, message: '请输入确认密码', trigger: 'blur' },
                    // { min: 8, max: 8, message: '请输入8位密码，包含字母、数字', trigger: 'blur' },
                    { validator: validatePass, trigger: 'blur' },
                ],
                companyName: [
                    { required: true, message: '请输入企业工商名称', trigger: 'blur' },
                ],
                creditCode: [
                    { required: true, message: '请输入统一社会信用代码', trigger: 'blur' },
                ],
                code: [
                    { required: true, message: '请输入验证码', trigger: 'blur' },
                    { min: 6, max: 6, message: '请输入正确的验证码', trigger: 'blur' },
                ],
            }
        }
    },
    methods:{
        doReg(){
            if(this.isForm){
                this.$refs.ruleForm.validate(valid => {
                    if (valid) {
                        let params = Object.assign({},this.regForm)
                        delete params.code;
                        delete params.repassword;
                        // 验证短信是否有效
                        checkSmsCode('/finance/userInfo/verifySmsCode',{mobile:params.mobile,smsCode:this.regForm.code,usageType:0}).then(res=>{
                            // 验证结果
                            if(res.content.smsCodeValid==1){
                                this.reg(params);
                            }else{
                                this.$message.error("验证码已过期")
                            }
                        }).catch(err=>this.$message.error(err))
                    } else {
                        return false;
                    }
                });
            }else{
                if(!this.regForm.companyName){
                    this.$message.error("请输入企业工商名称");
                    return ;
                }
                if(!this.regForm.creditCode){
                    this.$message.error("请输入统一社会信用代码");
                    return ;
                }
                if(!this.regForm.userName){
                    this.$message.error("请输入联系人");
                    return ;
                }
                if(!this.regForm.mobile || !/^1[3-9]{1}[0-9]{9}/.test(this.regForm.mobile) ){
                    this.$message.error("请输入正确的联系人电话");
                    return ;
                }
                if(!this.regForm.password || String(this.regForm.password).length != 8 ){
                    this.$message.error("请输入8位密码，包含字母、数字");
                    return ;
                }
                if(!this.regForm.repassword || String(this.regForm.password).length != 8 || this.regForm.repassword!==this.regForm.password ){
                    this.$message.error("请输入8位正确的确认密码，包含字母、数字");
                    return ;
                }
                if(!this.regForm.code || String(this.regForm.code).length != 6 ){
                    this.$message.error("请输入正确的验证码");
                    return ;
                }
                let params = Object.assign({},this.regForm)
                delete params.code;
                delete params.repassword;
                // 验证短信是否有效
                checkSmsCode('/finance/userInfo/verifySmsCode',{mobile:params.mobile,smsCode:this.regForm.code,usageType:0}).then(res=>{
                    // 验证结果
                    if(res.content.smsCodeValid==1){
                        this.reg(params);
                    }else{
                        this.$message.error("验证码已过期")
                    }
                }).catch(err=>this.$message.error(err))

            }
            // this.$emit('do-reg',regForm);
        },
        reg(params){
            // 注册
            this.$http.post('/finance/userInfo/register',params).then(res=>{
                if(res.data.code==0){
                    this.$message.success('注册成功');
                    this.$emit('do-reg',this.regForm);
                }else{
                    this.$message.success(res.data.msg);
                }
            })
        },
        sendSms(){
            if(this.isForm){
                if(!this.regForm.mobile){
                    this.$refs.mobile.onFieldBlur();
                    return ;
                }
                if(this.regForm.mobile && !/^1[3-9]{1}[0-9]{9}/.test(this.regForm.mobile)){
                    this.$refs.mobile.onFieldBlur();
                    return ;
                }
            }else{
                if(!this.regForm.mobile){
                    this.$message.error('请输入联系人电话');
                    return ;
                }
                if(this.regForm.mobile && !/^1[3-9]{1}[0-9]{9}/.test(this.regForm.mobile)){
                    this.$message.error('请输入正确联系人电话');
                    return ;
                }
            }
            if(this.time==0){
                sendSms('/finance/userInfo/sendSmsCode',{mobile:this.regForm.mobile,usageType:0}).then(res=>{
                    this.time = 60;
                    this.codeStr = this.time+'s'
                    let timer = setInterval(()=>{
                        if(this.time==0){
                            this.codeStr = '获取验证码';
                            clearInterval(timer);
                            return ;
                        }
                        this.time--;
                        this.codeStr = this.time+'s'
                    },1000)
                }).catch(err=>{
                    this.$message.error(err);
                })
            }
        }
    }
}
</script>
<style lang="scss" scoped>
.login{
    position:absolute;
    top:0vw;
    left:0vw;
    width:100vw;
    height:100vh;
    background-color: rgba(0, 0, 0, 0.75);
    z-index:9;
    .login-box{
        width:31vw;
        height:27vw;
        position: absolute;
        top:50%;
        left:50%;
        transform:translate(-50%,-50%);
        // background:url(/image/home/login.png) center no-repeat;
        background-size:cover;
        .reg-img{
            position: absolute;
            top: 53%;
            left: 50%;
            width: 33vw;
            height: 35vw;
            transform: translate(-50%, -50%);
            z-index: 0;
        }
        .input-box{
            position: relative;
            z-index:10;
        }
        .l-title{
            position:relative;
            z-index:10;
            display:flex;
            justify-content:space-between;
            margin:2vw 1vw 1vw;
            div{
                font-family: FZLTXHK;
                font-size: 1vw;
                font-weight: normal;
                font-stretch: normal;
                line-height: 2vw;
                letter-spacing: 0vw;
                color: #75d7ff;
            }
            .close{
                width:2vw;
                height:2vw;
                cursor: pointer;
            }
        }
        .l-input{
            height:2vw;
            padding:0 2vw;
            margin:0vw auto 1vw;
            display:flex;
            justify-content:space-between;
            align-items:center;
            div{
                font-family: PingFangSC-Regular;
                font-size: 0.8vw;
                font-weight: normal;
                font-stretch: normal;
                letter-spacing: 0vw;
                color: #7edcff;
            }
            div:nth-child(2){
                input{
                    width:15vw;
                    height:2vw;
                    padding:0 0.3vw;
                    background:url(/image/home/l-input.png) left no-repeat,url(/image/home/l-input.png) right no-repeat;
                    background-size:cover;
                    border:none;
                    outline:none;
                    font-size: 0.63vw;
                    font-weight: normal;
                    font-stretch: normal;
                    letter-spacing: 0vw;
                    color: #7edcff;
                    &::-webkit-input-placeholder{
                        font-family: PingFangSC-Regular;
                        font-size: 0.63vw;
                        font-weight: normal;
                        font-stretch: normal;
                        letter-spacing: 0vw;
                        color: #43575e;
                    }
                }
            }
        }
        .l-input-code{
            div:nth-child(2){
                width:15vw;
                display:flex;
                justify-content:center;
                input{
                    width:7vw;
                    height:2vw;
                    background:url(/image/home/l-input.png) left no-repeat,url(/image/home/l-input.png) right no-repeat;
                    background-size:cover;
                    border:none;
                    outline:none;
                }
                .code{
                    width:7vw;
                    height:2vw;
                    line-height:2vw;
                    margin-left:1vw;
                    padding:0 1vw;
                    text-align:center;
                    background:url(/image/home/l-input.png) left no-repeat,url(/image/home/l-input.png) right no-repeat;
                    background-size:cover;
                    font-family: PingFangSC-Regular;
                    font-size: 0.8vw;
                    font-weight: normal;
                    font-stretch: normal;
                    letter-spacing: 0vw;
                    color: #7edcff;
                    cursor: pointer;
                }
            }
        }
        .l-btns{
            padding:0 2vw;
            display:flex;
            justify-content:space-between;
            .l-btn{
                width: 12vw;
                height:3vw;
                background:url(/image/home/l-btn.png) center no-repeat;
                background-size:cover;
                text-align:center;
                line-height:3vw;
                font-family: PingFangSC-Regular;
                font-size: 1vw;
                font-weight: normal;
                font-stretch: normal;
                letter-spacing: 0vw;
                color: #7edcff;
                cursor: pointer;
            }
        }
    }
}
</style>