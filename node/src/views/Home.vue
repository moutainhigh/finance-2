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
          <img src="/image/home/left-animbg.png" class="left-animbg"/>
          <img src="/image/home/left-anim.gif" class="left-anim"/>
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
        <div class="main-center">
          <div class="c-map">
            <img src="/image/home/cd-map.png" width="802" height="594" class="ct-map"/>
            <!-- <div id="bmap"></div> -->
            <img class="map-s" :src="imgItem.src" v-for="(imgItem,index) in map" :key="index" :name='imgItem.name'
                 :style="{top:imgItem.top,left:imgItem.left,opacity:imgItem.opacity,...imgItem.style,position:'absolute'}"/>
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
        </div>
        <div class="main-right">
          <img src="/image/home/right-animbg.png" class="right-animbg"/>
          <img src="/image/home/right-anim.gif" class="right-anim"/>
          <div class="main-right-top">
            <img src="/image/home/right-top.png" class="right-top" />
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
    <img src="/image/home/seld.png" alt="" class="seld">
    <transition name="fade">
      <Login v-show="isLogin" @do-login="doLogin" @to-reg="reg" @close="close"></Login>
    </transition>
    <transition name="fade">
      <Register v-show="isReg" @to-login="login" @close="close"></Register>
    </transition>
  </div>
