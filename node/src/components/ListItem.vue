<template>
  <div class="list-item">
        <svg width="248" height="50"  version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
            <!-- <path d="M12 0L0 25L12 50" fill="none" stroke="#9ee9ff" stroke-width="1" />
            <path d="M20 0L8 25L20 50" fill="none" stroke="#9ee9ff" stroke-width="2" />
            <path d="M12 0L20 0" fill="none" stroke="#9ee9ff" stroke-width="1" />
            <path d="M12 50L20 50" fill="none" stroke="#9ee9ff" stroke-width="1" /> -->
            <path d="M20 0L8 25L20 50" fill="none" stroke="#9ee9ff" stroke-width="3" />
            <polyline points="12 0 0 25 12 50 236 50 248 25 236 0 12 0" style="stroke:#9ee9ff;stroke-width:2;" :fill="index==currIndex?'url(#insetshow)':'none'" />
            <text x="124" y="33" :style="index==currIndex?itemActiveStyle:''" text-anchor="middle" fill="#9ee9ff" style="font-family: PingFangSC-Medium; font-size: 18px; font-weight: normal; font-stretch: normal; letter-spacing: 0px;color: #9ee9ff;text-shadow: 1px 1px 3px #9ee9ff, -1px -1px 3px #9ee9ff">
            {{item.title}}
            </text>
            <path d="M226 50 L238 25 L226 0" fill="none" stroke="#9ee9ff" stroke-width="3" />
        </svg>
    </div>
</template>

<script>
export default {
    name:'ListItem',
    props:{
        item:Object,default:()=>{return {title:''}},
        index:Number,default:0,
        currIndex:Number,default:0,
    },
    data(){
        return {
            itemActiveStyle:{
                color:'#ffd600',
                fill:'#ffd600',
                textShadow:'1px 1px 3px #ffd600,-1px -1px 3px #ffd600'
            }
        }
    }
}
</script>
<style scoped>

</style>