<template>
  <div class="home-box">
    <div class="header">
      <div class="left">
        <div class="logo">
          <img src="/image/home/logo.png" alt="">
        </div>
        <div class="nav">
          <div class="nav-item" @click="toPage('/')">首页</div>
          <div class="nav-item" @click="toPage('/guquan')">股权融资</div>
          <div class="nav-item" @click="toPage('/zhaiquan')">债权融资</div>
        </div>
      </div>
      <div class="right">
        <div class="btn" @click="login">登录</div>
        <div class="btn" @click="reg">注册</div>
      </div>
    </div>
    <div class="main">
      <img src="/image/home/main.png" class="main-bg" />
      <div class="main-body">
        <div class="main-left">
          <div class="main-left-tab">
            <div class="active">股权机构</div>
            <div>债券机构</div>
          </div>
          <div class="main-left-list">
            <div class="main-list-item" v-for="item in 10" :key="item" :class="item==1?'item-active':''">
              成都技转创投{{item}}
            </div>
          </div>
        </div>
        <div class="left-anim">
          <img src="/image/home/left-animbg.png" />
        </div>
        <div class="main-center">
          <div class="c-map">
            <img src="/image/home/cd-map.png" usemap="#cdmap" width="802" height="594"/>
          </div>
          <div class="c-info">
            <div class="c-title">成都技转创投</div>
            <ul>
              <li>物联网投资基金</li>
              <li>中小企业专项投资</li>
              <li>创新医药投资基金</li>
            </ul>
          </div>
          <div class="c-bottom">
            <img src="/image/home/znz.png"/>
          </div>
          <map name="cdmap" id="cdmap">
            <area shape="rect" coords="0,0,259,256,100,100"  alt="新疆" href="/">
            <area shape="circle" coords="129,161,10" href="mercur.htm" alt="Mercury" />
          </map>
        </div>
        <div class="right-anim">
          <img src="/image/home/right-animbg.png" />
        </div>
        <div class="main-right">
          <div class="main-right-top">
            <div>一键匹配</div>
          </div>
          <div class="main-right-list">
            <div class="list-title">当前结果</div>
            <div class="right-list-item" v-for="item in 6" :key="item">
                <div>企业入驻（家）</div>
                <div>1039</div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <Login v-show="isLogin"></Login>
    <Register v-show="isReg"></Register>
  </div>
