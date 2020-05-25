<template>
    <div class="guquan-title">
        <div>{{title}}</div>
        <div>{{enTitle}}</div>
    </div>
</template>
<script>
export default {
    name:'DespComp',
    data(){
        return {
            title:'债券融资',
            enTitle:'Bond FINANCING'
        }
    },
    created() {
        if(this.$route.path=='/guquan'){
            this.title="股权融资";
            this.entitle="EQUITY FINANCING";
        }
    },
    methods: {
        
    },
}
</script>
<<style lang="scss" scope>
.guquan-title{position:absolute;top:136px;left:130px;}
.guquan-title div:nth-child(1){font-family: BDZYJT--GB1-0;font-size: 42px;color: #FFFFFF;}
.guquan-title div:nth-child(2){font-family: PingFangSC-Regular;font-size: 20px;color: #FFFFFF;}
</style>