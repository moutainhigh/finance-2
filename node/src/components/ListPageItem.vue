<template>
  <div class="page-item-box" @click="$emit('to-detail',item)">
      <img src="/image/c6.png" alt="" style="width:100px;height:100px;margin-right:32px;">
      <div>
          <div class="title" :title="item.title">{{item.title|subStr(30)}}</div>
          <div class="desc">
              <div class="desc-item">
                  <img src="/image/icon-suoshujigou.png" alt="" style="width:16px;height:16px;">
                  <div :title="item.mechanism"><span>所属机构：</span> {{item.mechanism|subStr(10)}}</div>
              </div>
              <div class="desc-item">
                  <img src="/image/icon-hangyefangxiang.png" alt="" style="width:16px;height:16px;">
                  <div :title="item.industryDirectStr"><span>行业方向：</span> {{item.industryDirectStr|subStr(14)}}</div>
              </div>
              <div class="desc-item" v-if="type==0">
                  <img src="/image/icon-rongziedu.png" alt="" style="width:16px;height:16px;">
                  <div :title="item.financeQuotaStr"><span>融资额度：</span> {{item.financeQuotaStr|subStr(10)}}</div>
              </div>
              <div class="desc-item" v-if="type==0">
                  <img src="/image/icon-rognzijieduan.png" alt="" style="width:16px;height:16px;">
                  <div :title="item.financeStateStr"><span>融资阶段：</span> {{item.financeStateStr|subStr(10)}}</div>
              </div>
              <div class="desc-item" v-if="type==1">
                  <img src="/image/icon-rongziedu.png" alt="" style="width:16px;height:16px;">
                  <div :title="item.loanQuotaStr"><span>贷款额度：</span> {{item.loanQuotaStr|subStr(12)}}</div>
              </div>
              <div class="desc-item" v-if="type==1">
                  <img src="/image/icon-rognzijieduan.png" alt="" style="width:16px;height:16px;">
                  <div :title="item.loanTermStr"><span>贷款期限：</span> {{item.loanTermStr|subStr(10)}}</div>
              </div>
          </div>
      </div>
  </div>
</template>

<script>
export default {
    name:'ListPageItem',
    props:{
        type:{type:Number,default:0},
        item:{type:Object,default:()=>{return {
                'id':'',
                "title":"",
                "mechanism":"",
                "industryDirect":'',
                "financeQuota":'',
                "financeState":'',
                "industryDirectStr":"",
                "financeQuotaStr":"",
                "financeStateStr":"",
                "loanQuota":"",
                "loanTerm":"",
        }}}
    },
    data(){
        return {

        }
    },
    methods:{

    },
    filters:{
        subStr(val,len){
            if(val && val.length>len){
                return val.substr(0,len)+'...';
            }else{
                return val;
            }
        }
    }
}
</script>
<style scoped>
.page-item-box{padding:28px 27px 26px;background: #F6F6F6;border-radius: 4px;border-radius: 4px;display:flex;justify-content:flex-start;cursor:pointer;    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;}
.page-item-box .title{font-family: PingFangSC-Medium;font-size: 1.2vw;color: #333333;text-overflow: ellipsis;
            white-space: nowrap;
            overflow: hidden;}
.page-item-box .desc{display:flex;flex-wrap:wrap;margin-top:16px;}
.page-item-box .desc .desc-item{text-overflow: ellipsis;
            white-space: nowrap;
            overflow: hidden;display:flex;flex:0 1 50%;align-items:center;font-family: PingFangSC-Regular;font-size: 14px;color:#151515;line-height: 24px;}
.page-item-box .desc .desc-item img{margin-right:6px;}
.page-item-box .desc .desc-item span{color: #999999;}

</style>