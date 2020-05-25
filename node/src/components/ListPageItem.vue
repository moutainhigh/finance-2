<template>
  <div class="page-item-box" @click="$emit('to-detail',item)">
      <img src="/image/c6.png" alt="" style="width:100px;height:100px;margin-right:32px;">
      <div>
          <div class="title">{{item.title}}</div>
          <div class="desc">
              <div class="desc-item">
                  <img src="/image/icon-suoshujigou.png" alt="" style="width:16px;height:16px;">
                  <div><span>所属机构：</span> {{item.mechanism}}</div>
              </div>
              <div class="desc-item">
                  <img src="/image/icon-hangyefangxiang.png" alt="" style="width:16px;height:16px;">
                  <div><span>行业方向：</span> {{item.industryDirectStr}}</div>
              </div>
              <div class="desc-item">
                  <img src="/image/icon-rongziedu.png" alt="" style="width:16px;height:16px;">
                  <div><span>融资额度：</span> {{item.financeQuotaStr}}</div>
              </div>
              <div class="desc-item">
                  <img src="/image/icon-rognzijieduan.png" alt="" style="width:16px;height:16px;">
                  <div><span>融资阶段：</span> {{item.financeStateStr}}</div>
              </div>
          </div>
      </div>
  </div>
</template>

<script>
export default {
    name:'ListPageItem',
    props:{
        item:{type:Object,default:()=>{return {
                'id':'',
                "title":"",
                "mechanism":"",
                "industryDirect":'',
                "financeQuota":'',
                "financeState":'',
                "industryDirectStr":"",
                "financeQuotaStr":"",
                "financeStateStr":""
        }}}
    },
    data(){
        return {

        }
    },
    methods:{

    }
}
</script>
<style scoped>
.page-item-box{padding:28px 27px 26px;background: #F6F6F6;border-radius: 4px;border-radius: 4px;display:flex;justify-content:flex-start;cursor:pointer;}
.page-item-box .title{font-family: PingFangSC-Medium;font-size: 1vw;color: #333333;}
.page-item-box .desc{display:flex;flex-wrap:wrap;margin-top:16px;}
.page-item-box .desc .desc-item{display:flex;flex:0 1 50%;align-items:center;font-family: PingFangSC-Regular;font-size: 14px;color:#151515;line-height: 24px;}
.page-item-box .desc .desc-item img{margin-right:6px;}
.page-item-box .desc .desc-item span{color: #999999;}

</style>