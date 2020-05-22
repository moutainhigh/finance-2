<template>
    <div class="guquan">
        <div class="top">
            <Header></Header>
            <div class="guquan-title">
                <div>债券融资</div>
                <div>Bond FINANCING</div>
            </div>
        </div>
        <div class="list-box">
            <div class="list-filter" :style="openStyle">
                <div class="filter-head">
                    <div class="head-title">
                        <img src="/image/icon-chaxunliebiao.png" alt="" style="width:20px;height:21px;vertical-align:sub;">
                        <span>筛选条件</span>
                    </div>
                    <div class="search">
                        <input type="text" placeholder="请输入产品或机构名称" />
                        <div class="search-btn">
                            <img src="/image/search.svg" alt="" style="width:15px;height:15px;vertical-align:sub;"> 搜索
                        </div>
                    </div>
                </div>
                <div class="filter-body" :style="openBodyStyle">
                    <div class="filter-item">
                        <div class="item-title">融资额度</div>
                        <div class="item">
                            <div class="item-list" v-for="(item,index) in eduList" :key="index">{{item.title}}</div>
                        </div>
                    </div>
                    <div class="filter-item">
                        <div class="item-title">融资阶段</div>
                        <div class="item">
                            <div class="item-list " v-for="(item,index) in rzjd" :key="index">{{item.title}}</div>
                        </div>
                    </div>
                    <div class="filter-item">
                        <div class="item-title">行业方向</div>
                        <div class="item">
                            <div class="item-list " v-for="(item,index) in hyfx" :key="index">{{item.title}}</div>
                        </div>
                    </div>
                    <div class="filter-item more">
                        <div class="item-title" v-for="(item,index) in selGroup" :key="index">
                            <span>{{item.title}}：</span>
                            <a-select default-value="不限" style="width: 120px">
                                <a-select-option value="jack">
                                    Jack
                                </a-select-option>
                                <a-select-option value="不限">
                                    不限
                                </a-select-option>
                                <a-select-option value="disabled" disabled>
                                    Disabled
                                </a-select-option>
                                <a-select-option value="Yiminghe">
                                    yiminghe
                                </a-select-option>
                            </a-select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="adv-btns">
                <div class="adv-btn" @click="openSearch">
                    <div>{{openMore==true?'收起':'高级选项'}}</div>
                    <img :src="openMore==true?'/image/icon-shouqi.png':'/image/icon-xiala.png'" style="width:16px;height:16px;" alt="">
                </div>
            </div>
            <div class="lists-box">
                <div class="filter-head">
                    <div class="head-title">
                        <img src="/image/icon-chaxunliebiao.png" alt="" style="width:20px;height:21px;vertical-align:sub;">
                        <span>列表</span>
                    </div>
                    <div class="search">
                        <span>排序</span>
                        <div class="search-btn">
                            <a-select default-value="默认排序" style="width: 120px" class="sort">
                                <a-select-option value="jack">
                                    Jack
                                </a-select-option>
                                <a-select-option value="不限">
                                    不限
                                </a-select-option>
                                <a-select-option value="disabled" disabled>
                                    Disabled
                                </a-select-option>
                                <a-select-option value="Yiminghe">
                                    yiminghe
                                </a-select-option>
                            </a-select>
                        </div>
                    </div>
                </div>
                <div class="list-items">
                    <ListPageItem class="list-item"></ListPageItem>
                    <ListPageItem class="list-item"></ListPageItem>
                    <ListPageItem class="list-item"></ListPageItem>
                    <ListPageItem class="list-item"></ListPageItem>
                    <ListPageItem class="list-item"></ListPageItem>
                </div>
                <div class="page">
                    <div class="page-num">
                        <a-pagination :default-current="1" :total="500" @change="onChange" />
                    </div>
                    <span class="total">共1000条</span>
                    <input type="text" class="page-input">
                    <div class="change-btn">跳转</div>
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
        ListPageItem:()=>import("@/components/ListPageItem.vue"),
        Footer:()=>import("@/components/Footer.vue")
    }
}
</script>
<style scoped>
.guquan{background: #F6F6F6;}
.guquan .top{height:342px;background:url(/image/bj-guquanrongzi.png) no-repeat;background-size:cover;position: relative;}
.guquan .top .guquan-title{position:absolute;top:136px;left:130px;}
.guquan .top .guquan-title div:nth-child(1){font-family: BDZYJT--GB1-0;font-size: 42px;color: #FFFFFF;}
.guquan .top .guquan-title div:nth-child(2){font-family: PingFangSC-Regular;font-size: 20px;color: #FFFFFF;}
.guquan .list-box{padding:0px 130px;margin:-85px auto 0px;}
.guquan .list-box .list-filter{background: #FFFFFF;border-radius: 4px;border-radius: 4px;position: relative;z-index:1;height:236px;overflow:hidden;transition:all 1s;}
.guquan .list-box .list-filter .filter-head{padding:32px 27px 0px;display:flex;justify-content: space-between;align-items:center;}
.guquan .list-box .list-filter .filter-head .head-title span{font-family: PingFangSC-Medium;font-size: 20px;color: #333333;margin-left:15px;font-weight:300;}
.guquan .list-box .list-filter .filter-head .search{display:flex;width:364px;height:32px;background: #F6F6F6;border-radius: 25px;border-radius: 25px;}
.guquan .list-box .list-filter .filter-head .search input{margin: 6px 22px;border: none;background: transparent;outline: none;line-height: 20px;width: 232px;}
.guquan .list-box .list-filter .filter-head .search .search-btn{transition:all 0.5s;background: rgba(47,171,218,0.8);border-radius:25px;border-radius:25px;width:88px;height:32px;
font-family:PingFangSC-Regular;font-size:14px;color:#FFFFFF;text-align:center;line-height:32px;cursor:pointer;}
.guquan .list-box .list-filter .filter-head .search .search-btn:hover{background:rgba(47,171,218,1);}
.guquan .filter-body{height:150px;overflow:hidden;transition:all 1s;}
.guquan .filter-body .filter-item{margin:23px 24px 22px;display:flex;flex-wrap:wrap;justify-content:flex-start;}
.guquan .filter-body .filter-item .filter-title{font-size: PingFangSC-Medium;font-size: 16px;color: #333333;}
.guquan .filter-body .more .item-title{margin-left:36px;width:calc(25% - 36px);}
.guquan .filter-body .more .item-title:nth-child(4n+1){margin-left:0px;margin-bottom:19px;}
.guquan .filter-body .filter-item .item{margin-left:56px;font-family: PingFangSC-Regular;font-size: 16px;color: #666666;display:flex;}
.guquan .filter-body .filter-item .item .item-list{margin-left:29px;cursor:pointer;}
.guquan .filter-body .filter-item .item .item-list:nth-child(1){margin-left:0px;}
.guquan .filter-body .filter-item .item .active{color: #2FABDA;}
.guquan .adv-btns{display:flex;justify-content:center;position:relative;z-index:2;}
.guquan .adv-btns .adv-btn{width:78px;height:57px;cursor: pointer;position: absolute;bottom: -22px;border-radius: 50%;background: #fff;padding: 15px;}
.guquan .adv-btns .adv-btn div{font-family: PingFangSC-Regular;font-size: 12px;color: #2FABDA;}
.guquan .adv-btns .adv-btn{display:flex;flex-direction:column;align-items:center;}
.lists-box .page{display:flex;justify-content:center;margin-top:30px;align-items:center;}
.lists-box .page .total{font-family: PingFangSC-Regular;font-size: 14px;color: #A2A2A2;letter-spacing: 0.12px;text-align: center;margin:auto 15px auto;}
.lists-box .page .page-input{background: #FFFFFF;border: 1px solid #D8D8D8;border-radius: 2px;border-radius: 2px;outline: none;width: 48px;height: 26px;margin-right:7px;padding:0 5px;}
.lists-box .page .change-btn{cursor:pointer;background: #418EE2;border-radius: 2px;border-radius: 2px;width: 48px;height: 26px;line-height:26px;font-family: PingFangSC-Regular;font-size: 14px;color: #2FABDA;letter-spacing: 0.12px;text-align: center;}
.lists-box{margin:30px auto;padding-bottom:30px;background:#FFFFFF;border-radius:4px;border-radius:4px;}
.guquan .lists-box .filter-head{padding:32px 27px 0px;display:flex;justify-content: space-between;align-items:center;}
.guquan .lists-box .filter-head .head-title span{font-family: PingFangSC-Medium;font-size: 20px;color: #333333;margin-left:15px;font-weight:300;}
.guquan .lists-box .filter-head .search{display:flex;align-items:center;}
.guquan .lists-box .filter-head .search span{opacity: 0.6;font-family: PingFangSC-Regular;font-size: 16px;color: #333333;}
.guquan .lists-box .filter-head .search .sort{background: #F6F6F6;border-radius: 16px;border-radius: 16px;margin-left:25px;}
.guquan .lists-box .filter-head .search .sort >>> .ant-select-selection{background-color: #F6F6F6;border: 1px solid #F6F6F6;border-top-width: 1.02px;border-radius: 28px;}
.guquan .lists-box .list-items{padding:32px 27px 0px;display:flex;justify-content:flex-start;flex-wrap:wrap;}
.guquan .lists-box .list-items .list-item{width:calc(50% - 24px);margin-bottom:24px;}
.guquan .lists-box .list-items .list-item:nth-child(2n+2){margin-left:24px;}





</style>