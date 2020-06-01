<template>
  <div class="match-box">
    <Header @do-login="login" @to-reg="reg" :isBg="true"></Header>
    <div class="match-body">
        <div class="match-top" style="position:sticky;top:0px;z-index:1;">
            <!-- <div class="back">返回</div> -->
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
                          <!-- <a-select-option key="" value="test">不限</a-select-option> -->
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
        <a-card title="业务信息" class="card-work" v-if="active==0">
            <a-form-model ref="matchForm" :model="matchForm" :rules="rules" :label-col="{span:4}" :wrapper-col="{span:12}">
                    <a-form-model-item ref="financeState" label="融资阶段" prop="financeState" class="input-item" >
                        <a-select v-model="matchForm.financeState"  @blur="
                            () => {
                                $refs.financeState.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('RZJD')">{{item.value}}</a-select-option>
                        </a-select>
                        <a-input v-show="matchForm.financeState==5" placeholder="请输入其他的内容" v-model="financeState" @blur=" 
                            () => {
                                $refs.financeState.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="financeQuota" label="融资额度" prop="financeQuota" class="input-item">
                        <a-select v-model="matchForm.financeQuota"  @blur="
                            () => {
                                $refs.financeQuota.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('RZED')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="industryDirect" label="行业方向" prop="industryDirect" class="input-item">
                        <a-select v-model="matchForm.industryDirect" @blur="
                            () => {
                                $refs.industryDirect.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('HYFX')">{{item.value}}</a-select-option>
                        </a-select>
                        <a-input v-show="matchForm.industryDirect==8" placeholder="请输入其他的内容" v-model="industryDirect" @blur=" 
                            () => {
                                $refs.industryDirect.onFieldBlur();
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
                        <a-input v-show="matchForm.shareholder==8" placeholder="请输入其他的内容" v-model="shareholder" @blur=" 
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
                        <a-input v-show="matchForm.productState==5" placeholder="请输入其他的内容" v-model="productState" @blur=" 
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
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('STOCKRIGHT_YYSR')">{{item.value}}</a-select-option>
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
                                $refs.matchForm.clearValidate('companyStatus');
                                $refs.experience.onFieldBlur();
                            }
                            ">
                            <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('CYJL')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="companyStatus" label="历史创业企业状态" prop="companyStatus" :required="matchForm.experience!=0" class="input-item">
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
                        <!-- <a-select v-model="matchForm.targetCustomer" @blur="
                            () => {
                                $refs.targetCustomer.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('MBKH')">{{item.value}}</a-select-option>
                          <a-input v-show="matchForm.targetCustomer==4" placeholder="请输入其他的内容" v-model="targetCustomer" @blur=" 
                                () => {
                                    $refs.targetCustomer.onFieldBlur();
                                }
                                "
                            />
                        </a-select> -->
                        <a-checkbox-group :options="getFieldList('MBKH').map((item)=>{
                            return {label:item.value,value:item.code};
                        })" :default-value="[]" @change="onChange" v-model="targetCustomers">
                            <span slot="label" slot-scope="{ code }">{{ value }}</span>
                        </a-checkbox-group>
                        <a-input v-show="targetCustomers.indexOf('4')!=-1" style="width:200px;margin-left:15px;" placeholder="请输入其他内容" v-model="targetCustomer"></a-input>
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
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('STOCKRIGHT_FMZLSL')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="advantage" label="公司竞争优势" prop="advantage" class="input-item" :wrapper-col="{ span: 18, offset: 0 }">
                        <a-checkbox @change="onChange" v-model="isCheckCb">成本优势</a-checkbox>
                        <a-select v-model="cbys" style="width:10vw;" v-if="isCheckCb" @blur="
                            () => {
                                $refs.advantage.onFieldBlur();
                            }
                            ">
                            <template v-for="item in getFieldList('GSJZYS')">
                            <a-select-option v-if="item.code==0" :key="sitem.code" :value="sitem.code" v-for="(sitem) in item.childSysCodes">{{sitem.value}}</a-select-option>
                            </template>
                        </a-select>
                        <a-checkbox @change="onChange" style="margin-left:0.3vw;" v-model="isCheckJs" >技术优势</a-checkbox>
                        <a-select v-model="jsys" style="width:10vw;" v-if="isCheckJs" @blur="
                            () => {
                                $refs.advantage.onFieldBlur();
                            }
                            ">
                            <template v-for="item in getFieldList('GSJZYS')">
                            <a-select-option v-if="item.code==1" :key="sitem.code" :value="sitem.code" v-for="(sitem) in item.childSysCodes">{{sitem.value}}</a-select-option>
                            </template>
                        </a-select>
                        <a-checkbox-group :options="['渠道优势','先发优势','资质优势','其他']" style="margin-left:0.3vw;" v-model="advantage"></a-checkbox-group>
                        <a-input v-show="advantage.indexOf('其他')!=-1" style="width:8vw;margin-left:0.3vw;" v-model="advantageOther"></a-input>
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
                    </a-form-model-item>
                    <a-form-model-item ref="evaluateName" label="公司所获评定称号" prop="evaluateName" class="input-item" :wrapper-col="{ span: 20 }">
                        <!-- <a-select v-model="matchForm.evaluateName" @blur="
                            () => {
                                $refs.evaluateName.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('PDCH')">{{item.value}}</a-select-option>
                        </a-select> -->
                        <a-checkbox-group :options="getFieldList('PDCH').map((item)=>{
                            return {label:item.value,value:item.code};
                        })" :default-value="[]" @change="onChange" v-model="evaluateNames">
                            <span slot="label" slot-scope="{ code }">{{ value }}</span>
                        </a-checkbox-group>
                        <a-input v-show="evaluateNames.indexOf('4')!=-1" style="width:200px;margin-left:15px;" placeholder="请输入其他内容" v-model="evaluateName"></a-input>
                    </a-form-model-item>
                    <a-form-model-item :wrapper-col="{ span: 8, offset: 4 }">
                        <a-spin :spinning="spinning">
                        <a-button type="primary" @click="onSubmit" v-if="$store.state.token">
                            一键匹配
                        </a-button>
                        <a-button type="default" @click="isLogin=true" v-if="!$store.state.token">
                            一键匹配
                        </a-button>
                        </a-spin>
                    </a-form-model-item>
            </a-form-model>
        </a-card>
        <BoneMatch v-if="active==1" @do-sub="onSubmit" @dologin="isLogin=true"></BoneMatch>
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
import BoneMatch from "@/components/BoneMatch.vue"
import lodash from 'lodash'
export default {
    data(){
        let validateMobile = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入联系电话'));
            } else if (!/^1[3|4|5|6|7|8|9][0-9]{9}/.test(value)) {
                callback(new Error("请输入正确的联系电话"));
            } else{
                callback();
            }
        };
        let validateStatus = (rule,value,callback)=>{
            if (this.matchForm.experience != 0 && value==='') {
                callback(new Error('请选择历史创业企业状态'));
            } else{
                callback();
            }
        }
        return {
            spinning:false,
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
                    {required:true,message:'请输入联系电话',trigger:'blur'},
                    { validator: validateMobile, trigger: 'blur' },
                ],
                username:[{required:true,message:'请输入联系人',trigger:'blur'}],
                registerAddress:[{required:true,message:'请选择注册地址',trigger:'blur'}],
                workAddress:[{required:true,message:'请输入办公地址',trigger:'blur'}],
                desc:[{required:true,message:'请输入公司简介',trigger:'blur'}],
            },
            matchForm:{
                financeQuota:"",
                financeState:"",
                industryDirect:"",
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
                financeQuota:[{required:true,message:'请选择融资额度',trigger:'blur'}],
                financeState:[{required:true,message:'请选择融资阶段',trigger:'blur'}],
                industryDirect:[{required:true,message:'请选择行业方向',trigger:'blur'}],
                registerAddress:[{required:true,message:'请选择注册地址',trigger:'blur'}],
                business:[{required:true,message:'请选择营业收入',trigger:'blur'}],
                staffCount:[{required:true,message:'请选择员工人数',trigger:'blur'}],
                // marketOccupyRate:[{required:true,message:'请选择预期市场占有率',trigger:'blur'}],
                // evaluateName:[{required:true,message:'请选择公司所获评定称号',trigger:'blur'}],
                mechanismOrProduct:[{required:true,message:'请选择产品或机构名称',trigger:'blur'}],
                productState:[{required:true,message:'请选择产品阶段',trigger:'blur'}],
                // productRate:[{required:true,message:'请选择产品毛利率',trigger:'blur'}],
                // experience:[{required:true,message:'请选择实际控制人创业经历',trigger:'blur'}],
                patentCount:[{required:true,message:'请选择专利发明数量',trigger:'blur'}],
                shareholder:[{required:true,message:'请选择股东背景',trigger:'blur'}],
                capitals:[{required:true,message:'请选择股东累计投入资金',trigger:'blur'}],
                advantage:[{required:true,message:'请选择公司竞争优势',trigger:'blur'}],
                oldFinanceQuota:[{required:true,message:'请选择过往融资金额',trigger:'blur'}],
                netInterestRate:[{required:true,message:'请选择净利率',trigger:'blur'}],
                targetCustomer:[{required:true,message:'请选择目标客户',trigger:'blur'}],
                // companyStatus:[{required:true,message:'请选择历史创业企业状态',trigger:'blur'}],
                companyStatus:[{validator:validateStatus,trigger:'blur'}],
                // marketCapacity:[{required:true,message:'请选择市场容量',trigger:'blur'}],
                // marketAddRate:[{required:true,message:'请选择市场容量预期增长率',trigger:'blur'}],
                boolBuyBack:[{required:true,message:'请选择是否接受回购条款',trigger:'blur'}],
                // timeToMarket:[{required:true,message:'请选择预计上市时间',trigger:'blur'}],
                // businessAddRate:[{required:true,message:'请选择营业收入增长率',trigger:'blur'}]
            },
            options:['成本优势','渠道优势','先发优势','资质优势'],
            searchFieldList:[],
            isCheckCb:false,
            isCheckJs:false,
            advantage:[],
            advantageOther:'',//公司竞争优势其他
            financeState:'',//融资阶段其他
            industryDirect:'',//行业方向其他
            shareholder:'',//股东背景其他
            productState:'',//产品阶段其他
            targetCustomer:'',//目标客户其他
            targetCustomers:[],//目标客户复选
            evaluateName:'',//预计上市时间其他
            cbys:'',//成本优势下拉
            jsys:'',//技术优势下拉,
            evaluateNames:[],//公司称号多选

        }
    },
    created(){
        if(!this.$store.state.token){
            this.$router.push({name:'Home',params:{islogin:1}});
            return ;
        }
        getSearchField(this.$http,'/finance/sysCode/getQuerySysCode',{financeType:0}).then(res=>{
            this.searchFieldList = res;//matchSearchData(res);
        }).catch(err=>console.log(err));
    },
    watch:{
        advantage:function(v,o){
            let map = {'渠道优势':2,'先发优势':3,'资质优势':4,'其他':5}

            if(v.length && !this.matchForm.advantage){
                let items = [];
                v.forEach((item)=>{
                    let obj = {code:map[item],value:''};
                    if(obj.code==5){
                        obj.value = this.advantageOther;
                    }
                    items.push(obj);
                })
                this.matchForm.advantage = items;
            }else if(v.length && this.matchForm.advantage){
                let advantage = JSON.stringify(this.matchForm.advantage);
                advantage = JSON.parse(advantage);
                lodash.remove(advantage,aitem=>[2,3,4,5].some(sitem=>sitem==aitem.code));
                this.$set(this.matchForm,'advantage',advantage);
                console.log(advantage)
                v.forEach((item)=>{
                    let obj = {code:map[item],value:''};
                    if(obj.code==5){
                        obj.value = this.advantageOther;
                    }
                    this.matchForm.advantage.push(obj)
                })
            }else if(!v.length && this.matchForm.advantage){
                let advantage = JSON.stringify(this.matchForm.advantage);
                advantage = JSON.parse(advantage);
                lodash.remove(advantage,aitem=> lodash.findIndex([2,3,4,5],sItem=>sItem==aitem.code));
                this.$set(this.matchForm,'advantage',advantage);
            }
        },
        cbys:function(v,o){
            if(v){
                let obj = {code:0,value:v};
                if(this.matchForm.advantage){
                    let index = lodash.findIndex(this.matchForm.advantage,item=>item.code==0);
                    if(index!=-1){
                        this.$set(this.matchForm.advantage,index,obj);
                    }else{
                        this.matchForm.advantage.push(obj);
                    }
                    // let ishas = this.matchForm.advantage.some(item=>item.code==0);
                    // this.matchForm.advantage.forEach(item=>{
                    //     if(item.code==1){
                    //         item.value=v;
                    //     }else{
                    //         if(!ishas){
                    //             this.matchForm.advantage.push(obj);
                    //         }
                    //     }
                    // })
                }else{
                    this.matchForm.advantage = [];
                    this.matchForm.advantage.push(obj);
                }
            }
        },
        jsys:function(v,o){
            if(v){
                let obj = {code:1,value:v};
                if(this.matchForm.advantage){
                    let index = lodash.findIndex(this.matchForm.advantage,item=>item.code==1);
                    if(index!=-1){
                        this.$set(this.matchForm.advantage,index,obj);
                    }else{
                        this.matchForm.advantage.push(obj);
                    }
                    // let ishas = this.matchForm.advantage.some(item=>item.code==1);
                    // this.matchForm.advantage.forEach(item=>{
                    //     if(item.code==1){
                    //         item.value=v;
                    //     }else{
                    //         if(!ishas){
                    //             this.matchForm.advantage.push(obj);
                    //         }
                    //     }
                    // })
                }else{
                    this.matchForm.advantage = [];
                    this.matchForm.advantage.push(obj);
                }
            }
        },
        isCheckCb:function(v,o){
            if(!v){
                let adv = JSON.stringify(this.matchForm.advantage);
                try{
                    adv = JSON.parse(adv); 
                    adv.forEach((item,index)=>{
                        if(item.code==0){
                            this.cbys = '';
                            this.matchForm.advantage.splice(index,1);
                        }
                    })
                }catch(err){}
            }
            (this.isCheckCb==false && this.isCheckJs==false) ? this.$refs.matchForm.clearValidate('advantage'):'';
        },
        isCheckJs:function(v,o){
            if(!v){
                let adv = JSON.stringify(this.matchForm.advantage);
                try {
                    adv = JSON.parse(adv);
                    adv.forEach((item,index)=>{
                        if(item.code==1){
                            this.jsys = '';
                            this.matchForm.advantage.splice(index,1);
                        }
                    })
                } catch (error) {
                    
                }
            }
            (this.isCheckCb==false && this.isCheckJs==false) ? this.$refs.matchForm.clearValidate('advantage'):'';
        },
        evaluateNames:function(v,o){
            this.matchForm.evaluateName = [];
            if(v.length>0){
                let items = [];
                v.forEach(item=>{
                    let obj = {code:Number(item),value:''};
                    if(Number(item)==4){
                        obj.value=this.evaluateName;
                    }
                    items.push(obj);
                });
                this.$set(this.matchForm,'evaluateName',items);
            }else{
                this.evaluateName="";
            }
        },
        evaluateName:function(v,o){
            if(v){
                this.matchForm.evaluateName.forEach(item=>{
                    if(item.code==4){
                        item.value = v;
                    }
                })
            }
        },
        targetCustomers:function(v,o){
            this.matchForm.targetCustomer = [];
            if(v.length>0){
                let items = [];
                v.forEach(item=>{
                    let obj = {code:Number(item),value:''};
                    if(Number(item)==4){
                        obj.value=this.targetCustomer;
                    }
                    items.push(obj);
                });
                this.$set(this.matchForm,'targetCustomer',items);
            }else{
                this.targetCustomer="";
            }
        },
        targetCustomer:function(v,o){
            if(v){
                this.matchForm.targetCustomer.forEach(item=>{
                    if(item.code==4){
                        item.value = v;
                    }
                })
            }
        }
    },
    methods:{
        tabchange(index){
            this.active = index;
            this.$refs.baseForm.clearValidate();
        },
        login(){
            this.isLogin = true;
            this.isReg = false;
            this.isForget = false;
        },
        doLogin(params){
            if(params && params.mobile){
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
            if(params && params.mobile){
                this.isForget = false;
            }
        },
        getFieldList(codeType){
            for(let item of this.searchFieldList){
                if(item.codeType==codeType){
                    return item.queryDetailSysCodeVos;
                }
            }
            return [];
        },
        getChildSysCodes(itemList,value){
            for(let item of itemList){
                if(item.value==value){
                    return item.childSysCodes;
                }
            }
            return [];
        },
        onChange(){

        },
        onSubmit(param){
            
            // new Promise((resolve,reject)=>{
            //      this.$refs.baseForm.validate(valid => {
            //         if (valid) {
            //             resolve()
            //         } else {
            //             setTimeout(()=>{
            //                 this.$refs.baseForm.clearValidate();
            //             },1000)
            //             return false;
            //         }
            //     });
            // }).then((res)=>{
            //     if(this.active==0){
            //         this.$refs.matchForm.validate(valid => {
            //              if (valid) {
            //                  console.log(valid)
            //                  this.goMatch();
            //              } else {
            //                  setTimeout(()=>{
            //                     this.$refs.matchForm.clearValidate();
            //                 },1000)
            //                  return false;
            //              }
            //          }); 
            //     }else{
            //         this.doSub(param)
            //     }
            // })
            let baseFormPromise = new Promise((resolve,reject)=>{
                this.$refs.baseForm.validate(valid => {
                    if (valid) {
                        resolve();
                    } else {
                        reject();
                    }
                });
            })
            let matchFormPromise = new Promise((resolve,reject)=>{
                if(this.active==0){
                    this.$refs.matchForm.validate(valid => {
                        if (valid) {
                            resolve();
                        } else {
                            reject();
                        }
                    });
                }else{
                    resolve();
                }
            })
            Promise.all([baseFormPromise,matchFormPromise]).then(res=>{
                if(this.active==0){
                    this.goMatch();
                }else{
                    if(param){
                        this.doSub(param)
                    }
                }
            }).catch(err=>console.log(err))
           
            
        },
        doSub(param){//债券一键匹配
            let params = Object.assign({},this.baseForm,param);
            
            let registerAddress = {code:this.baseForm.registerAddress,value:''}
            params.registerAddress=registerAddress;

            this.spinning=true
            this.$http.postWithAuth('/finance/financeBondDetail/getBondOneKeyMatching',params).then(res=>{
                console.log(res)
                this.spinning=false;
                if(res.data.code==0){
                    this.$message.success('匹配成功，跳转中');
                    localStorage.setItem('zids',res.data.content);
                    this.$router.push({name:'Zhaiquan'})
                }else{
                    this.$message.error(res.data.msg);
                }
            }).catch(err=>console.log(err))
        },
        goMatch(){//股权一键匹配
            let params = Object.assign({},this.baseForm,this.matchForm);
        
            let financeState = {code:this.matchForm.financeState,value:this.financeState}
            params.financeState=financeState;

            let industryDirect = {code:this.matchForm.industryDirect,value:this.industryDirect}
            params.industryDirect=industryDirect;

            let shareholder = {code:this.matchForm.shareholder,value:this.shareholder}
            params.shareholder=shareholder;

            let productState = {code:this.matchForm.productState,value:this.productState}
            params.productState=productState;

            // let targetCustomer = {code:this.matchForm.targetCustomer,value:this.targetCustomer}
            // params.targetCustomer=targetCustomer;

            // let evaluateName = {code:this.matchForm.evaluateName,value:this.evaluateName}
            // params.evaluateName=evaluateName;
            let registerAddress = {code:this.baseForm.registerAddress,value:''}
            params.registerAddress=registerAddress;

            // let map = {'渠道优势':2,'先发优势':3,'资质优势':4,'其他':5}
            // if(this.advantage){
            //     let advantageArr = [];
            //     this.advantage.forEach(item=>{
            //         let obj = {code:map[item],value:''};
            //         if(obj.code==5){
            //             obj.value=this.advantageOther;
            //         }
            //         advantageArr.push(obj);
            //     });
            //     if(params.advantage){
            //         params.advantage = params.advantage.concat(advantageArr);
            //     }else{
            //         params.advantage = advantageArr;
            //     }
            // }

            // /finance/financeProduct/getOneKeyMatching
            this.spinning=true
            this.$http.postWithAuth('/finance/financeStockDetail/getStockOneKeyMatching',params).then(res=>{
                console.log(res)
                this.spinning=false
                if(res.data.code==0){
                    this.$message.success('匹配成功，跳转中');
                    localStorage.setItem('ids',res.data.content);
                    this.$router.push({name:'Guquan'})
                }else{
                    this.$message.error(res.data.msg);
                }
            }).catch(err=>console.log(err))
        }
    },
    components:{
        Header:()=>{return import('@/components/Header.vue')},
        Footer:()=>import("@/components/Footer.vue"),
        Login:()=>import("@/components/Login.vue"),
        Register:()=>import("@/components/Register.vue"),
        Forget:()=>import("@/components/Forget.vue"),
        BoneMatch:BoneMatch,
    }
}
</script>
<style lang="scss" scoped>
$minHeight:100vw;
/deep/ .ant-checkbox-group{margin:9px 0 0;}
/deep/ .ant-form-item label{overflow:hidden;}
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
.ant-form-explain{
    animation: all 1s initial 1s;
}
</style>