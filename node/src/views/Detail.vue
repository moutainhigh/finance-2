<template>
    <div class="guquan">
        <div class="top">
            <Header></Header>
        </div>
        <div class="list-box">
            <div class="list-filter">
                <DetailItem class="list-item" :item="detail"></DetailItem>
            </div>
        </div>
        <div class="list-box">
            <div class="list-filter">
                <div class="filter-head">
                    <div class="head-title">
                        <img src="/image/icon-chaxunliebiao.png" alt="" style="width:20px;height:21px;vertical-align:sub;">
                        <span>资质要求</span>
                    </div>
                    <a-spin :spinning="spinning">
                    <a-button type="primary" @click="subMatch">立即申请</a-button>
                    </a-spin>
                </div>
                <div class="detail-body">
                    <div class="b-item">
                        <span>注册地址：</span>
                        <span>{{detail.registerAddressStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>融资阶段：</span>
                        <span>{{detail.financeStateStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>融资额度：</span>
                        <span>{{detail.financeQuotaStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>行业方向：</span>
                        <span>{{detail.industryDirectStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>股东背景：</span>
                        <span>{{detail.shareholderStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>产品阶段：</span>
                        <span>{{detail.productStateStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>营业收入：</span>
                        <span>{{detail.businessStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>营业收入增长率：</span>
                        <span>{{detail.businessAddRateStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>产品毛利率/预期产品毛利率：</span>
                        <span>{{detail.productRateStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>净利润：</span>
                        <span>{{detail.netInterestRateStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>过往融资金额：</span>
                        <span>{{detail.oldFinanceQuotaStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>实际控制人创业经历：</span>
                        <span>{{detail.experienceStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>员工人数：</span>
                        <span>{{detail.staffCountStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>目标市场容量：</span>
                        <span>{{detail.marketCapacityStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>目标市场增长率：</span>
                        <span>{{detail.marketAddRateStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>目标客户：</span>
                        <span>{{detail.targetCustomerStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>市场占有率/预期市场占有率：</span>
                        <span>{{detail.marketOccupyRateStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>是否接受回购条款：</span>
                        <span>{{detail.boolBuyBackStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>发明专利数量</span>
                        <span>{{detail.patentCountStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>公司竞争优势：</span>
                        <span>{{detail.advantageStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>股东累计投入资金：</span>
                        <span>{{detail.capitalsStr}}</span>
                    </div>
                    <div class="b-item">
                        <span>公司所获评定称号：</span>
                        <span>{{detail.evaluateNameStr}}</span>
                    </div>
                </div>
            </div>
            
        </div>
        <Footer></Footer>
    </div>
</template>
<script>
export default {
    name:"Detail",
    data(){
        return {
            detail:{},
            spinning:false
        }
    },
    methods:{
        initPage(){
            var params = this.$route.query;
            this.$http.post('/finance/financeStockDetail/getFinanceStockProductDetail',{...params}).then(res=>{
                if(res.data.code==0){
                    this.detail = res.data.content;
                }
            }).catch()
        },
        subMatch(){
            if(!this.$store.state.token){
                this.$router.push({name:'Home',params:{islogin:1,isBack:1}});
                return ;
            }
            // 股权申请
            let params = {userId:this.$store.state.userInfo.id,productType:0};
            this.spinning=true;
            // /finance/company/getCompanyBoolMatch
            this.$http.postWithAuth('/finance/company/getCompanyBoolMatch',params).then(res=>{
                this.spinning=false;
                if(res.data.code==0){
                    if(res.data.content.boolMatch==0){
                        this.$router.push({name:'Match',params:{active:0,isSub:true,productId:Number(this.$route.query.companyId)}});
                    }else{
                        this.$confirm({
                            title:'温馨提示',
                            content:'是否确认提交申请吗？',
                            centered:true,
                            okText:"确定",
                            cancelText:'取消',
                            onOk:()=>{
                                this.doSubMatch();
                            },
                            onCancel:()=>{

                            }
                        })
                        // this.doSubMatch();
                    }
                }else{
                    this.$message.error(res.data.msg);
                }
            }).catch()
            
        },
        doSubMatch(){
            // 股权申请
            let params = {productId:this.$route.query.companyId,userId:this.$store.state.userInfo.id};
            // /finance/financeApply/saveProductApply
            this.spinning=true;
            this.$http.postWithAuth('/finance/financeApply/saveProductApply',params).then(res=>{
                this.spinning=false;
                if(res.data.code==0){
                    this.$router.push({name:'Result',params:{path:"/",msg:"申请成功"}})
                }else{
                    this.$message.error(res.data.msg);
                }
            }).catch()
        }
    },
    mounted(){
        this.initPage();
    },
    components:{
        Header:()=>{return import('@/components/Header.vue')},
        DetailItem:()=>import("@/components/DetailItem.vue"),
        Footer:()=>import("@/components/Footer.vue")
    }
}
</script>
<style scoped>
.guquan{background: #F6F6F6;}
.guquan .top{background: #06162C;position: relative;}
.guquan .list-box{padding:0px 130px;margin:25px auto 40px;}
.guquan .list-box .list-filter{background: #FFFFFF;border-radius: 4px;border-radius: 4px;position: relative;z-index:1;padding-bottom:30px;transition:all 1s;}
.guquan .list-box .list-filter .filter-head{padding:32px 27px 0px;display:flex;justify-content: space-between;align-items:center;}
.guquan .list-box .list-filter .filter-head .head-title span{font-family: PingFangSC-Medium;font-size: 20px;color: #333333;margin-left:15px;font-weight:300;}
.guquan .list-box .list-filter .filter-head .search{display:flex;width:364px;height:32px;background: #F6F6F6;border-radius: 25px;border-radius: 25px;}
.guquan .list-box .list-filter .filter-head .search input{margin: 6px 22px;border: none;background: transparent;outline: none;line-height: 20px;width: 232px;}
.guquan .list-box .list-filter .filter-head .search .search-btn{transition:all 0.5s;background: rgba(47,171,218,0.8);border-radius:25px;border-radius:25px;width:88px;height:32px;
font-family:PingFangSC-Regular;font-size:14px;color:#FFFFFF;text-align:center;line-height:32px;cursor:pointer;}
.guquan .list-box .list-filter .filter-head .search .search-btn:hover{background:rgba(47,171,218,1);}
.guquan .detail-body{margin-top:26px;}
.guquan .detail-body .b-item{display:flex;justify-content:flex-start;margin-bottom:8px;}
.guquan .detail-body .b-item span:nth-child(1){width:309px;font-family: PingFangSC-Regular;font-size: 16px;color: #999999;text-align: right;line-height: 24px;}
.guquan .detail-body .b-item span:nth-child(2){margin-left:15px;font-family: PingFangSC-Regular;font-size: 16px;color: #333333;text-align: justify;line-height: 24px;}




</style>