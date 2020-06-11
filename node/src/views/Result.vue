<template>
  <div>
        <a-modal v-model="show" title="温馨提示" centered :keyboard="false" :closable="false" :maskClosable="false" :footer="null">
            <p>{{$route.params.msg}}</p>
            <p>{{second}}秒后，跳转...</p>
        </a-modal>
  </div>
</template>
<script>
export default {
    data(){
        return {
            show:true,
            second:3
        }
    },
    methods:{
        toPage(){
            let path = this.$route.params.path?this.$route.params.path:"/"
            this.$router.push({path:path});
        }
    },
    mounted(){
        setInterval(()=>{
            this.second--;
            if(this.second==0){
                this.toPage();
            }
        },1000)
    }
}
</script>

<style>

</style>