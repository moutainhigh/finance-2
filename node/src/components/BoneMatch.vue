<template>
  <div>
        <a-card title="业务信息" class="card-work">
            <a-form-model ref="matchForm" :model="matchForm" :rules="rules" :label-col="{span:4}" :wrapper-col="{span:12}">
                    <a-form-model-item ref="loanTerm" label="贷款期限" prop="loanTerm" class="input-item" >
                        <a-select v-model="matchForm.loanTerm"  @blur="
                            () => {
                                $refs.loanTerm.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('DKQX')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="loanQuota" label="贷款额度" prop="loanQuota" class="input-item">
                        <a-select v-model="matchForm.loanQuota"  @blur="
                            () => {
                                $refs.loanQuota.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('DKED')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="industryDirect" label="行业方向" prop="industryDirect" class="input-item">
                        <a-select v-model="matchForm.industryDirect" @blur="
                            () => {
                                $refs.industryDirect.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('BOND_HYFX')">{{item.value}}</a-select-option>
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
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('BOND_GDBJ')">{{item.value}}</a-select-option>
                        </a-select>
                        <a-input v-show="matchForm.shareholder==8" placeholder="请输入其他的内容" v-model="shareholder" @blur=" 
                            () => {
                                $refs.shareholder.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="creditType" label="增信方式" prop="creditType" class="input-item">
                        <a-select v-model="matchForm.creditType" @blur="
                            () => {
                                $refs.creditType.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('ZXFS')">{{item.value}}</a-select-option>
                        </a-select>
                        <a-input v-show="matchForm.creditType==7" placeholder="请输入其他的内容" v-model="creditType" @blur=" 
                            () => {
                                $refs.creditType.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="houseMortgage" label="是否接受房产抵押" prop="houseMortgage" class="input-item">
                        <a-select v-model="matchForm.houseMortgage" @blur="
                            () => {
                                $refs.houseMortgage.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('FCDY')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="business" label="营业收入" prop="business" class="input-item">
                        <a-select v-model="matchForm.business" @blur="
                            () => {
                                $refs.business.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('BOND_YYSR')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="jlr" label="净利润" prop="jlr" class="input-item">
                        <a-select v-model="matchForm.jlr" @blur="
                            () => {
                                $refs.jlr.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('BOND_JLR')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="cashFlow" label="上年度经营活动现金流净额" prop="cashFlow" class="input-item">
                        <a-select v-model="matchForm.cashFlow" @blur="
                            () => {
                                $refs.cashFlow.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('XJLJE')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="goverOrderAmount" label="政府订单额" prop="goverOrderAmount" class="input-item">
                        <a-select v-model="matchForm.goverOrderAmount" @blur="
                            () => {
                                $refs.goverOrderAmount.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('ZFDDE')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="nationOrderAmount" label="央企/大型国企核心供应商订单额" prop="nationOrderAmount" class="input-item">
                        <a-select v-model="matchForm.nationOrderAmount" @blur="
                            () => {
                                $refs.nationOrderAmount.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('GQDDE')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="assetAmount" label="资产总额（万元）" prop="assetAmount" class="input-item">
                        <a-input v-model="matchForm.assetAmount" placeholder="请输入资产总额（万元）" @blur="
                            () => {
                                $refs.assetAmount.onFieldBlur();
                            }
                            " />
                    </a-form-model-item>
                    <a-form-model-item ref="liabilitiesAmount" label="负债总额（万元）" prop="liabilitiesAmount" class="input-item">
                        <a-input v-model="matchForm.liabilitiesAmount" placeholder="请输入资产总额（万元）" @blur="
                            () => {
                                $refs.liabilitiesAmount.onFieldBlur();
                            }
                            " />
                    </a-form-model-item>
                    <a-form-model-item ref="owner" label="所有者权益（万元）" prop="owner" class="input-item">
                        <a-input v-model="matchForm.owner" placeholder="请输入所有者权益" @blur="
                            () => {
                                $refs.owner.onFieldBlur();
                            }
                            " />
                    </a-form-model-item>
                    <a-form-model-item ref="qualification" label="企业资质" prop="qualification" class="input-item">
                        <a-select v-model="matchForm.qualification" @blur="
                            () => {
                                $refs.qualification.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('QYZZ')">{{item.value}}</a-select-option>
                        </a-select>
                        <a-input v-show="matchForm.qualification==5" placeholder="请输入其他的内容" v-model="qualification" @blur=" 
                            () => {
                                $refs.qualification.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="subsidy" label="政府补贴" prop="subsidy" class="input-item">
                        <a-select v-model="matchForm.subsidy" @blur="
                            () => {
                                $refs.subsidy.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('ZFBT')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="boolIntroduce" label="是否已引入股权投资" prop="boolIntroduce" class="input-item">
                        <a-select v-model="matchForm.boolIntroduce" @blur="
                            () => {
                                $refs.boolIntroduce.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('GQTZ')">{{item.value}}</a-select-option>
                          <!-- <a-input v-show="matchForm.targetCustomer==4" placeholder="请输入其他的内容" v-model="targetCustomer" @blur=" 
                                () => {
                                    $refs.targetCustomer.onFieldBlur();
                                }
                                "
                            /> -->
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="taxAmount" label="纳税额度" prop="taxAmount" class="input-item">
                        <a-select v-model="matchForm.taxAmount" @blur="
                            () => {
                                $refs.taxAmount.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('NRED')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="patentCount" label="发明专利数量" prop="patentCount" class="input-item">
                        <a-select v-model="matchForm.patentCount" @blur="
                            () => {
                                $refs.patentCount.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('BOND_FMZLS')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="boolLoan" label="是否有其他贷款" prop="boolLoan" class="input-item">
                        <a-select v-model="matchForm.boolLoan" @blur="
                            () => {
                                $refs.boolLoan.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('QTDK')">{{item.value}}</a-select-option>
                        </a-select>
                        <a-input v-show="matchForm.boolLoan==4" placeholder="请输入其他的内容" v-model="boolLoan" @blur=" 
                            () => {
                                $refs.boolLoan.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="existAmount" label="现有贷款金额" prop="existAmount" class="input-item">
                        <a-select v-model="matchForm.existAmount" @blur="
                            () => {
                                $refs.existAmount.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('DKJE')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item :wrapper-col="{ span: 8, offset: 4 }">
                        <a-button type="primary" @click="onSubmit" v-if="$store.state.token">
                            一键匹配
                        </a-button>
                        <a-button type="default" @click="$emit('dologin',true)" v-if="!$store.state.token">
                            一键匹配
                        </a-button>
                    </a-form-model-item>
            </a-form-model>
        </a-card>
  </div>
</template>

<script>
import { getSearchField,mapData } from "@/common/commapi.js"
export default {
    name:'BoneMatch',
    data(){
        return {
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
                industryDirect:'',
                registerAddress:'',
                patentCount:'',
                shareholder:'',
                loanTerm:'',
                loanQuota:'',
                business:'',
                creditType:'',
                houseMortgage:'',
                cashFlow:'',
                goverOrderAmount:'',
                nationOrderAmount:'',
                termLoan:'',
                assetAmount:'',
                liabilitiesAmount:'',
                owner:'',
                qualification:'',
                subsidy:'',
                boolIntroduce:'',
                taxAmount:'',
                boolLoan:'',
                existAmount:'',
                jlr:''
            },
            rules:{
                industryDirect:[{required:true,message:'请选择行业方向',trigger:'blur'}],
                registerAddress:[{required:true,message:'请选择注册地址',trigger:'blur'}],
                patentCount:[{required:true,message:'请选择专利发明数量',trigger:'blur'}],
                shareholder:[{required:true,message:'请选择股东背景',trigger:'blur'}],
                loanTerm:[{required:true,message:'请选择贷款期限',trigger:'blur'}],
                loanQuota:[{required:true,message:'请选择贷款额度',trigger:'blur'}],
                business:[{required:true,message:'请选择营业收入',trigger:'blur'}],
                creditType:[{required:true,message:'请选择增信方式',trigger:'blur'}],
                houseMortgage:[{required:true,message:'请选择房产抵押',trigger:'blur'}],
                cashFlow:[{required:true,message:'请选择现金流',trigger:'blur'}],
                goverOrderAmount:[{required:true,message:'请选择政府订单额',trigger:'blur'}],
                nationOrderAmount:[{required:true,message:'请选择国企订单额',trigger:'blur'}],
                termLoan:[{required:true,message:'请选择贷款期限',trigger:'blur'}],
                assetAmount:[{required:true,message:'请选择资产总额',trigger:'blur'}],
                liabilitiesAmount:[{required:true,message:'请选择负债总额',trigger:'blur'}],
                owner:[{required:true,message:'请选择所有者权益',trigger:'blur'}],
                qualification:[{required:true,message:'请选择企业资质',trigger:'blur'}],
                subsidy:[{required:true,message:'请选择政府补贴',trigger:'blur'}],
                boolIntroduce:[{required:true,message:'请选择是否引入股权投资',trigger:'blur'}],
                taxAmount:[{required:true,message:'请选择纳税额度',trigger:'blur'}],
                boolLoan:[{required:true,message:'请选择是否有其他贷款',trigger:'blur'}],
                existAmount:[{required:true,message:'请选择现有贷款金额',trigger:'blur'}],
                jlr:[{required:true,message:'请选择净利率',trigger:'blur'}]
            },
            searchFieldList:[],
            isCheckCb:false,
            isCheckJs:false,
            advantage:[],
            creditType:'',//增信方式其他
            qualification:'',//企业资质其他
            industryDirect:'',//行业方向其他
            shareholder:'',//股东背景其他
            boolLoan:'',//是否有其他贷款其他

        }
    },
    created(){
        getSearchField(this.$http,'/finance/sysCode/getQuerySysCode',{financeType:1}).then(res=>{
            this.searchFieldList = res;//matchSearchData(res);
        }).catch(err=>console.log(err));
    },
    methods:{
        getFieldList(codeType){
            for(let item of this.searchFieldList){
                // console.log(item.codeType,codeType);
                if(item.codeType==codeType){
                    return item.queryDetailSysCodeVos;
                }
            }
            return [];
        },
        getChildSysCodes(itemList,value){
            for(let item of itemList){
                // console.log(item.codeType,codeType);
                if(item.value==value){
                    return item.childSysCodes;
                }
            }
            return [];
        },
        onChange(){

        },
        onSubmit(){
            this.$refs.matchForm.validate(valid => {
                if (valid) {
                    this.goMatch();
                } else {
                   this.$emit('do-sub');;
                }
            });
           
            
        },
        goMatch(){
            let params = Object.assign({},this.matchForm);
            

            let creditType = {code:this.matchForm.creditType,value:this.creditType}
            params.creditType=creditType;

            let qualification = {code:this.matchForm.qualification,value:this.qualification}
            params.qualification=qualification;

            let industryDirect = {code:this.matchForm.industryDirect,value:this.industryDirect}
            params.industryDirect=industryDirect;

            let shareholder = {code:this.matchForm.shareholder,value:this.shareholder}
            params.shareholder=shareholder;

            let boolLoan = {code:this.matchForm.boolLoan,value:this.boolLoan}
            params.boolLoan=boolLoan;


            console.log(params)

            this.$emit('do-sub',params);

            // // /finance/financeProduct/getOneKeyMatching
            // this.$http.post('/finance/financeProduct/getOneKeyMatching',params).then(res=>{
            //     console.log(res)
            //     if(res.data.code==0){

            //     }else{
            //         this.$message.error(res.data.msg);
            //     }
            // }).catch(err=>console.log(err))
        }
    }
}
</script>
<style lang="scss" scoped>
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
</style>