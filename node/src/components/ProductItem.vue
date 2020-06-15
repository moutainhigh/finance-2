<template>
    <div class="item-box">
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
                        <div><span>行业方向：</span> {{item.industryDirect}}</div>
                    </div>
                    <div class="desc-item" v-if="item.financeType==0">
                        <img src="/image/icon-rongziedu.png" alt="" style="width:16px;height:16px;">
                        <div><span>融资额度：</span> {{item.financeQuota}}</div>
                    </div>
                    <div class="desc-item" v-if="item.financeType==0">
                        <img src="/image/icon-rognzijieduan.png" alt="" style="width:16px;height:16px;">
                        <div><span>融资阶段：</span> {{item.financeState}}</div>
                    </div>
                    <div class="desc-item" v-if="item.financeType==1">
                        <img src="/image/icon-rongziedu.png" alt="" style="width:16px;height:16px;">
                        <div><span>贷款额度：</span> {{item.loanQuota}}</div>
                    </div>
                    <div class="desc-item" v-if="item.financeType==1">
                        <img src="/image/icon-rognzijieduan.png" alt="" style="width:16px;height:16px;">
                        <div><span>贷款期限：</span> {{item.loanTerm}}</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="status-time">
            <div class="status">{{item.status}}</div>
            <div class="time">申请时间：{{item.createdTime|dateformat}}</div>
        </div>
    </div>
</template>

<script>
export default {
    name:'ProductItem',
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
        dateformat:function(v){
            if(!v){
                return '';
            }
            let date = new Date(Number(v)*1000);
            let month = date.getMonth()>=10?date.getMonth():'0'+date.getMonth().toString();
            let day = date.getDate()>=10?date.getDate():'0'+date.getDate().toString();
            return date.getFullYear().toString()+'-'+month+'-'+day;
        }
    }
}
</script>
<style scoped>
.item-box{display:flex;justify-content:space-between;background-color:#F6F6F6;cursor: pointer;}
.page-item-box{flex:1;padding:28px 27px 26px;background: #F6F6F6;border-radius: 4px;border-radius: 4px;display:flex;justify-content:flex-start;cursor:pointer;}
.page-item-box .title{font-family: PingFangSC-Medium;font-size: 1.2vw;color: #333333;text-overflow: ellipsis;
            white-space: nowrap;
            overflow: hidden;}
.page-item-box .desc{display:flex;flex-wrap:wrap;margin-top:16px;}
.page-item-box .desc .desc-item{text-overflow: ellipsis;
            white-space: nowrap;
            overflow: hidden;display:flex;flex:0 1 50%;align-items:center;font-family: PingFangSC-Regular;font-size: 14px;color:#151515;line-height: 24px;}
.page-item-box .desc .desc-item img{margin-right:6px;}
.page-item-box .desc .desc-item span{color: #999999;}

.status-time{
    width:200px;
    padding: 28px 27px 26px;
    text-align:right;
    display: flex;
    flex-direction:column;
    justify-content: space-between;
}
.status{
    color:red;
}
.time{
    color:#666;
    font-weight:600;
}

</style>