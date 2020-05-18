<template>
    <div class="guquan">
        <div class="top">
            <Header></Header>
        </div>
        <div class="list-box">
            <div class="list-filter">
                <DetailItem class="list-item"></DetailItem>
            </div>
        </div>
        <div class="list-box">
            <div class="list-filter">
                <div class="filter-head">
                    <div class="head-title">
                        <img src="/image/icon-chaxunliebiao.png" alt="" style="width:20px;height:21px;vertical-align:sub;">
                        <span>资质要求</span>
                    </div>
                </div>
                <div class="detail-body">
                    <div class="b-item">
                        <span>注册地址：</span>
                        <span>四川省成都市高新区交子金融中心B座103</span>
                    </div>
                    <div class="b-item">
                        <span>融资阶段：</span>
                        <span>Pre-A轮</span>
                    </div>
                    <div class="b-item">
                        <span>融资额度：</span>
                        <span>500-1000万</span>
                    </div>
                    <div class="b-item">
                        <span>行业方向：</span>
                        <span>医药/医疗器械</span>
                    </div>
                    <div class="b-item">
                        <span>股东背景：</span>
                        <span>国家千人</span>
                    </div>
                    <div class="b-item">
                        <span>产品阶段：</span>
                        <span>扩大生产</span>
                    </div>
                    <div class="b-item">
                        <span>营业收入：</span>
                        <span>100-500万</span>
                    </div>
                    <div class="b-item">
                        <span>营业收入增长率：</span>
                        <span>10%-20%</span>
                    </div>
                    <div class="b-item">
                        <span>产品毛利率/预期产品毛利率：</span>
                        <span>0-10%</span>
                    </div>
                    <div class="b-item">
                        <span>净利润：</span>
                        <span>30%-50%</span>
                    </div>
                    <div class="b-item">
                        <span>过往融资金额：</span>
                        <span>无</span>
                    </div>
                    <div class="b-item">
                        <span>实际控制人创业经历：</span>
                        <span>3次以上</span>
                    </div>
                    <div class="b-item">
                        <span>员工人数：</span>
                        <span>100-500人</span>
                    </div>
                    <div class="b-item">
                        <span>目标市场容量：</span>
                        <span>10-30亿元</span>
                    </div>
                    <div class="b-item">
                        <span>目标市场增长率：</span>
                        <span>10-20%</span>
                    </div>
                    <div class="b-item">
                        <span>目标客户：</span>
                        <span>政府</span>
                    </div>
                    <div class="b-item">
                        <span>市场占有率/预期市场占有率：</span>
                        <span>10%-30%</span>
                    </div>
                    <div class="b-item">
                        <span>是否接受回购条款：</span>
                        <span>是</span>
                    </div>
                    <div class="b-item">
                        <span>发明专利数量</span>
                        <span>3-10个</span>
                    </div>
                    <div class="b-item">
                        <span>公司竞争优势：</span>
                        <span>技术优势</span>
                    </div>
                    <div class="b-item">
                        <span>股东累计投入资金：</span>
                        <span>100-500万</span>
                    </div>
                    <div class="b-item">
                        <span>公司所获评定称号：</span>
                        <span>平台生态型龙头企业</span>
                    </div>
                </div>
            </div>
            
        </div>
        <Footer></Footer>
    </div>
</template>
<script>
export default {
    name:"Guquan",
    data(){
        return {
            openMore:false,
            openStyle:{},
            openBodyStyle:{},
            eduList:[
                {title:'不限'},
                {title:'0-100万'},
                {title:'100-200万'},
                {title:'200-500万'},
                {title:'500-1000万'},
                {title:'1000万以上'},
            ],
            rzjd:[
                {title:'不限'},
                {title:'未融资'},
                {title:'天使轮'},
                {title:'Pre-A轮'},
                {title:'B轮'},
                {title:'C轮'},
                {title:'D轮及以上'},
                {title:'上市公司'},
                {title:'其他'},
            ],
            hyfx:[
                {title:'不限'},
                {title:'医药/医疗器械'},
                {title:'电子信息'},
                {title:'互联网'},
                {title:'大数据'},
                {title:'人工智能'},
                {title:'新经济'},
                {title:'轨道交通'},
                {title:'军民融合'},
                {title:'其他'},
            ],
            selGroup:[
                {title:'注册地址'},
                {title:'股东背景'},
                {title:'过往融资金额'},
                {title:'实控人创业经历'},
                {title:'营业收入'},
                {title:'营业收入增长率'},
                {title:'产品毛利率'},
                {title:'净利润'},
                {title:'员工人数'},
                {title:'目标市场容量'},
                {title:'目标市场增长率'},
                {title:'目标客户'},
                {title:'预期市场占有率'},
                {title:'发明专利数量'},
                {title:'公司竞争优势'},
                {title:'公司竞争优势'},
                {title:'股东累计投入资金'},
                {title:'公司所获评定称号'},
            ],
            params:{
                "content":{
                    "financeQuota":"16bjij",
                    "financeState":"u7fg8g",
                    "IndustryDirect":"dgvs8v",
                    "registerAddress":"Apt. 159 夏栋03号， 大庆， 青 534853",
                    "business":"6fjgo4",
                    "staffCount":681,
                    "marketOccupyRate":"tduvlc",
                    "evaluateName":"子骞.雷",
                    "mechanismOrProduct":"ppdvnz"
                },
                "pager":{
                    "pageSize":459,
                    "currentPage":694
                }
            }
        }
    },
    methods:{
        initPage(){
            var params = this.params;
            this.$http.post('/finance/financeCompany/getFinanceCompany',{...params}).then(res=>{
                console.log(res);
            }).catch(err=>console.log(err))
        },
        openSearch(){
            this.openMore = !this.openMore;
            if(this.openMore){
                this.openStyle={overflowX:'auto',height:'auto'}
                this.openBodyStyle={overflowX:'auto',height:'auto'}
            }else{
                this.openStyle={}
                this.openBodyStyle={}
            }
        },
        onChange(){

        },

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