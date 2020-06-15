<template>
  <div class="user">
      <Header :isBg="true"></Header>
      <div class="user-box">
        <div class="base-info">
            <div class="title">个人中心</div>
            <div class="info-detail">
                <div class="logo-box">
                    <div class="logo">
                        <a-spin :spinning="spinning">
                            <a-upload
                                name="file"
                                list-type="picture-card"
                                class="avatar-uploader"
                                :show-upload-list="false"
                                action="/finance/uploadCompanyLogo"
                                :before-upload="beforeUpload"
                                @change="changeUpFile"
                                :headers="headers"
                            >
                                <img v-if="companyInfo.companyLogo" :src="companyInfo.companyLogo" alt="avatar" />
                                <div v-else>
                                <div class="ant-upload-text">
                                    <img src="/image/c6.png" >
                                </div>
                                </div>
                            </a-upload>
                        </a-spin>
                    </div>
                    <div style="color:red;text-align:center;font-size:14px;">
                        *点击图片更换企业LOGO
                    </div>
                </div>
                <div class="company-info">
                    <div class="company-name">{{companyInfo.companyName}}</div>
                    <div class="company-ucode">{{companyInfo.creditCode}}</div>
                    <div class="company-concat">
                        <div class="company-uname">联系人：<span>{{companyInfo.contact}}</span></div>
                        <div class="company-uname">联系电话：<span>{{companyInfo.tel}}</span></div>
                    </div>
                    <div class="company-desc">
                        <div>公司简介：{{companyInfo.introduce}}</div>
                        <span></span>
                    </div>
                </div>
            </div>
            <div class="line"></div>
            <div class="prodlist">
                <div class="title">申请列表</div>
                <a-spin :spinning="spinninglist">
                <div class="list">
                    <ProductItem class="item" @to-detail="toDetail(item)" :item="item" v-for="(item,index) in matchList" :key="index"></ProductItem>
                </div>
                </a-spin>
                <div style="margin:auto;text-align:center;padding-bottom:15px;">
                    <a-pagination :total="totalNum" @change="onChange" :current='pageNo' :defaultPageSize='pageSize' />
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
            matchList:[],
            companyInfo:{},
            pageNo:1,
            pageSize:10,
            totalNum:0,
            spinning:false,
            spinninglist:false,
            upData:{companyId:0},
            headers:{}
        }
    },
    created(){
        this.headers = {'Authorization':this.$store.state.token?'Bearer '+this.$store.state.token:''}
        this.getCompany();
    },
    methods:{
        beforeUpload(file){
            return true;
        },
        changeUpFile(info){
            if (info.file.status === 'uploading') {
                this.spinning = true;
                return;
            }
            if (info.file.status === 'done') {
                this.spinning = false;
            }
        },
        getCompany(){
            let params = {userId:this.$store.state.userInfo.id};
            this.$http.post('/finance/company/getCompanyInfoById',params).then(res=>{
                if(res.data.code==0){
                    this.companyInfo = res.data.content;
                    // this.companyInfo.companyLogo = "https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png";
                    this.getMatchList();
                }else{
                    this.$message.error(res.data.msg);
                }
            }).catch(err=>{})
        },
        getMatchList(){
            let params = {content:{companyId:this.companyInfo.id},pager:{pageSize:this.pageSize,currentPage:this.pageNo},pageSize:this.pageSize,currentPage:this.pageNo};
            this.spinninglist = true ;
            this.$http.post('/finance/financeApply/getCompanyApplyProductVo',params).then(res=>{
            this.spinninglist = false ;
                if(res.data.code==0){
                    this.matchList = res.data.content.list;
                    this.totalNum = res.data.content.pager.totalCount;
                }else{
                    this.$message.error(res.data.msg);
                }
            }).catch(err=>{})
        },
        toDetail(item){
            // 根据产品类型跳转
            this.$router.push({path:'/detail',query:{companyId:item.productId}})
        },
        onChange(){

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
/deep/ .ant-upload.ant-upload-select-picture-card{
    border:none;
}
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
                    /deep/ .avatar-uploader{
                        border:none;
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