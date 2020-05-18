<template>
  <div class="home">
    <div class="header" style="position:sticky;z-index:1;">
      <div class="header-title">
        <span class="logo">CTG</span> 成都市科技金融智能服务平台
        <div class="nav">
          <div class="nav-item active"  @click="toPage('/')">首页</div>
          <div class="nav-item" @click="toPage('/guquan')">股权融资</div>
          <div class="nav-item"  @click="toPage('/zhaiquan')">债权融资</div>
        </div>
      </div>
      <div class="right">
        <div class="btn">登录</div>
        <div class="btn">注册</div>
      </div>
    </div>
    <div class="main">
      <HomeLeft class="main-left"></HomeLeft>
      <HomeCenter></HomeCenter>
      <HomeRight></HomeRight>
    </div>
    
  </div>
</template>

<script>
export default {
  name: 'Home',
  data(){
    return {
      width:document.documentElement.clientWidth,
      height:document.documentElement.clientHeight,
      svgObj:{}
    }
  },
  methods:{
    initPage(){
      this.svgObj = this.$d3.select('svg')
    },
    toPage(url,params){
      if(params){
        this.$router.push({path:url,params:params});
      }else{
        this.$router.push({path:url}).catch((err)=>{});
      }
    },
    addCircle(){
      // var data = this.$d3.range(100).map(() => { 
      //               return {
      //                 cx: Math.floor(Math.random() * 1000)+400,
      //                 cy: Math.floor(Math.random() * 1000),
      //                 r: Math.floor(Math.random() * 5)
      //               };
      //             });
      //   console.log(data)
      //   data.forEach(item => {
      //     setTimeout(()=>{
      //       this.$d3.select('svg').append('circle').attr('cx',item.cx).attr('cy',item.cy).attr('r',item.r).style('fill','#9ee9ff').style('opacity',1-Math.random())
      //       .style('animation','opacityAn 2s')
      //       .style('animation-timing-function','linear')
      //       .style('animation-iteration-count','infinite')
      //     },1000)
      //   });
        // for(let i=0;i<num;i++){
        //   // this.$svgObg.append('circle').attr(x:this.$d3.random)
        // }
    }
  },
  mounted(){
    this.initPage();
    this.addCircle();
  },
  components: {
    HomeLeft:()=> import("@/components/HomeLeft.vue"),
    HomeRight:()=> import("@/components/HomeRight.vue"),
    HomeCenter:()=> import("@/components/HomeCenter.vue"),
  }
}
</script>
<style scoped>
  .home{
    background-color:#091429;
    width:100vw;
    height:100vh;
  }
  .header{max-width:1920px;display:flex;justify-content:space-between;padding-left:63px;padding-top:18px;align-items: center;padding-right:63px;}
  .header .logo{font-style: normal;font-size: 28px;margin-right:14px;}
  .header-title{
    font-family: BDZYJT--GB1-0;
    font-size: 28px;
    font-weight: normal;
    font-style: italic;
    font-stretch: normal;
    line-height: 24px;
    letter-spacing: 1px;
    color: #9ee9ff;
    text-shadow:3px 2px 8px rgba(0, 90, 255, 1),-3px -2px 8px rgba(0, 90, 255, 1);
    display:flex;
    align-items: center;
  }
  .header .nav{margin-left:75px;display:flex;justify-content: space-between;}
  .header .nav .nav-item{
    font-family: FZLTXHK;
    font-size: 20px;
    font-weight: normal;
    font-stretch: normal;
    line-height: 24px;
    letter-spacing: 0px;
    color: #9ee9ff;
    text-align:center;
    font-style: normal;
    background: url(/image/button.svg) no-repeat;
    background-size:cover;
    padding:15px 25px;
    width: 133px;
    height: 51px;
    margin-left:14px;
    cursor: pointer;
  }
  .header .nav .active{
    background: url(/image/button-active.svg) no-repeat;
    color:#abff9b;
    text-shadow:2px 2px 8px #abff9b,-2px -1px 8px #abff9b;
  }
  .header .nav .nav-item:hover{
    background: url(/image/button-active.svg) no-repeat;
    color:#abff9b;
    text-shadow:2px 2px 8px #abff9b,-2px -1px 8px #abff9b;
  }
  .right{display:flex;}
  .right .btn{
    font-family: FZLTXHK;
    font-size: 20px;
    font-weight: normal;
    font-stretch: normal;
    line-height: 24px;
    letter-spacing: 0px;
    color: #9ee9ff;
    text-align:center;
    font-style: normal;
    background: url(/image/button.svg) no-repeat;
    background-size:cover;
    padding:15px 25px;
    width: 133px;
    height: 51px;
    margin-left:14px;
    cursor: pointer;
  }
  .right .btn:hover{
    background: url(/image/button-active.svg) no-repeat;
    color:#abff9b;
    text-shadow:2px 2px 8px #abff9b,-2px -1px 8px #abff9b;
  }
  .main{padding:18px 72px;display:flex;position:relative;z-index:1;margin-top:93px;}
  .main .main-left{margin-left:50px;}
</style>
<style>
   @keyframes path-animation {
    from {
    stroke-dashoffset: 100%; 
    }
    to {
    stroke-dashoffset: 0%; 
    } 
  }
   @keyframes opacityAn {
    from {
      opacity:0;
      r:0;
      stroke:#00af4c;
      fill:#00af4c;
    }
    to {
      opacity:0.5;
      r:20;
      stroke:#091429;
      fill:#091429;
    }
  }
</style>
