<template>
  <div class="match-box">
    <Header @do-login="login" @to-reg="reg" :isBg="true"></Header>
    <div class="match-body">
        <div class="match-top">
            <div class="back">返回</div>
            <div class="tab">
                <div :class="active==0?'active':''" @click="tabchange(0)">股权融资</div>
                <div :class="active==1?'active':''" @click="tabchange(1)">债权融资</div>
            </div>
        </div>
        <div class="tis">请尽量填写更多信息，以便更精准的为您推荐相关融资产品!</div>
        <a-card title="基本信息" class="card">
            <a-form-model ref="baseForm" :model="baseForm" :rules="baseRules" :label-col="{span:4}" :wrapper-col="{span:6}">
                    <a-form-model-item ref="registerAddress" label="注册地址" prop="registerAddress" class="input-item">
                        <a-select v-model="baseForm.registerAddress" @blur="
                            () => {
                                $refs.registerAddress.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="">不限</a-select-option>
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('REGION')">{{item.value}}</a-select-option>
                        </a-select> 
                    </a-form-model-item>
                    <a-form-model-item ref="workAddress" label="办公地址" prop="workAddress" class="input-item">
                        <a-input v-model="baseForm.workAddress" @blur="
                            () => {
                                $refs.workAddress.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="username" label="联系人" prop="username" class="input-item">
                        <a-input v-model="baseForm.username" @blur="
                            () => {
                                $refs.username.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="联系方式" prop="mobile" class="input-item">
                        <a-input v-model="baseForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="desc" label="公司简介" prop="desc" class="input-item" :wrapper-col="{ span: 12, offset: 0 }">
                        <a-textarea v-model="baseForm.desc" @blur="
                            () => {
                                $refs.desc.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
            </a-form-model>
        </a-card>
        <a-card title="业务信息" class="card-work">
            <a-form-model ref="ruleForm" :model="matchForm" :rules="rules" :label-col="{span:4}" :wrapper-col="{span:12}">
                    <a-form-model-item ref="financeState" label="融资阶段" prop="financeState" class="input-item" >
                        <a-select v-model="matchForm.financeState"  @blur="
                            () => {
                                $refs.financeState.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('RZJD')">{{item.value}}</a-select-option>
                        </a-select>
                        <a-input v-show="matchForm.financeState==1" placeholder="请输入其他的内容" @blur=" 
                            () => {
                                $refs.financeState.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <!-- <a-form-model-item ref="financeQuota" label="融资额度" prop="financeQuota" class="input-item">
                        <a-select v-model="matchForm.financeQuota"  @blur="
                            () => {
                                $refs.financeQuota.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('RZED')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="IndustryDirect" label="行业方向" prop="IndustryDirect" class="input-item">
                        <a-select v-model="matchForm.IndustryDirect" @blur="
                            () => {
                                $refs.IndustryDirect.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('HYFX')">{{item.value}}</a-select-option>
                        </a-select>
                        <a-input v-show="matchForm.IndustryDirect==1" placeholder="请输入其他的内容" @blur=" 
                            () => {
                                $refs.IndustryDirect.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="shareholder" label="股东背景" prop="shareholder" class="input-item">
                        <a-select v-model="matchForm.shareholder" @blur="
                            () => {
                                $refs.shareholder.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('GDBJ')">{{item.value}}</a-select-option>
                        </a-select>
                        <a-input v-show="matchForm.shareholder==1" placeholder="请输入其他的内容" @blur=" 
                            () => {
                                $refs.shareholder.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="productState" label="产品阶段" prop="productState" class="input-item">
                        <a-select v-model="matchForm.productState" @blur="
                            () => {
                                $refs.productState.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('CPJD')">{{item.value}}</a-select-option>
                        </a-select>
                        <a-input v-show="matchForm.productState==1" placeholder="请输入其他的内容" @blur=" 
                            () => {
                                $refs.productState.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="business" label="营业收入" prop="business" class="input-item">
                        <a-select v-model="matchForm.business" @blur="
                            () => {
                                $refs.business.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('YYSR')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="businessAddRate" label="营业收入增长率" prop="businessAddRate" class="input-item">
                        <a-select v-model="matchForm.businessAddRate" @blur="
                            () => {
                                $refs.businessAddRate.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('YYSRZZL')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="productRate" label="产品毛利率/预期产品毛利率" prop="productRate" class="input-item">
                        <a-select v-model="matchForm.productRate" @blur="
                            () => {
                                $refs.productRate.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('CPMLL')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="netInterestRate" label="净利润" prop="netInterestRate" class="input-item">
                        <a-select v-model="matchForm.netInterestRate" @blur="
                            () => {
                                $refs.netInterestRate.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('JLL')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="oldFinanceQuota" label="过往融资金额" prop="oldFinanceQuota" class="input-item">
                        <a-select v-model="matchForm.oldFinanceQuota" @blur="
                            () => {
                                $refs.oldFinanceQuota.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('GWRZJE')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="experience" label="实控人创业经历" prop="experience" class="input-item">
                        <a-select v-model="matchForm.experience" @blur="
                            () => {
                                $refs.experience.onFieldBlur();
                            }
                            ">
                            <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('CYJL')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="companyStatus" label="历史创业企业状态" prop="companyStatus" class="input-item">
                        <a-select v-model="matchForm.companyStatus" @blur="
                            () => {
                                $refs.companyStatus.onFieldBlur();
                            }
                            ">
                           <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('QYZT')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="staffCount" label="员工人数" prop="staffCount" class="input-item">
                        <a-select v-model="matchForm.staffCount" @blur="
                            () => {
                                $refs.staffCount.onFieldBlur();
                            }
                            ">
                           <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('YGRS')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="marketCapacity" label="市场容量" prop="marketCapacity" class="input-item">
                        <a-select v-model="matchForm.marketCapacity" @blur="
                            () => {
                                $refs.marketCapacity.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('SCRL')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="marketAddRate" label="市场容量预期增长率" prop="marketAddRate" class="input-item">
                        <a-select v-model="matchForm.marketAddRate" @blur="
                            () => {
                                $refs.marketAddRate.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('SCRLZZL')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="targetCustomer" label="目标客户" prop="targetCustomer" class="input-item">
                        <a-select v-model="matchForm.targetCustomer" @blur="
                            () => {
                                $refs.targetCustomer.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('MBKH')">{{item.value}}</a-select-option>
                          <a-input v-show="matchForm.targetCustomer==1" placeholder="请输入其他的内容" @blur=" 
                                () => {
                                    $refs.targetCustomer.onFieldBlur();
                                }
                                "
                            />
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="marketOccupyRate" label="市场占有率/预期市场占有率" prop="marketOccupyRate" class="input-item">
                        <a-select v-model="matchForm.marketOccupyRate" @blur="
                            () => {
                                $refs.marketOccupyRate.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('SCZYL')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="boolBuyBack" label="是否接受回购条款" prop="boolBuyBack" class="input-item">
                        <a-select v-model="matchForm.boolBuyBack" @blur="
                            () => {
                                $refs.boolBuyBack.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('SFHG')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="patentCount" label="发明专利数量" prop="patentCount" class="input-item">
                        <a-select v-model="matchForm.patentCount" @blur="
                            () => {
                                $refs.patentCount.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('FMZLSL')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="advantage" label="公司竞争优势" prop="advantage" class="input-item">
                        <a-checkbox @change="onChange">成本优势</a-checkbox>
                        <a-select v-model="matchForm.advantage" style="width:5vw;" @blur="
                            () => {
                                $refs.advantage.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('gsjzys')">{{item.value}}</a-select-option>
                        </a-select>
                        <a-checkbox @change="onChange" style="margin-left:0.3vw;">技术优势</a-checkbox>
                        <a-select v-model="matchForm.advantage" style="width:5vw;" @blur="
                            () => {
                                $refs.advantage.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('gsjzys')">{{item.value}}</a-select-option>
                        </a-select>
                        <a-checkbox-group :options="['渠道优势','先发优势','资质优势']" style="margin-left:0.3vw;"></a-checkbox-group>
                        <a-checkbox @change="onChange" style="margin-left:0.3vw;" v-model="matchForm.advantage" >其他</a-checkbox>
                        <a-input v-show="matchForm.advantage" style="width:5vw;margin-left:0.3vw;"></a-input>
                    </a-form-model-item>
                    <a-form-model-item ref="capitals" label="股东累计投入资金" prop="capitals" class="input-item">
                        <a-select v-model="matchForm.capitals" @blur="
                            () => {
                                $refs.capitals.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('GDLJTRZJ')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="timeToMarket" label="预计上市时间" prop="timeToMarket" class="input-item">
                        <a-select v-model="matchForm.timeToMarket" @blur="
                            () => {
                                $refs.timeToMarket.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('SSSJ')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item> -->
                    <a-form-model-item ref="evaluateName" label="公司所获评定称号" prop="evaluateName" class="input-item">
                        <a-select v-model="matchForm.evaluateName" @blur="
                            () => {
                                $refs.evaluateName.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('PDCH')">{{item.value}}</a-select-option>
                        </a-select>
                        <a-input v-show="matchForm.evaluateName==1" key="" placeholder="请输入其他内容"></a-input>
                    </a-form-model-item>
                    <a-form-model-item :wrapper-col="{ span: 8, offset: 4 }">
                        <a-button type="primary" @click="onSubmit">
                            一键匹配
                        </a-button>
                    </a-form-model-item>
            </a-form-model>
        </a-card>
    </div>
    <Login v-show="isLogin" @do-login="doLogin" @to-reg="reg" @close="close" @to-forget="toForget" :isForm="true"></Login>
    <Register v-show="isReg" @do-reg="doReg" @to-login="login" @close="close" :isForm="true"></Register>
    <Forget v-show="isForget" @do-forget="doforget" @to-login="login" @close="close" :isForm="true"></Forget>
    <Footer></Footer>
  </div>
</template>

<script>
import { getSearchField,mapData } from "@/common/commapi.js"
import { matchSearchData} from "@/common/lib/tools.js"
export default {
    data(){
        return {
            isLogin:false,
            isReg:false,
            isForget:false,
            active:0,
            baseForm:{
                username:'',
                mobile:'',
                registerAddress:'',
                workAddress:'',
                desc:''
            },
            baseRules:{
                mobile:[
                    {required:true,message:'请输入联系电话',trigger:'blur'}
                ],
                username:[{required:true,message:'请输入联系人',trigger:'blur'}],
                registerAddress:[{required:true,message:'请选择注册地址',trigger:'blur'}],
                workAddress:[{required:true,message:'请输入办公地址',trigger:'blur'}],
                desc:[{required:true,message:'请输入公司简介',trigger:'blur'}],
            },
            matchForm:{
                financeQuota:"",
                financeState:"",
                IndustryDirect:"",
                registerAddress:"",
                business:"",
                staffCount:"",
                marketOccupyRate:"",
                evaluateName:"",
                mechanismOrProduct:"",
                productState:"",
                productRate:"",
                experience:"",
                patentCount:"",
                shareholder:"",
                capitals:"",
                advantage:"",
                oldFinanceQuota:"",
                netInterestRate:"",
                targetCustomer:"",
                companyStatus:"",
                marketCapacity:"",
                marketAddRate:"",
                boolBuyBack:"",
                timeToMarket:"",
                businessAddRate:""
            },
            rules:{
                mobile:[
                    {required:true,message:'请输入联系电话',trigger:'blur'}
                ]
            },
            options:['成本优势','渠道优势','先发优势','资质优势'],
            searchFieldList:[]
        }
    },
    created(){
        getSearchField(this.$http,'/finance/sysCode/getSysCode',{codeType:''}).then(res=>{
            this.searchFieldList = matchSearchData(res);
        }).catch(err=>console.log(err));
    },
    methods:{
        tabchange(index){
            this.active = index;
        },
        login(){
            this.isLogin = true;
            this.isReg = false;
            this.isForget = false;
        },
        doLogin(params){
            console.log(params)
            if(params && params.mobile){
                console.log(params)
                this.isLogin = false;
            }
        },
        reg(){
            this.isReg = true;
            this.isLogin = false;
            this.isForget = false;
        },
        doReg(params){
            if(params && params.mobile){
            console.log(params)
            this.isReg = false;
            }
        },
        close(){
            this.isLogin = false;
            this.isReg = false;
            this.isForget = false;
        },
        toForget(){
            this.isLogin=false;
            this.isForget=true;
        },
        doforget(params){
            console.log(params)
            if(params && params.mobile){
                this.isForget = false;
            }
        },
        getFieldList(codeType){
            // let fieldItem = this.searchFieldList.filter(item=> item.codeType==codeType);
            for(let item in this.searchFieldList){
                console.log(codeType,Object.keys(item));
                if(item.codeType==codeType){
                    return item['sysCodeValueVos'];
                }
                return [];
            }
        },
        onChange(){

        },
        onSubmit(){
            this.goMatch();
        },
        goMatch(){
            // /finance/financeProduct/getOneKeyMatching
            this.$http.post('/finance/financeProduct/getOneKeyMatching',this.params).then(res=>{
                if(res.data.code==0){

                }else{
                    this.$message.error(res.data.msg);
                }
            })
        }
    },
    components:{
        Header:()=>{return import('@/components/Header.vue')},
        Footer:()=>import("@/components/Footer.vue"),
        Login:()=>import("@/components/Login.vue"),
        Register:()=>import("@/components/Register.vue"),
        Forget:()=>import("@/components/Forget.vue"),
    }
}
</script>
<style lang="scss" scoped>
$minHeight:100vw;
.match-box{
    background:#f6f6f6;
    .match-body{
        padding:1vw 5vw;
        min-height:calc(100vh - 331px);
        .tab{
            height:3vw;
            background:#FFF;
            border-radius:0.3vw;
            display:flex;
            justify-content:flex-start;
            align-items:center;
            font-size:1vw;
            font-family: PingFangSC-Regular;
            color:#151515;
            text-align:center;
            position: relative;
            &::before{
                content:'';
                width:0vw;
                height:0vw;
                border-width:1.5vw;
                border-style:solid;
                border-color:#FFF transparent transparent;
                position: absolute;
                bottom: -2.8vw;
                left: 6vw;  
            }
            div{
                flex:1;
                position: relative;
                cursor: pointer;
            }
            .active{
                &::after{
                    content:'';
                    width:3vw;
                    height:0.2vw;
                    background: #5581e3;
                    position: absolute;
                    bottom:-0.6vw;
                    left:50%;
                    transform:translateX(-50%);
                }
            }
        }
        .tis{
            margin-top: 1.8vw;
            color: red;
            font-size: 1vw;
        }
        .card{
            /deep/ .ant-card-head{border-bottom:none;}
        }
        .card-work{
            /deep/ .ant-card-head{border-bottom:none;}
            margin-top:1vw;
            /deep/ .ant-form-item-children{
                display:flex;
                justify-content:flex-start;
                align-items:center;
                .ant-select{
                    width:50%;
                }
                input{
                    width:50%;
                    margin-left:0.3vw;
                }
            }
        }
    }
}
</style>