</template>
<script>
export default {
  data(){
    return {
      isLogin:false,
      isReg:false,
    }
  },
  methods: {
    login(){
      this.isLogin = true;
    },
    reg(){
      this.isReg = true;
    },
    toPage(url,params){
      if(params){
        this.$router.push({path:url,params:params});
      }else{
        this.$router.push({path:url}).catch((err)=>{});
      }
    },
  },
  components:{
    Login:()=>import("@/components/Login.vue"),
    Register:()=>import("@/components/Register.vue"),
  }
}
</script>
<style scoped>
.home-box{min-width:1920px;min-height:1080px;background:url(/image/home/bg.png);}
/* .home-box{min-width:100vw;min-height:100vh;background:url(/image/home/bg.png);} */
.home-box .header{padding:17px 63px 7px;display:flex;justify-content:space-between;}
.home-box .header .left{display:flex;justify-content:space-between;}
.home-box .header .left .nav{display:flex;justify-content:flex-start;align-items:center;margin-left:75px;}
.home-box .header .left .nav .nav-item{width:129px;height:50px;background:url(/image/home/btn.png) center -8px no-repeat;background-size:cover;
	font-family: FZLTXHK;font-size: 20px;font-weight: normal;font-stretch: normal;letter-spacing: 0px;color: #9ee9ff;text-shadow: 0px 0px 8px rgba(0, 90, 255, 0.2);
  text-align:center;line-height:52px;cursor:pointer;
}
  .home-box .header .left .nav .nav-item:hover{background:url(/image/home/btn-act.png) center -8px no-repeat;background-size:cover;color:#abff9b;}
  .home-box .header .left .nav .btn-action{background:url(/image/home/btn-act.png) center -8px no-repeat;background-size:cover;color:#abff9b;}

.home-box .header .left .logo{display:flex;align-items:center;}
.home-box .header .left .logo img{width:500px;height:56px;cursor:pointer;}
.home-box .header .right{display:flex;align-items:center;}
.home-box .header .right .btn{width:129px;height:50px;background:url(/image/home/btn.png) center -8px no-repeat;background-size:cover;
	font-family: FZLTXHK;font-size: 20px;font-weight: normal;font-stretch: normal;letter-spacing: 0px;color: #9ee9ff;text-shadow: 0px 0px 8px rgba(0, 90, 255, 0.2);
  text-align:center;line-height:52px;cursor:pointer;}
  .home-box .header .right .btn:hover{background:url(/image/home/btn-act.png) center -8px no-repeat;background-size:cover;color:#abff9b;}


.main{height:988px;position:relative;margin:0px 0px;background:url(/image/home/map.png) center no-repeat;}
.main .main-bg{position:absolute;top:0px;left:0px;width:100%;height:100%;z-index:0;}
.main .main-body{display:flex;justify-content:space-between;position:relative;z-index:6;margin:0px 65px 0 60px;}
.main .main-body .main-left{margin-top:84px;width:317px;}
.main .main-body .main-left-tab{margin:0 auto;background: url(/image/home/tab.png) center center no-repeat;background-size: cover;width: 235px;height: 26px;display: flex;justify-content: space-between;align-items: center;padding: 0px 33px;}
.main .main-body .main-left-tab div{cursor:pointer;font-family: FZLTXHK;font-size: 20px;font-weight: normal;font-stretch: normal;line-height: 24px;letter-spacing: 0px;color: #9ee9ff;}
.main .main-body .main-left-tab div.active{color: #ffd600;position:relative;font-size:18px;}
.main .main-body .main-left-tab div.active:after{content:'';width:100%;height:2px;background:#ffd600;position:absolute;bottom:-2px;left:50%;transform:translate(-50%);}
.main .main-body .main-left .main-left-list{margin:28px auto 0px;display:flex;flex-direction:column;align-items:center;}
.main .main-body .main-left .main-left-list .main-list-item{width:248px;height:50px;background-size:cover;line-height:50px;text-align:center;	font-family: PingFangSC-Medium;
	font-size: 18px;margin-bottom:26px;cursor:pointer;
  background-image:url(/image/home/left.png);
  background-repeat:no-repeat;
  background-position: center;
	font-weight: normal;
	font-stretch: normal;
	letter-spacing: 0px;
	color: #c1edfd;}
.main .main-body .main-left .main-left-list .main-list-item{position:relative;}
.main .main-body .main-left .main-left-list .item-active{color:#ffd600;}
.main .main-body .main-left .main-left-list .item-active:before{
  content:'';width:300px;height:58px;
  background-image:url(/image/home/left-act.png);
  background-repeat:no-repeat;
  background-position: center;
  background-size: cover;
  position: absolute;
  top: -4px;
  left: -30px;
}
.main .main-body .main-left .main-left-list .item-active:after{
  content: '';
  width: 56px;
  height: 57px;
  background-image: url(/image/home/left-p.png);
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  position: absolute;
  top: -4px;
  right: -48px;
}
.main .main-body .main-left .main-left-list .main-list-item:hover{color:#ffd600;}
.main .main-body .main-left .main-left-list .main-list-item:hover:before{
  content:'';width:300px;height:58px;
  background-image:url(/image/home/left-act.png);
  background-repeat:no-repeat;
  background-position: center;
  background-size: cover;
  position: absolute;
  top: -4px;
  left: -30px;
}
.main .main-body .main-left .main-left-list .main-list-item:hover:after{
  content: '';
  width: 56px;
  height: 57px;
  background-image: url(/image/home/left-p.png);
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  position: absolute;
  top: -4px;
  right: -48px;
}
.main .main-body .main-center{margin-top:20px;flex:1;position: relative;}
.main .main-body .main-center .c-map{width:100%;height:100%;display:flex;justify-content:center;align-items:center;}
.main .main-body .main-center .c-info{width:179px;height:189px;background:url(/image/home/info.png) center no-repeat;position:absolute;top:170px;right: 10px;}
.main .main-body .main-center .c-info .c-title{font-family: PingFangSC-Medium;position:relative;
	font-size: 18px;margin:21px 0px 11px 18px;
	font-weight: normal;
	font-stretch: normal;
	letter-spacing: 2px;
	color: #ffd600;}
.main .main-body .main-center .c-info .c-title:after{content:'';width: 142px;height: 1px;background:#7edcff;position:absolute;bottom:-11px;left:0;}
.main .main-body .main-center .c-info ul{padding-top:10px;}
.main .main-body .main-center .c-info ul li{font-family: FZLTXHK;font-size: 14px;font-weight: normal;font-stretch: normal;line-height: 26px;letter-spacing: 1px;
	color: #ffd600;
}
.main .main-body .main-center .c-bottom{position:absolute;bottom:30px;left:10px;}


.main .main-body .main-right{margin-top:84px;width:331px;}
.main .main-body .main-right .main-right-top{display:flex;justify-content:center;align-items:center;width:155px;height:155px;background:url(/image/home/right-top.png) center no-repeat;background-size:cover;margin:0px auto;}
.main .main-body .main-right .main-right-top div{font-family: FZLTCHJW--GB1-0;font-size: 22px;
	font-weight: normal;
	font-stretch: normal;
	line-height: 24px;
	letter-spacing: 0px;
	color: #abff9b;}
.main .main-body .main-right .main-right-list{width:331px;height:644px;background:url(/image/home/result.png) center no-repeat;background:cover;padding:19px 0px;}
.main .main-body .main-right .main-right-list .list-title{margin:27px 0px 16px 25px;font-family: FZLTXHK;
	font-size: 20px;
	font-weight: normal;
	font-stretch: normal;
	line-height: 24px;
	letter-spacing: 0px;
	color: #b7efff;
}
.main .main-body .main-right .main-right-list .right-list-item{margin:0 auto;width:314px;height:89px;background:url(/image/home/result-item.png) center no-repeat;background-size:cover;position:relative;}
.main .main-body .main-right .main-right-list .right-list-item div:nth-child(1){font-family: FZLTXHK;
	font-size: 18px;padding:0 0 0 32px;display:inline-block;position: absolute;
  top: 35px;
	font-weight: normal;
	font-stretch: normal;
	line-height: 22px;
	letter-spacing: 0px;
	color: #b7efff;}
.main .main-body .main-right .main-right-list .right-list-item div:nth-child(2){font-family:DS-DIGIT;
	font-size: 45px;position: absolute;
  top: 34px;
  right: 30px;
	font-weight: normal;
	font-stretch: normal;
	line-height: 24px;
	letter-spacing: 0px;
	color: #ff993a;}
.main .main-body .left-anim{margin-top:84px;display:flex;justify-content:center;align-items:center;background: url(/image/home/left-anim.gif) -3px center no-repeat;}
.main .main-body .right-anim{margin-top: 84px;padding: 195px 0px 0px;
    display: flex;
    justify-content: center;
    align-items: center;
    background: url(/image/home/right-anim.gif) 20px 286px  no-repeat;
}


</style>