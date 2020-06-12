<template>
  <div class="user">
      <Header :isBg="true"></Header>
      <div class="user-box">
        <div class="base-info">
            <div class="title">个人中心</div>
            <div class="info-detail">
                <div class="logo-box">
                    <div class="logo">
                        <img src="/image/c6.png" >
                    </div>
                    <div style="color:red;text-align:center;font-size:14px;">
                        *点击图片更换企业LOGO
                    </div>
                </div>
                <div class="company-info">
                    <div class="company-name">{{$store.state.userInfo.companyName}}</div>
                    <div class="company-ucode">{{$store.state.userInfo.companyName}}</div>
                    <div class="company-concat">
                        <div class="company-uname">联系人：<span>{{$store.state.userInfo.userName}}</span></div>
                        <div class="company-uname">联系电话：<span>{{$store.state.userInfo.mobile}}</span></div>
                    </div>
                    <div class="company-desc">
                        <div>公司简介：</div>
                        <span></span>
                    </div>
                </div>
            </div>
            <div class="line"></div>
            <div class="prodlist">
                <div class="title">申请列表</div>
                <div class="list">
                    <ProductItem class="item" @click="toDetail(item)" v-for="(item,index) in matchList" :key="index"></ProductItem>
                </div>
            </div>
        </div>
      </div>
      <Footer></Footer>
  </div>
</template>
<script>
export default {
    data(){
        return{
            matchList:[1,2]
        }
    },
    methods:{
        getMatchList(){

        },
        toDetail(item){
            // 根据产品类型跳转
            this.$router.push({path:'/detail',query:{companyId:item.id}})
        }
    },
    components:{
        Header:()=>import("@/components/Header.vue"),
        Footer:()=>import("@/components/Footer.vue"),
        ProductItem:()=>import("@/components/ProductItem.vue"),
    }
}
</script>
<style lang="scss" scoped>
.user{
    background-color:#f6f6f6;
    .user-box{
        padding: 0px 130px;
        min-height:calc(100vh - 331px);
        .title{
            padding:15px;
            font-weight:600;
            font-size:16px;
        }
        .base-info{
            margin-top:30px;
            background-color:white;
            min-height:200px;
            .info-detail{
                padding:15px;
                display:flex;
                .logo-box{
                    .logo{
                        cursor: pointer;
                        width:200px;
                        height:200px;
                        display:flex;
                        flex-direction:column;
                        justify-content: center;
                        align-items: center;
                        background-color:#f6f6f6;
                        img{
                            width:100px;
                            height:100px;
                        }
                    }

                }
                .company-info{
                    flex:1;
                    margin-left:45px;
                    color:#acacac;
                    .company-name{
                        color:#666;
                        font-size:16px;
                        font-weight:600;
                    }
                    .company-ucode{
                        color:#acacac;
                        font-size:14px;
                    }
                    .company-concat{
                        display: flex;
                        .company-uname{
                            padding:15px 0px;
                            span{
                                color:#666;
                            }
                        }
                        .company-uname:nth-child(2){
                            margin-left:30px;
                        }
                    }
                    .company-desc{
                        display:flex;
                        span{
                            flex:1;
                            color:#666;
                            display: -webkit-box;
                            -webkit-line-clamp: 2;
                            text-overflow: ellipsis;
                            -webkit-box-orient: vertical;
                            overflow: hidden;
                        }
                    }
                }
            }
        }
        .line{
            height:1px;
            background-color:#f6f6f6;
        }
        .prodlist{
            margin-top:1px;
            background-color:white;
            .list{
                padding:15px;
                .item{
                    margin-bottom:15px;
                }
            }
        }
    }

}
</style>