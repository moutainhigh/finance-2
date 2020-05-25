(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-5825076d"],{"64ab":function(t,o,e){"use strict";e.r(o);var i=function(){var t=this,o=t.$createElement,e=t._self._c||o;return e("div",{staticClass:"login"},[t.isForm?t._e():e("div",{staticClass:"login-box"},[e("div",{staticClass:"l-title"},[e("div",[t._v("成都市科技金融智能服务平台")]),e("img",{staticClass:"close",attrs:{src:"/image/home/close.png"},on:{click:function(o){return t.$emit("close")}}})]),e("div",{staticClass:"l-input"},[e("div",[t._v("帐号")]),e("div",[e("input",{directives:[{name:"model",rawName:"v-model.number",value:t.loginForm.mobile,expression:"loginForm.mobile",modifiers:{number:!0}}],attrs:{type:"text",placeholder:"请输入手机号或统一社会信用代码"},domProps:{value:t.loginForm.mobile},on:{input:function(o){o.target.composing||t.$set(t.loginForm,"mobile",t._n(o.target.value))},blur:function(o){return t.$forceUpdate()}}})])]),e("div",{staticClass:"l-input"},[e("div",[t._v("密码")]),e("div",[e("input",{directives:[{name:"model",rawName:"v-model.trim",value:t.loginForm.password,expression:"loginForm.password",modifiers:{trim:!0}}],attrs:{type:"password",placeholder:"请输入密码"},domProps:{value:t.loginForm.password},on:{input:function(o){o.target.composing||t.$set(t.loginForm,"password",o.target.value.trim())},blur:function(o){return t.$forceUpdate()}}})])]),e("div",{staticClass:"l-btn",on:{click:t.doLogin}},[t._v("登录")]),e("div",{staticClass:"reg"},[e("span",{staticStyle:{color:"#fff"},on:{click:function(o){return t.$emit("to-reg")}}},[t._v("没有账号？")]),e("span",{on:{click:function(o){return t.$emit("to-reg")}}},[t._v("去注册")]),e("a-button",{staticStyle:{"margin-left":"1.5vw",color:"#fff"},attrs:{type:"link"},on:{click:function(o){return t.$emit("to-forget")}}},[t._v("忘记密码")])],1)]),t.isForm?e("div",{staticClass:"login-box",staticStyle:{background:"none"}},[e("a-card",{staticStyle:{width:"504px","border-radius":"15px"},attrs:{title:"成都市科技金融智能服务平台"}},[e("a",{attrs:{slot:"extra",href:"#"},slot:"extra"},[e("img",{staticClass:"close",staticStyle:{padding:"0px",width:"27px"},attrs:{src:"/image/home/close.png"},on:{click:function(o){return t.$emit("close")}}})]),e("a-form-model",{ref:"ruleForm",attrs:{model:t.loginForm,rules:t.rules,"label-col":{span:4},"wrapper-col":{span:18}}},[e("a-form-model-item",{ref:"mobile",staticClass:"input-item",attrs:{label:"帐号",prop:"mobile"}},[e("a-input",{on:{blur:function(){t.$refs.mobile.onFieldBlur()}},model:{value:t.loginForm.mobile,callback:function(o){t.$set(t.loginForm,"mobile",o)},expression:"loginForm.mobile"}})],1),e("a-form-model-item",{ref:"password",staticClass:"input-item",attrs:{label:"密码",prop:"password"}},[e("a-input",{attrs:{type:"password"},on:{blur:function(){t.$refs.password.onFieldBlur()}},model:{value:t.loginForm.password,callback:function(o){t.$set(t.loginForm,"password",o)},expression:"loginForm.password"}})],1),e("a-form-model-item",{attrs:{"wrapper-col":{span:20,offset:2}}},[e("a-button",{attrs:{type:"primary",block:"",size:"large"},on:{click:t.doLogin}},[t._v(" 登录 ")]),e("a-button",{staticStyle:{"margin-left":"10px",color:"#7edcff"},attrs:{type:"link"},on:{click:function(o){return t.$emit("to-reg")}}},[t._v(" 没有帐号，去注册 ")]),e("a-button",{staticStyle:{"margin-left":"1vw"},attrs:{type:"link"},on:{click:function(o){return t.$emit("to-forget")}}},[t._v(" 忘记密码 ")])],1)],1)],1)],1):t._e()])},r=[],s=e("80ee"),n=e("c585"),l=e("add3"),a={name:"Login",props:{isForm:{type:Boolean,default:!1}},data:function(){return{loginForm:{mobile:"",password:""},rules:{mobile:[{required:!0,message:"请输入手机号或统一社会信用代码",trigger:"blur"}],password:[{required:!0,message:"请输入密码",trigger:"blur"},{min:8,max:8,message:"请输入8位密码，包含字母、数字",trigger:"blur"}]}}},methods:Object(s["a"])(Object(s["a"])({},Object(l["b"])(["setUserInfo"])),{},{doLogin:function(){var t=this;if(this.isForm)this.$refs.ruleForm.validate((function(o){if(!o)return!1;11!=String(t.loginForm.mobile).length||/^1[3-9]{1}[0-9]{9}/.test(t.loginForm.mobile)?t.login(t.loginForm):t.$message.error("请输入手机号或统一社会信用代码")}));else{if(!this.loginForm.mobile||11==String(this.loginForm.mobile).length&&!/^1[3-9]{1}[0-9]{9}/.test(this.loginForm.mobile))return void this.$message.error("请输入手机号或统一社会信用代码");if(!this.loginForm.password||8!=String(this.loginForm.password).length)return void this.$message.error("请输入8位密码，包含字母、数字");this.login(this.loginForm)}},login:function(t){var o=this;this.$message.destroy(),this.$message.loading("登录中",0),this.$http.post("/finance/userInfo/login",t).then((function(t){o.$message.destroy(),0==t.data.code?(Object(n["f"])("cdjr_token",t.data.content.token,120),o.setUserInfo(t.data.content.userInfo),localStorage.setItem("userInfo",JSON.stringify(t.data.content.userInfo)),o.$message.loading("登录成功",1).then((function(){o.$emit("do-login",o.loginForm)}))):o.$message.error(t.data.msg)}))}})},m=a,c=(e("c462"),e("376d")),u=Object(c["a"])(m,i,r,!1,null,"5702f1bc",null);o["default"]=u.exports},b156:function(t,o,e){},c462:function(t,o,e){"use strict";var i=e("b156"),r=e.n(i);r.a}}]);