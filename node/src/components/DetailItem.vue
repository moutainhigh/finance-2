<template>
  <div class="page-item-box" @click="$emit('to-detail',item)">
      <div class="logo">
          <img src="/image/c6.png" alt="" style="width:100px;height:100px;margin-right:32px;">
      </div>
      <div class="right">
            <div class="title">物联网投资基金</div>
            <div class="desc">
                <div class="desc-item">
                    <img src="/image/icon-suoshujigou.png" alt="" style="width:16px;height:16px;">
                    <div><span>所属机构：</span> 红杉资本</div>
                </div>
                <div class="desc-item">
                    <img src="/image/icon-dizhi.png" alt="" style="width:16px;height:16px;">
                    <div><span>办公地址：</span> 四川省成都市高新区交子金融中心B座103</div>
                </div>
                <div class="desc-item">
                    <img src="/image/icon-lianxi.png" alt="" style="width:16px;height:16px;">
                    <div><span>联系方式：</span> 028-389400</div>
                </div>
            </div>
            <div class="descs-item">
                <img src="/image/icon-chanpingjieshao.png" alt="" style="width:16px;height:16px;">
                <div>
                    <span>产品简介：</span> 
                    红杉资本中国专注于科技/传媒、医疗健康、消费品/服务、工业科技四个方向的投资机遇，投资了包括阿里巴巴、贝达药业、大众点评网、德邦物流、DJI大疆创新、赶集网、高德软件、光环新网、华大基因、今日头条、京东、聚美优品、美丽说、美团网、陌陌、诺亚财富、奇虎360、万达院线、威高集团、唯品会、文思创新、新产业生物、新浪网、英雄互娱、鱼跃医疗、掌趣科技、中通快递在内的300余家企
                </div>
            </div>
      </div>
  </div>
</template>

<script>
export default {
    name:'DetailItem',
    props:{
        item:{type:Object,default:()=>{return {};}}
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
.page-item-box{padding:28px 27px 26px;background: #ffffff;;border-radius: 4px;border-radius: 4px;display:flex;justify-content:flex-start;cursor:pointer;}
.page-item-box .logo{background: #F6F6F6;width:160px;height:160px;text-align:center;line-height:160px;margin-right:53px;}
.page-item-box .right{flex:1;}
.page-item-box .title{font-family: PingFangSC-Medium;font-size: 24px;color: #333333;}
.page-item-box .desc{display:flex;flex-wrap:wrap;margin-top:16px;flex:1;}
.page-item-box .desc .desc-item{display:flex;width:calc(33% - 38px);align-items:center;font-family: PingFangSC-Regular;font-size: 14px;color:#151515;line-height: 24px;}
.page-item-box .descs-item{display:flex;font-family: PingFangSC-Regular;font-size: 14px;color:#151515;line-height: 24px;}
.page-item-box .descs-item img{margin-top: 4px;}
.page-item-box .desc .desc-item span{color: #999999;}
.page-item-box .descs-item span{color: #999999;}

</style>