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
        <div class="btn" @click="login" v-show="!userInfo.userName">登录</div>
        <div class="btn" @click="reg" v-show="!userInfo.userName">注册</div>
        <UserInfo :userInfo="userInfo" :isHome='true' v-show="userInfo.userName"></UserInfo>
      </div>
    </div>
    <div class="main">
      <img src="/image/home/main.png" class="main-bg" alt="">
      <div class="main-body">
        <div class="left">
          <div class="tab">
            <div class="tab-item" :class="tabAction==0?'tab-active':''" @click="tabChange(0)">股权机构</div>
            <div class="tab-item" :class="tabAction==1?'tab-active':''" @click="tabChange(1)">债券机构</div>
          </div>
          <div class="left-list" v-show="tabAction==0">
              <div class="list-item" v-for="(item,index) in gqjg" :key="index"
                  @click="selComp(index,item)" 
                  :class="index==gqIndex?'item-active':''">
                {{item.institutionName}}
              </div>
          </div>
          <div class="left-list" v-show="tabAction==1">
              <div class="list-item" v-for="(item,index) in zjjg" :key="index"
                  @click="selComp(index,item)" 
                  :class="index==gqIndex?'item-active':''">
                {{item.institutionName}}
              </div>
          </div>
          <div class="left-anima">
            <img src="/image/home/left-animbg.png" alt="" class="anima-bg">
            <img src="/image/home/left-anim.gif" alt="" class="anima">
          </div>
        </div>
        <div class="center">
          <img src="/image/home/map.png" alt="" class="center-bg">
          <div class="center-map">
            <!-- <img src="/image/home/cd-map.png" alt="" class="cd-map">
            <img class="map-s" :src="imgItem.src" v-for="(imgItem,index) in map" :key="index" :name='imgItem.name'
                 :style="{top:imgItem.top,left:imgItem.left,opacity:imgItem.opacity,...imgItem.style,position:'absolute'}"/>
            <svg class="bg-svg" id="selsvg"></svg> -->
            <svg width="42vw" height="55vh" style="margin:4vw auto;" viewBox="0 0 802 594" version="1.1" preserveAspectRatio="xMinYMin meet" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
              <image x="0" y='0' xlink:href="/image/home/cd-map.png"></image>
              <image v-bind:xlink:href="imgItem.src" v-for="(imgItem,index) in cdMap" :key="index" 
                  :name='imgItem.name'
                  :style="{x:imgItem.top,y:imgItem.left,opacity:imgItem.opacity,...imgItem.style}"></image>
            </svg>
            <svg class="bg-svg" id="selsvg" width="42vw" height="55vh" style="z-index:6;" viewBox="0 0 802 594" version="1.1" preserveAspectRatio="xMinYMin meet"></svg>
            <svg class="bg-svg" id="circles" width="42vw" height="55vh" style="z-index:7;" viewBox="0 0 802 594" version="1.1" preserveAspectRatio="xMinYMin meet" @click="selCirle"></svg>
          </div>
          <div class="c-info">
            <div class="c-title">{{currSelItem.institutionName}}</div>
            <ul>
              <li>物联网投资基金</li>
              <li>中小企业专项投资</li>
              <li>创新医药投资基金</li>
            </ul>
          </div>
          <div class="znz">
            <img src="/image/home/znz.png" alt="">
          </div>
        </div>
        <div class="right">
            <div class="right-top">
              <div class="top-img"><img src="/image/home/right-top.png" alt=""></div>
              一键匹配
            </div>
            <div class="right-list">
                <img src="/image/home/result.png" alt="" class="result-bg">
                <div class="list-title">当前结果</div>
                <div class="list-item" v-for="item in result" :key="item.title">
                    <div>{{item.title}}</div>
                    <div>{{item.value}}</div>
                </div>
            </div>
            <div class="right-anima">
              <img src="/image/home/right-animbg.png" alt="" class="anima-bg">
              <img src="/image/home/right-anim.gif" alt="" class="anima">
            </div>
        </div>
      </div>
    </div>
    
    <!-- <img src="/image/home/seld.png" alt="" class="seld"> -->
    <Login v-show="isLogin" @do-login="doLogin" @to-reg="reg" @close="close"></Login>
    <Register v-show="isReg" @to-login="login" @close="close"></Register>
  </div>
