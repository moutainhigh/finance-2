<template>
  <div class="page-item-box" @click="$emit('to-detail',item)">
      <div class="logo">
          <img src="/image/c6.png" alt="" style="width:100px;height:100px;margin-right:32px;">
      </div>
      <div class="right">
            <div class="title">{{item.title}}</div>
            <div class="desc">
                <div class="desc-item">
                    <img src="/image/icon-suoshujigou.png" alt="" style="width:16px;height:16px;">
                    <div><span>所属机构：</span> {{item.mechanism}}</div>
                </div>
                <div class="desc-item">
                    <img src="/image/icon-dizhi.png" alt="" style="width:16px;height:16px;">
                    <div><span>办公地址：</span>{{item.workAddress}}</div>
                </div>
                <div class="desc-item">
                    <img src="/image/icon-lianxi.png" alt="" style="width:16px;height:16px;">
                    <div><span>联系方式：</span>{{item.tel}}</div>
                </div>
            </div>
            <div class="descs-item">
                <img src="/image/icon-chanpingjieshao.png" alt="" style="width:16px;height:16px;">
                <div>
                    <span>产品简介：</span> 
                    {{item.introduce}}
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
.page-item-box{padding:28px 27px 26px;background: #ffffff;;border-radius: 4px;border-radius: 4px;display:flex;justify-content:flex-start;}
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