</template>
<script>
import BMap from 'BMap';
import $ from 'jQuery';
export default {
  data(){
    return {
      isLogin:false,
      isReg:false,
      map:[
        {opacity:0,top:'378px',left:'466px',src:'/image/home/成华区.png',isshow:true,name:'成华区',style:{zIndex:0}},
        // {opacity:0,top:'331px',left:'107px',src:'/image/home/czs.svg',isshow:true,name:'崇州市'style:{zIndex:0}},
        {opacity:0,top:'331px',left:'107px',src:'/image/home/崇州市.png',isshow:true,name:'崇州市',style:{zIndex:0}},
        {opacity:0,top:'355px',left:'59px',src:'/image/home/大邑县.png',isshow:true,name:'大邑县',style:{zIndex:0}},
        // {opacity:0,top:'355px',left:'59px',src:'/image/home/dyx.svg',isshow:true,name:'大邑县',style:{zIndex:0}},
        {opacity:0,top:'146px',left:'222px',src:'/image/home/都江堰.png',isshow:true,name:'都江堰',style:{zIndex:0}},
        {opacity:0,top:'444px',left:'466px',src:'/image/home/高新技术产业开发区.png',isshow:true,name:'高新技术产业开发区',style:{zIndex:0}},
        {opacity:0,top:'418px',left:'512px',src:'/image/home/简阳市.png',isshow:true,name:'简阳市',style:{zIndex:0}},
        {opacity:0,top:'354px',left:'416px',src:'/image/home/金牛区.png',isshow:true,name:'金牛区',style:{zIndex:0}},
        {opacity:0,top:'302px',left:'569px',src:'/image/home/金堂县.png',isshow:true,name:'金堂县',style:{zIndex:0}},
        {opacity:0,top:'407px',left:'463px',src:'/image/home/锦江区.png',isshow:true,name:'锦江区',style:{zIndex:0}},
        {opacity:0,top:'387px',left:'491px',src:'/image/home/龙泉驿区.png',isshow:true,name:'龙泉驿区',style:{zIndex:0}},
        {opacity:0,top:'119px',left:'316px',src:'/image/home/彭州市.png',isshow:true,name:'彭州市',style:{zIndex:0}},
        {opacity:0,top:'299px',left:'334px',src:'/image/home/郫都区.png',isshow:true,name:'郫都区',style:{zIndex:0}},
        {opacity:0,top:'532px',left:'177px',src:'/image/home/浦江县.png',isshow:true,name:'浦江县',style:{zIndex:0}},
        {opacity:0,top:'316px',left:'500px',src:'/image/home/青白江.png',isshow:true,name:'青白江',style:{zIndex:0}},
        {opacity:0,top:'386px',left:'398px',src:'/image/home/青羊区.png',isshow:true,name:'青羊区',style:{zIndex:0}},
        {opacity:0,top:'453px',left:'83px',src:'/image/home/邛崃市.png',isshow:true,name:'邛崃市',style:{zIndex:0}},
        // {opacity:0,top:'476px',left:'106px',style:{width:"268px"},src:'/image/home/chq.svg',isshow:true,name:'邛崃市'},
        {opacity:0,top:'408px',left:'357px',src:'/image/home/天府新区.png',isshow:true,name:'天府新区',style:{zIndex:0}},
        {opacity:0,top:'324px',left:'317px',src:'/image/home/温江区.png',isshow:true,name:'温江区',style:{zIndex:0}},
        {opacity:0,top:'407px',left:'416px',src:'/image/home/武侯区.png',isshow:true,name:'武侯区',style:{zIndex:0}},
        {opacity:0,top:'298px',left:'402px',src:'/image/home/新都区.png',isshow:true,name:'新都区',style:{zIndex:0}},
        {opacity:0,top:'463px',left:'327px',src:'/image/home/新津县.png',isshow:true,name:'新津县',style:{zIndex:0}},
      ]
    }
  },
  mounted(){
    setTimeout(()=>{
      this.initMap();
    },1000)
    
  },
  methods: {
    initMap(){
      // var map = new BMap.Map("bmap");            // 创建Map实例
      // map.centerAndZoom("成都市",15);                
      // map.enableScrollWheelZoom(false);  
      // 获取当前区域图片
      let name="彭州市";
      var img = document.querySelector('img[name="'+ name +'"]')
      let top = Number(img.style.top.replace('px',''));
      let left = Number(img.style.left.replace('px',''));
      let width = img.width;
      let height = img.height; 
      // 绘制svg
      let svg = this.$d3.select('.c-map').append('svg').attr('width',width/2).attr('height',height/2)
              .style('position','absolute').style('z-index',7).style('top',top+(height/4)).style('left',left+(width/4));
              // .style('position','absolute').style('top',top+(width/2)).style('left',left+(height/2))
      // svg.append('image').attr('x',Math.random()).attr('y',Math.random()).attr('width',10).attr('height',10).attr('xlink:href','/image/home/guquan.png')
      var cx = Math.floor(Math.random()*10+1)+20;
      var cy = Math.floor(Math.random()*10+1)+20;
      // 绘制点
      svg.append('circle').attr('cx',cx).attr('cy',cy).attr('r',10).style('fill','rgba(255, 214, 0, 0.2)')
      svg.append('circle').attr('cx',cx).attr('cy',cy).attr('r',5).attr('name',name).attr('top','c').style('fill','rgba(255, 214, 0, 0.8)').style('cursor','pointer');
      var cx = Math.floor(Math.random()*10+10)+20;
      var cy = Math.floor(Math.random()*10+10)+20;
      svg.append('circle').attr('cx',cx).attr('cy',cy).attr('r',10).style('fill','rgba(255, 214, 0, 0.2)')
      svg.append('circle').attr('cx',cx).attr('cy',cy).attr('r',5).attr('top','c').attr('name',name).style('fill','rgba(255, 214, 0, 0.8)').style('cursor','pointer')
      // 给添加的点，添加点击事件监听
      $('circle[top="c"]').click((e)=>{
          console.log(e)
          $('img[name]').css({opacity:0});
          $('img[name="'+e.target.getAttribute('name')+'"]').css({opacity:1});
          $('.seld').css({top:(e.pageY-$('.seld').width()/2)+'px',left:(e.clientX-$('.seld').height()/2)+'px',zIndex:6})
          $('.c-info').css({opacity:1,top:'366px',right:'500px',transform:'scale(0)'})
          .animate({opacity:1,top:'117px',right:'154px'},'show','linear').css({transform:'scale(1)'})
      })
      // 给选中添加监听再次点击消失，然后可以点击其他的机构
      $('.seld').click(()=>{
        $('.seld').css({zIndex:-1})
      })
     
    },
    login(){
      this.isLogin = true;
      this.isReg = false;
    },
    doLogin(form){
      // /finance/userInfo/login
      // 验证手机或者信用代码
      if(!form.mobile){
        this.$message.error('请输入正确的手机号或统一社会信用代码');
        return ;
      }
      if(!form.password){
        this.$message.error('请输入密码');
        return ;
      }

      this.$message.loading('登录中',0)
      this.$http.post('/finance/userInfo/login',form).then(res=>{
          this.$message.destroy();
          // 判断服务器端数据是否有误
          if(res.data.code!=0){
            this.$message.error(res.data.data.msg);
            return ;
          }
          // 登录成功存储用户信息
          localStorage.setItem('cdjr_token',res.data.content.token);
          localStorage.setItem('userInfo',JSON.stringify(res.data.content.userInfo));
          this.$message.loading('登录成功',1).then(()=>{
            // 处理完后关闭登录窗口
            this.isLogin = false;
          });
      });
    },
    reg(){
      this.isReg = true;
      this.isLogin = false;
    },
    doReg(){
      // /finance/userInfo/register
    },
    close(){
      this.isLogin = false;
      this.isReg = false;
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
.home-box{background:url(/image/home/bg.png);}
/* .home-box{min-width:100vw;min-height:100vh;background:url(/image/home/bg.png);} */
.home-box .header{height:80px;padding:17px 63px 7px;display:flex;justify-content:space-between;}
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


.main{height:calc(100vh - 80px);min-height:1000px;position:relative;padding-top:93px;}
.main .main-bg{position:absolute;top:0px;left:0px;width:100%;height:100%;z-index:0;}
.main .main-body{display:flex;justify-content:space-between;position:relative;z-index:6;margin:0px 65px 0 60px;}
.main .main-body .main-left{/*margin-top:84px;*/width:317px;height:780px;position: relative;}
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
.main .main-body .main-center{flex:1;position: relative;background: url(/image/home/map.png) center no-repeat;background-size: cover;}
.main .main-body .main-center .c-map{width:880px;height:794px;margin:0 auto;display:flex;justify-content:center;align-items:center;position: relative;}
.main .main-body .main-center .c-map .ct-map{margin:auto;}
.main .main-body .main-center .c-info{width:179px;height:189px;background:url(/image/home/info.png) center no-repeat;position:absolute;top:117px;right:154px;}
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
.main .main-body .main-center .c-bottom{position:absolute;bottom:84px;left:167px;}
.main .main-body .main-center .c-bottom img{width:60px;height:79px;}


.main .main-body .main-right{/*margin-top:84px;*/width:331px;height:789px;position: relative;margin-right: 26px;}
/* .main .main-body .main-right .main-right-top{display:flex;justify-content:center;align-items:center;width:155px;height:155px;background:url(/image/home/right-top.png) center no-repeat;background-size:cover;margin:0px auto;} */
.main .main-body .main-right .main-right-top{cursor:pointer;position:relative;display:flex;justify-content:center;align-items:center;width:155px;height:155px;margin:0px auto;}
.main .main-body .main-right .main-right-top .right-top{width:100%;height:100%;position:absolute;top:0px;left:0px;animation:topanimate 5s infinite;animation-timing-function:linear;}
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

.main .main-body .main-left .left-animbg,.main .main-body .main-left .left-anim{position: absolute;top: 50%;right: -126px;transform: translateY(-50%);}
.main .main-body .main-left .left-anim{right: -104px;top: 392px;}
.main .main-body .main-right .right-animbg,.main .main-body .main-right .right-anim{position: absolute;top: 258px;left: -116px;}
.main .main-body .main-right .right-anim{left: -95px;top: 258px;}
/* .map-s{transition:all 1s;cursor:pointer;}
.map-s:hover{opacity:1 !important;}
rect{transition:all 1s;cursor:pointer;opacity:0;stroke:red;stroke-width:2;fill:rgba(255, 214, 0, 0.3);}
rect:hover{opacity:1;} */

#bmap{width:100%;height:100%;position:absolute;top:0px;left:0px;}
.seld{position:absolute;top:0px;z-index:-1;}
</style>
<style>
@keyframes topanimate {
  from {
    transform:rotate(0deg);
    opacity:0.8;
  }
  to {
    transform:rotate(360deg);
    opacity:1;
  }
}
</style>