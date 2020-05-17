<template>
  <div class="footer">
      <div class="footer-left">
          <div>联系我们</div>
          <div>Contact Us</div>
      </div>
      <div class="footer-center">
          <div>成都科技服务集团有限公司</div>
          <div>四川省成都市高新区南部园区XXX</div>
          <div>028-70808090</div>
          <div>123456@rongchaug.com</div>
          <div>
              <img src="/image/button-weixin.png" alt="">
              <img src="/image/button-weibo.png" alt="">
              <img src="/image/button-youxiang.png" alt="">
          </div>
      </div>
      <div class="footer-right">
          <div>Copyright   2018 成都科技服务集团有限公司</div>
          <div>蜀ICP备18010101号-2</div>
      </div>
  </div>
</template>
<script>
export default {
    name:'Footer',
    data(){
        return{

        }
    },
    methods:{

    }
}
</script>
<style scoped>
.footer{background: #2D3540;padding:63px 120px 43px;display:flex;justify-content:space-between;}
.footer .footer-left{width:129px;margin-right:96px;font-family: PingFangSC-Semibold; font-size: 24px; color: #FFFFFF;} 
.footer .footer-left div:nth-child(2){font-size: 12px;} 
.footer .footer-center{flex:1;font-family: PingFangSC-Semibold;font-size: 12px;color: #FFFFFF;}
.footer .footer-center div{margin-bottom:7px;}
.footer .footer-center div:first-child{margin-top:5px;}
.footer .footer-center div:last-child{margin-top:23px;}
.footer .footer-center div:last-child img{margin-left:20px;cursor:pointer;}
.footer .footer-center div:last-child img:nth-child(1){margin-left:0px;}
.footer .footer-right{font-family: PingFangSC-Semibold; font-size: 12px; color: #FFFFFF; text-align: right;}
.footer .footer-right>div{margin-bottom:14px;}
</style>