</template>
<script>
import BMap from 'BMap';
import $ from 'jQuery';
import {filterCompany, drawCompany} from '@/common/lib/tools.js'
export default {
  data(){
    return {
      tabAction:0,
      gqIndex:-1,
      isLogin:false,
      isReg:false,
      userInfo:{userName:''},
      cdMap:[
        {opacity:0,top:'428',left:'278',src:'/image/home/成华区.png',isshow:true,name:'成华区',style:{zIndex:0}},
        {opacity:0,top:'68',left:'231',src:'/image/home/崇州市.png',isshow:true,name:'崇州市',style:{zIndex:0}},
        {opacity:0,top:'19.7',left:'255',src:'/image/home/大邑县.png',isshow:true,name:'大邑县',style:{zIndex:0}},
        {opacity:0,top:'183',left:'45.6',src:'/image/home/都江堰.png',isshow:true,name:'都江堰',style:{zIndex:0}},
        {opacity:0,top:'428',left:'345',src:'/image/home/高新技术产业开发区.png',isshow:true,name:'高新技术产业开发区',style:{zIndex:0}},
        {opacity:0,top:'472',left:'318',src:'/image/home/简阳市.png',isshow:true,name:'简阳市',style:{zIndex:0}},
        {opacity:0,top:'378',left:'254',src:'/image/home/金牛区.png',isshow:true,name:'金牛区',style:{zIndex:0}},
        {opacity:0,top:'528',left:'203',src:'/image/home/金堂县.png',isshow:true,name:'金堂县',style:{zIndex:0}},
        {opacity:0,top:'425',left:'308',src:'/image/home/锦江区.png',isshow:true,name:'锦江区',style:{zIndex:0}},
        {opacity:0,top:'453',left:'287',src:'/image/home/龙泉驿区.png',isshow:true,name:'龙泉驿区',style:{zIndex:0}},
        {opacity:0,top:'276',left:'19',src:'/image/home/彭州市.png',isshow:true,name:'彭州市',style:{zIndex:0}},
        {opacity:0,top:'295',left:'198',src:'/image/home/郫都区.png',isshow:true,name:'郫都区',style:{zIndex:0}},
        {opacity:0,top:'138',left:'432',src:'/image/home/浦江县.png',isshow:true,name:'浦江县',style:{zIndex:0}},
        {opacity:0,top:'460',left:'216.5',src:'/image/home/青白江.png',isshow:true,name:'青白江',style:{zIndex:0}},
        {opacity:0,top:'360',left:'287',src:'/image/home/青羊区.png',isshow:true,name:'青羊区',style:{zIndex:0}},
        {opacity:0,top:'43',left:'353',src:'/image/home/邛崃市.png',isshow:true,name:'邛崃市',style:{zIndex:0}},
        {opacity:0,top:'317',left:'308',src:'/image/home/天府新区.png',isshow:true,name:'天府新区',style:{zIndex:0}},
        {opacity:0,top:'278',left:'223',src:'/image/home/温江区.png',isshow:true,name:'温江区',style:{zIndex:0}},
        {opacity:0,top:'378',left:'308',src:'/image/home/武侯区.png',isshow:true,name:'武侯区',style:{zIndex:0}},
        {opacity:0,top:'363',left:'197',src:'/image/home/新都区.png',isshow:true,name:'新都区',style:{zIndex:0}},
        {opacity:0,top:'288',left:'362.5',src:'/image/home/新津县.png',isshow:true,name:'新津县',style:{zIndex:0}},
      ],
      result:[
        {title:'企业入驻（家）',type:'companyCount',value:0},
        {title:'机构入驻（家）',type:'institutionCount',value:0},
        {title:'股权产品（个）',type:'stockCount',value:0},
        {title:'债劵产品（个）',type:'bondCount',value:0},
        {title:'匹配（笔）',type:'matchCount',value:0},
        {title:'匹配金额（亿元）',type:'matchMoney',value:0},
      ],
      gqjg:[
        {institutionName:'成都技转创投'},
        {institutionName:'红杉资本'},
        {institutionName:'深证创新投'},
        {institutionName:'达晨创投'},
        {institutionName:'君联资本'},
        {institutionName:'IDG资本'},
        {institutionName:'北极光创投'},
        {institutionName:'晨兴资本'},
        {institutionName:'今日资本'},
        {institutionName:'红杉中国'},
      ],
      zjjg:[],
      companyList:[],
      currSelItem:{}
    }
  },
  mounted(){
    if(localStorage.getItem('userInfo')){
      this.userInfo =JSON.parse(localStorage.getItem('userInfo'));
      this.$store.commit('setUserInfo',this.userInfo);
    }
    setTimeout(()=>{
      this.initMap();
    },1000)
    // 获取统计结果
    this.getResult();
    // 获取推荐机构
    this.getCompany();

    
  },
  methods: {
    initMap(){ 

      this.cdMap.forEach(item=>{
        let companyList = this.companyList.filter(sitem=>item.name==sitem.regionName);
        drawCompany(this.$d3,item.name,companyList);
      });
     
    },
    login(){
      this.isLogin = true;
      this.isReg = false;
    },
    doLogin(params){
        console.log(params)
        if(params && params.mobile){
            this.userInfo = this.$store.state.userInfo;
            this.isLogin = false;
        }
    },
    reg(){
      this.isReg = true;
      this.isLogin = false;
    },
    doReg(params){
      console.log(params)
      if(params && params.mobile){
        console.log(params)
        this.isReg = false;
      }
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
    getResult(){
      this.$http.post('/finance/institution/getFinanceStatistics').then(res=>{
        if(res.data.code==0){
          this.result.forEach((item,index)=>{
            this.$set(this.result[index],'value',res.data.content[item.type])
          })
        }
      })
    },
    tabChange(index){
      this.tabAction = index;
    },
    selComp(index,item){
      if(index==this.gqIndex){
        return ;
      }
      this.gqIndex = index;
      this.currSelItem = item;
      $('circle[comp="'+item.institutionName+'"]').click();
    },
    getCompany(){
      // /finance/institution/getInstitutionAndRegion
      this.$http.post('/finance/institution/getInstitutionAndRegion',{}).then(res=>{
        if(res.data.code==0){
          this.companyList = res.data.content;
          let company = filterCompany(this.companyList);
          if(company){
            this.gqjg = company.gqjg;
            this.currSelItem = this.gqjg[0];
            this.gqIndex = 1;
            this.zjjg = company.zjjg;
          }
        }
      })
    },
    selCirle(e){
      let id = e.target.getAttribute('did');
      if(id){
        let currItem = this.companyList.filter(item=>item.id == id);
        if(currItem.length==1){
          this.currSelItem = currItem[0];
        }
      }
    }

    
  },
  components:{
    Login:()=>import("@/components/Login.vue"),
    Register:()=>import("@/components/Register.vue"),
    UserInfo:()=>import("@/components/UserInfo.vue"),
  }
}
</script>
<style lang="scss" scoped>
*{padding:0px;margin:0px;}
.home-box{
  width:100vw;height: 100vh;background:url(/image/home/bg.png) no-repeat;background-size:cover;
  .header{
    width:100vw;
    height:7vh;
    padding:1vh 3vw;
    display:flex;
    justify-content:space-between;
    position:relative;
    z-index:2;
    .left{
      display:flex;
      justify-content:flex-start;
      .logo{
        img{
          width:25vw;
        }
      }
    }
    .nav{
      display:flex;
      justify-content:flex-start;
      margin-left:4vw;
      .nav-item{
        width:7vw;
        height:3vw;
        margin-left:1vw;
        text-align:center;
        line-height:3vw;
        background:url(/image/home/btn.png) center no-repeat;
        background-size:cover;
        font-family: FZLTXHK;
        font-size: 1vw;
        font-weight: normal;
        font-stretch: normal;
        letter-spacing: 0vw;
        color: #9ee9ff;
        cursor:pointer;
        &:first-child{
          margin-left:0vw;
        }
        &:hover{
          color: #abff9b;
          background-image:url(/image/home/btn-act.png)
        }
      }
    }
    .right{
      display:flex;
      justify-content:flex-start;
      .btn{
        width:7vw;
        height:3vw;
        margin-left:1vw;
        text-align:center;
        line-height:3vw;
        background:url(/image/home/btn.png) center no-repeat;
        background-size:cover;
        font-family: FZLTXHK;
        font-size: 1vw;
        font-weight: normal;
        font-stretch: normal;
        letter-spacing: 0vw;
        color: #9ee9ff;
        cursor:pointer;
        &:first-child{
          margin-left:0vw;
        }
        &:hover{
          color: #abff9b;
          background-image:url(/image/home/btn-act.png)
        }
      }
    }
  }
  .main{
    width:100vw;
    height:93vh;
    position: relative;
    .main-bg{
      width:100vw;
      height:93vh;
      position:absolute;
      top:0vw;
      left:0vh;
      z-index:0;
    }
    .main-body{
      padding:8vh 4vw 11vh;
      display:flex;
      justify-content:space-between;
      position:relative;
      z-index:1;
      .left{
        width:17vw;
        height:73vh;
        position:relative;
        // background:green;
        .tab{
          width:13vw;
          height:3vh;
          background:url(/image/home/tab.png) center no-repeat;
          background-size:cover;
          margin:0 auto;
          display:flex;
          justify-content:center;
          .tab-item{
            position:relative;
            font-family: FZLTXHK;
            font-size: 1vw;
            font-weight: normal;
            font-stretch: normal;
            letter-spacing: 0vh;
            color: #9ee9ff;
            margin-left:1vw;
            text-align:center;
            cursor:pointer;
            &:first-child{margin-left:0vw;}
            &:hover{
              color:#ffd600;
            }
            &:hover::after{
              content:'';
              width:4vw;
              height:0.2vh;
              background:#ffd600;
              position:absolute;
              bottom:0vh;
              left:50%;
              transform: translateX(-50%);
            }
          }
          .tab-active{
            color:#ffd600;
            &::after{
              content:'';
              width:4vw;
              height:0.2vh;
              background:#ffd600;
              position:absolute;
              bottom:0vh;
              left:50%;
              transform: translateX(-50%);
            }
          }
        }
        .left-list{
          width:17vw;
          height:70vh;
          padding-top:3vh;
          // background:#fff;
          .list-item{
            width:13vw;
            height:5vh;
            margin:0vw 2vw 1.8vh;
            position:relative;
            background:url(/image/home/left.png) center no-repeat;
            background-size:cover;
            font-family: PingFangSC-Medium;
            font-size: 1vw;
            font-weight: normal;
            font-stretch: normal;
            letter-spacing: 0vh;
            color:#c1edfd;
            text-align:center;
            line-height:5vh;
            cursor:pointer;
            &:hover::before{
              content:'';
              width:17vw;
              height:6vh;
              background:url(/image/home/left-act.png) center no-repeat;
              background-size:cover;
              position:absolute;
              top: 50%;
              left: 50%;
              transform: translate(-50%,-50%);
            }
            &:hover::after{
              content:'';
              width:2vw;
              height:2vh;
              background:url(/image/home/left-p.png) center no-repeat;
              background-size:cover;
              position:absolute;
              top: 50%;
              right: -2.5vw;
              transform: translateY(-50%);
            }
          }
          .item-active{
            &::before{
              content:'';
              width:17vw;
              height:6vh;
              background:url(/image/home/left-act.png) center no-repeat;
              background-size:cover;
              position:absolute;
              top: 50%;
              left: 50%;
              transform: translate(-50%,-50%);
            }
            &::after{
              content:'';
              width:2vw;
              height:2vh;
              background:url(/image/home/left-p.png) center no-repeat;
              background-size:cover;
              position:absolute;
              top: 50%;
              right: -2.5vw;
              transform: translateY(-50%);
            }
          }
        }
        .left-anima{
          width: 10vw;
          height: 60vh;
          position: absolute;
          top: 52%;
          left: 89%;
          transform: translateY(-50%);
          .anima-bg{
            width: 10vw;
            height: 60vh;
          }
          .anima{
            position:absolute;
            top: 50%;
            left: -13%;
            transform: translateY(-50%);
            width:10vw;
            height:60vh;
          }
        }
      }
      .center{
        width:50vw;
        height:73vh;
        // background:blue;
        position:relative;
        .center-bg{
          width: 60vw;
          height: 96vh;
          position: absolute;
          z-index:-1;
          top: 50%;
          left: 50%;
          transform: translate(-50%,-52%);
        }
        .center-map{
          width:42vw;
          height:55vh;
          margin:2vw auto;
          position:relative;
          z-index:1;
          .cd-map{
            width:42vw;
            height:55vh;
          }
          .bg-svg{
            width:42vw;
            height:55vh;
            margin:4vw auto;
            position: absolute;
            // background:#ff993a;
            top: 0vw;
            left: 0vh;
          }
        }
        .c-info{
          width:9vw;
          height:10vw;
          position: absolute;
          top:4vw;
          right:3.2vw;
          background:url(/image/home/info.png) center no-repeat;
          background-size:cover;
          .c-title{
            margin:1.5vw 1vw;
            font-family: PingFangSC-Medium;
            font-size: 1vw;
            font-weight: normal;
            font-stretch: normal;
            letter-spacing: 0vw;
            color:#ffd600;
            position:relative;
            text-overflow: ellipsis;
            white-space: nowrap;
            overflow: hidden;
            &::after{
              content:'';
              width: 7vw;
              height:0.1vw;
              background:#7edcff;
              position:absolute;
              bottom:-0.5vw;
              left:50%;
              transform:translateX(-50%);
            }
          }
          ul{
            margin:0vw 0vw 0 1.8vw;
            li{
              font-family: FZLTXHK;
              font-size: 0.73vw;
              font-weight: normal;
              font-stretch: normal;
              letter-spacing: 0vw;
              color: #ffd600;
              text-overflow: ellipsis;
              white-space: nowrap;
              overflow: hidden;
            }
          }
        }
        .znz{
          width: 3.13vw;
	        height: 4.11vw;
          position:absolute;
          bottom:2.86vw;
          left:4.61vw;
          img{
            width: 3.13vw;
	          height: 4.11vw;
          }
        }
      }
      .right{
        width:17vw;
        height:73vh;
        // background:gold;
        position: relative;
        .right-top{
          width:8vw;
          height:8vw;
          margin:0vw auto;
          font-family: FZLTCHJW--GB1-0;
          font-size: 1vw;
          font-weight: normal;
          font-stretch: normal;
          line-height: 8vw;
          letter-spacing: 0vh;
          color: #abff9b;
          text-align:center;
          position:relative;
          cursor: pointer;
          .top-img{
            width:8vw;
            height:8vw;
            position: absolute;
            top:50%;
            left:50%;
            transform:translate(-50%,-50%);
            img{
              width:8vw;
              height:8vw;
              animation:topanimate 5s infinite;animation-timing-function:linear;
            }
          }
        }
        .right-list{
          width:17vw;
          height:56vh;
          position:relative;
          .result-bg{
            width:18vw;
            height:60vh;
            position:absolute;
            top:0vw;
            left:-1vh;
          }
          .list-title{
            padding:3vh 0vw 1vh 1vw;
            font-family: FZLTXHK;
            font-size: 1vw;
            font-weight: normal;
            font-stretch: normal;
            letter-spacing: 0vw;
            color: #9ee9ff;
          }
          .list-item{
            width:16vw;
            height:8vh;
            margin:0vw auto;
            background:url(/image/home/result-item.png) center no-repeat;
            background-size:cover;
            position:relative;
            div:nth-child(1){
              height:8vh;
              line-height:8vh;
              margin-left:1.5vw;
              font-family: FZLTXHK;
              font-size: 0.9vw;
              font-weight: normal;
              font-stretch: normal;
              letter-spacing: 0vw;
              color: #9ee9ff;
            }
            div:nth-child(2){
              position:absolute;
              top:0vw;
              right:2.5vh;
              width:6vw;
              height:8vh;
              line-height:8vh;
              margin-left:1vw;
              font-family: DS-DIGIT;
              font-size: 2.3vw;
              font-weight: normal;
              font-stretch: normal;
              letter-spacing: 0vw;
              color: #ff993a;
              opacity: 1;
              text-align:right;
            }
          }
        }
        .right-anima{
          width: 7vw;
          height: 50vh;
          position: absolute;
          top: 32%;
          left: -42%;
          // transform: translateY(-50%);
          .anima-bg{
            width: 7vw;
            height: 50vh;
          }
          .anima{
            position:absolute;
            top: 50%;
            left: 15%;
            transform: translateY(-50%);
            width:7vw;
            height:50vh;
          }
        }
      }
    }
  }
}
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