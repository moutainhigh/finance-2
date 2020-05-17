<template>
  <div class="left">
      <div class="left-list">
          <div class="list-title">
            <svg width="22" height="26" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
              <defs>
                <linearGradient id="right" x1="0%" y1="0%" x2="100%" y2="0%">
                  <stop offset="0%" style="stop-color:#091429; stop-opacity:1"/>
                  <stop offset="50%" style="stop-color:#c1edfd; stop-opacity:1"/>
                  <stop offset="100%" style="stop-color:#c1edfd; stop-opacity:1"/>
                </linearGradient>
                <linearGradient id="right1" x1="0%" y1="0%" x2="100%" y2="0%">
                  <stop offset="0%" style="stop-color:#091429; stop-opacity:1"/>
                  <stop offset="25%" style="stop-color:#c1edfd; stop-opacity:1"/>
                  <stop offset="50%" style="stop-color:#c1edfd; stop-opacity:1"/>
                  <stop offset="100%" style="stop-color:#c1edfd; stop-opacity:1"/>
                </linearGradient>
                <linearGradient id="left1" x1="100%" y1="0%" x2="0%" y2="0%">
                  <stop offset="0%" style="stop-color:#091429; stop-opacity:1"/>
                  <stop offset="25%" style="stop-color:#c1edfd; stop-opacity:1"/>
                  <stop offset="50%" style="stop-color:#c1edfd; stop-opacity:1"/>
                  <stop offset="100%" style="stop-color:#c1edfd; stop-opacity:1"/>
                </linearGradient>
                <linearGradient id="left" x1="100%" y1="0%" x2="0%" y2="0%">
                  <stop offset="0%" style="stop-color:#091429; stop-opacity:1"/>
                  <stop offset="50%" style="stop-color:#c1edfd; stop-opacity:1"/>
                  <stop offset="100%" style="stop-color:#c1edfd; stop-opacity:1"/>
                </linearGradient>

                <radialGradient id="insetshow" fx="50%" fy="50%" r="100%" spreadMethod="pad">
                  <stop offset="0%"   stop-color="transparent" stop-opacity="1"/>
                  <stop offset="15%"   stop-color="transparent" stop-opacity="1"/>
                  <stop offset="100%" stop-color="#9ee9ff" stop-opacity="1" />
                </radialGradient>
              </defs>
              <g style="opacity:0.8;">
                <path d="M11 0L0 13L11 26" fill="none" style="stroke: url(#left);opacity:0.5" stroke-width="2" />
                <path d="M22 0L11 13L22 26" fill="none" style="stroke: url(#left1);" stroke-width="2" />
              </g>
            </svg>
            <div class="title-item" @click="tab(1)" :class="tabActive==1?'active':''">股票机构</div>
            <div class="title-item" @click="tab(2)" :class="tabActive==2?'active':''">债券机构</div>
            <svg width="22" height="26" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
              <g style="opacity:0.8;">
                <path d="M0 0L11 13L0 26" fill="none" style="stroke: url(#right1);" stroke-width="2" />
                <path d="M11 0L22 13L11 26" fill="none" stroke-width="2" style="stroke: url(#right);opacity:0.5;" />
              </g>
            </svg>
          </div>
          <div class="lists">
            <!-- v-for="(item,index) in list" :key="index" @mouseover.native="mouseOver(index)" -->
            <ListItem v-for="(item,index) in list" :key="index" 
                @click="selItem(index)"
                @mouseleave.native="mouseLeve()"
                @mouseenter.native="mouseEntry(index)" :item="item" :index="index" :currIndex="overIndex"></ListItem>
            <svg width="317" height="58" class="active-item-box" :style="moveStyle" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
              <path d="M13 15L7 29L13 43" fill="none" stroke="#9ee9ff" stroke-width="2" />
              <path d="M35 0L30 0L14 29L30 58L35 58" fill="none" stroke="#9ee9ff" stroke-width="2" />
              <path d="M304 15L310 29L304 43" fill="none" stroke="#9ee9ff" stroke-width="2" />
              <path d="M282 0L287 0L303 29L287 58L282 58" fill="none" stroke="#9ee9ff" stroke-width="2" />
            </svg>
          </div>
      </div>
  </div>
</template>
<script>
export default {
    name:'HomeLeft',
    data(){
        return {
          tabActive:1,
          overIndex:0,
          currIndex:0,
          leave:null,
          list:[
            {title:'成都技转创投'},
            {title:'红杉资本'},
            {title:'深证创新投'},
            {title:'达晨创投'},
            {title:'君联资本'},
            {title:'ＩDG资本'},
            {title:'北极光创投'},
            {title:'晨兴资本'},
            {title:'今日资本'},
            {title:'红杉中国'},
          ],
          itemActiveStyle:{
            color:'#ffd600',
            fill:'#ffd600',
            textShadow:'1px 1px 3px #ffd600,-1px -1px 3px #ffd600'
          },
          moveStyle:{
            top:'-5px',
            transition:'all 0.5s',
            animateDuration: '2s',
            animateDelay: '1s'
          }
        }
    },
    methods:{
      tab(active){
        this.tabActive = active;
      },
      mouseEntry(index){
        this.overIndex = index
        clearTimeout(this.leave);
        if(index==0){
          this.moveStyle.top="-5px"
        }else{
          this.moveStyle.top=(index*66+3)+"px"
        }
      },
      mouseLeve(){
        this.leave = setTimeout(()=>{
          this.overIndex = this.currIndex
          this.moveStyle.top="-5px"
        },100)
      }
    },
    components:{
      ListItem:()=>import('@/components/ListItem.vue')
    }
}
</script>
<style scoped>
.left .left-list .list-title{display:flex;align-items:center;}
.left .left-list .list-title .title-item{
  font-family: FZLTXHK;font-size: 20px;font-weight: normal;font-stretch: normal;line-height: 24px;letter-spacing: 0px;
  color: #9ee9ff;text-shadow: 1px 1px 3px #9ee9ff,-1px -1px 3px #9ee9ff;
  padding:0px 17px;
  cursor: pointer;
}
.left .left-list .list-title .title-item:nth-child(3){padding-left:0px;}
.left .left-list .list-title .active{
    font-family: FZLTXHK;
    font-size: 20px;
    font-weight: normal;
    font-stretch: normal;
    line-height: 24px;
    letter-spacing: 0px;
    color: #ffd600;
    text-shadow: 1px 1px 3px #ffd600,-1px -1px 3px #ffd600;
}
.left .left-list .lists{position:relative;}
.left .left-list .lists .list-item{margin-bottom:12px;cursor:pointer;position:relative;z-index:1;}
.left .left-list .lists .list-item:nth-child(1){margin-top:31px;}
.item-active{background:red;}
.active-item-box{position:absolute;top:-5px;left:-35px;z-index:0;}
</style>