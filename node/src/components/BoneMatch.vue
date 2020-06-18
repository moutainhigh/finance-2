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
                    <a-form-model-item ref="industryDirect" label="行业方向" prop="industryDirect" class="input-item" :wrapper-col="{span:20}">
                        <!-- <a-select v-model="matchForm.industryDirect" @blur="
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
                        /> -->
                        <a-checkbox-group :options="getFieldList('BOND_HYFX').map((item)=>{
                            return {label:item.value,value:item.code};
                        })" :default-value="[]" @change="onChange" v-model="industryDirects">
                            <span slot="label" slot-scope="{ code }">{{ value }}</span>
                        </a-checkbox-group>
                        <a-input v-show="industryDirects.indexOf('8')!=-1" style="width:200px;margin-left:15px;" placeholder="请输入其他内容" v-model="industryDirect"></a-input>
                    
                    </a-form-model-item>
                    <a-form-model-item ref="shareholder" label="股东背景" prop="shareholder" class="input-item" :wrapper-col="{span:20}">
                        <!-- <a-select v-model="matchForm.shareholder" @blur="
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
                        /> -->
                        <a-checkbox-group :options="getFieldList('BOND_GDBJ').map((item)=>{
                            return {label:item.value,value:item.code};
                        })" :default-value="[]" @change="onChange" v-model="shareholders">
                            <span slot="label" slot-scope="{ code }">{{ value }}</span>
                        </a-checkbox-group>
                        <a-input v-show="shareholders.indexOf('8')!=-1" style="width:200px;margin-left:15px;" placeholder="请输入其他内容" v-model="shareholder"></a-input>
                    </a-form-model-item>
                    <a-form-model-item ref="creditType" label="增信方式" prop="creditType" class="input-item" :wrapper-col="{span:20}">
                        <!-- <a-select v-model="matchForm.creditType" @blur="
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
                        /> -->
                        <a-checkbox-group :options="getFieldList('ZXFS').map((item)=>{
                            return {label:item.value,value:item.code};
                        })" :default-value="[]" @change="onChange" v-model="creditTypes">
                            <span slot="label" slot-scope="{ code }">{{ value }}</span>
                        </a-checkbox-group>
                        <a-input v-show="creditTypes.indexOf('7')!=-1" style="width:200px;margin-left:15px;" placeholder="请输入其他内容" v-model="creditType"></a-input>
                    
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
                    <a-form-model-item ref="qualification" label="企业资质" prop="qualification" class="input-item" :wrapper-col="{span:20}">
                        <!-- <a-select v-model="matchForm.qualification" @blur="
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
                        /> -->
                        <a-checkbox-group :options="getFieldList('QYZZ').map((item)=>{
                            return {label:item.value,value:item.code};
                        })" :default-value="[]" @change="onChange" v-model="qualifications">
                            <span slot="label" slot-scope="{ code }">{{ value }}</span>
                        </a-checkbox-group>
                        <a-input v-show="qualifications.indexOf('5')!=-1" style="width:200px;margin-left:15px;" placeholder="请输入其他内容" v-model="qualification"></a-input>
                    
                    </a-form-model-item>
                    <a-form-model-item ref="subsidy" label="本年度政府补贴" prop="subsidy" class="input-item">
                        <a-select v-model="matchForm.subsidy" @blur="
                            () => {
                                $refs.subsidy.onFieldBlur();
                            }
                            ">
                          <a-select-option :key="item.code" :value="item.code" v-for="(item,index) in getFieldList('ZFBT')">{{item.value}}</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="lastSubsidy" label="上一年度政府补贴" prop="lastSubsidy" class="input-item">
                        <a-select v-model="matchForm.lastSubsidy" @blur="
                            () => {
                                $refs.lastSubsidy.onFieldBlur();
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
                        <!-- <a-select v-model="matchForm.boolLoan" @blur="
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
                        /> -->
                        <a-checkbox-group :options="getFieldList('QTDK').map((item)=>{
                            return {label:item.value,value:item.code};
                        })" :default-value="[]" @change="onChange" v-model="boolLoans">
                            <span slot="label" slot-scope="{ code }">{{ value }}</span>
                        </a-checkbox-group>
                        <a-input v-show="boolLoans.indexOf('4')!=-1" style="width:200px;margin-left:15px;" placeholder="请输入其他内容" v-model="boolLoan"></a-input>
                    
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
                    <a-form-model-item :wrapper-col="{ span: 8, offset: 4 }" v-if="!$route.params.isSub">
                        <a-spin :spinning="spinning">
                        <a-button type="default" class="save-btn" @click="saveInfo" v-if="$store.state.token">
                            保 存
                        </a-button>
                        <a-button type="primary" @click="onSubmit" v-if="$store.state.token">
                            一键匹配
                        </a-button>
                        <a-button type="default" @click="$emit('dologin',true)" v-if="!$store.state.token">
                            一键匹配
                        </a-button>
                        </a-spin>
                    </a-form-model-item>
                    <a-form-model-item :wrapper-col="{ span: 8, offset: 4 }" v-if="$route.params.isSub">
                        <a-spin :spinning="spinning">
                        <a-button type="primary" @click="onSubmit" v-if="$store.state.token">
                            提 交
                        </a-button>
                        </a-spin>
                    </a-form-model-item>
            </a-form-model>
        </a-card>
  </div>
</template>

<script>
import { getSearchField } from "@/common/commapi.js"
export default {
    name:'BoneMatch',
    props:{
        spinning:{type:Boolean,default:false},
    },
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
                industryDirect:[],
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
                lastSubsidy:'',
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
                subsidy:[{required:true,message:'请选择本年度政府补贴',trigger:'blur'}],
                lastSubsidy:[{required:true,message:'请选择上一年度政府补贴',trigger:'blur'}],
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
            creditTypes:[],//增信方式其他
            qualification:'',//企业资质其他
            qualifications:[],//企业资质其他
            industryDirect:'',//行业方向其他
            industryDirects:[],//行业方向
            shareholder:'',//股东背景其他
            shareholders:[],//股东背景其他
            boolLoan:'',//是否有其他贷款其他
            boolLoans:[],//是否有其他贷款其他
        }
    },
    created(){
        getSearchField(this.$http,'/finance/sysCode/getQuerySysCode',{financeType:1}).then(res=>{
            this.searchFieldList = res;//matchSearchData(res);
        }).catch();
        this.getSaveInfo();
    },
    watch:{
        industryDirects:function(v,o){
            this.matchForm.industryDirect = [];
            if(v.length>0){
                let items = [];
                v.forEach(item=>{
                    let obj = {code:Number(item),value:''};
                    if(Number(item)==8){
                        obj.value=this.industryDirect;
                    }
                    items.push(obj);
                });
                this.$set(this.matchForm,'industryDirect',items);
            }else{
                this.industryDirect="";
            }
        },
        industryDirect:function(v,o){
            if(v){
                this.matchForm.industryDirect.forEach(item=>{
                    if(item.code==8){
                        item.value = v;
                    }
                })
            }
        },
        shareholders:function(v,o){
            this.matchForm.shareholder = [];
            if(v.length>0){
                let items = [];
                v.forEach(item=>{
                    let obj = {code:Number(item),value:''};
                    if(Number(item)==8){
                        obj.value=this.shareholder;
                    }
                    items.push(obj);
                });
                this.$set(this.matchForm,'shareholder',items);
            }else{
                this.shareholder="";
            }
        },
        shareholder:function(v,o){
            if(v){
                this.matchForm.shareholder.forEach(item=>{
                    if(item.code==8){
                        item.value = v;
                    }
                })
            }
        },
        creditTypes:function(v,o){
            this.matchForm.creditType = [];
            if(v.length>0){
                let items = [];
                v.forEach(item=>{
                    let obj = {code:Number(item),value:''};
                    if(Number(item)==7){
                        obj.value=this.creditType;
                    }
                    items.push(obj);
                });
                this.$set(this.matchForm,'creditType',items);
            }else{
                this.creditType="";
            }
        },
        creditType:function(v,o){
            if(v){
                this.matchForm.creditType.forEach(item=>{
                    if(item.code==7){
                        item.value = v;
                    }
                })
            }
        },
        qualifications:function(v,o){
            this.matchForm.qualification = [];
            if(v.length>0){
                let items = [];
                v.forEach(item=>{
                    let obj = {code:Number(item),value:''};
                    if(Number(item)==5){
                        obj.value=this.qualification;
                    }
                    items.push(obj);
                });
                this.$set(this.matchForm,'qualification',items);
            }else{
                this.qualification="";
            }
        },
        qualification:function(v,o){
            if(v){
                this.matchForm.qualification.forEach(item=>{
                    if(item.code==5){
                        item.value = v;
                    }
                })
            }
        },
        boolLoans:function(v,o){
            this.matchForm.boolLoan = [];
            if(v.length>0){
                let items = [];
                v.forEach(item=>{
                    let obj = {code:Number(item),value:''};
                    if(Number(item)==4){
                        obj.value=this.boolLoan;
                    }
                    items.push(obj);
                });
                this.$set(this.matchForm,'boolLoan',items);
            }else{
                this.boolLoan="";
            }
        },
        boolLoan:function(v,o){
            if(v){
                this.matchForm.boolLoan.forEach(item=>{
                    if(item.code==4){
                        item.value = v;
                    }
                })
            }
        },
    },
    methods:{
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
            

            // let creditType = {code:this.matchForm.creditType,value:this.creditType}
            // params.creditType=creditType;

            // let qualification = {code:this.matchForm.qualification,value:this.qualification}
            // params.qualification=qualification;

            // let industryDirect = {code:this.matchForm.industryDirect,value:this.industryDirect}
            // params.industryDirect=industryDirect;

            // let shareholder = {code:this.matchForm.shareholder,value:this.shareholder}
            // params.shareholder=shareholder;

            // let boolLoan = {code:this.matchForm.boolLoan,value:this.boolLoan}
            // params.boolLoan=boolLoan;



            this.$emit('do-sub',{matchForm:params,data:this.$data});

            // // /finance/financeProduct/getOneKeyMatching
            // this.$http.post('/finance/financeProduct/getOneKeyMatching',params).then(res=>{
            //     console.log(res)
            //     if(res.data.code==0){

            //     }else{
            //         this.$message.error(res.data.msg);
            //     }
            // }).catch(err=>console.log(err))
        },
        getSaveInfo(){
            // 获取保存在服务器的一键匹配数据
            this.$http.postWithAuth('/finance/operateData/findMatchData',{userId:this.$store.state.userInfo.id}).then(res=>{
                this.spinning=false
                if(res.data.code==0){
                    let contentList = res.data.content;
                    if(contentList.length==0){
                        return ;
                    }
                    let infos = contentList.filter(item=>item.operateType==1);
                    if(infos.length==0){
                        return ;
                    }
                    let info = infos[0];
                    // let info = localStorage.getItem('mathData');
                    // info = JSON.parse(info);
                    info.content = JSON.parse(info.content);
                    if(info.operateType==1){
                        this.matchForm = info.content.matchForm;
                        this.isCheckCb= info.content.isCheckCb;
                        this.isCheckJs=info.content.isCheckJs;
                        this.advantage= info.content.advantage;
                        this.creditType= info.content.creditType;//增信方式其他
                        this.creditTypes= info.content.creditTypes;//增信方式其他
                        this.qualification= info.content.qualification;//企业资质其他
                        this.qualifications= info.content.qualifications;//企业资质其他
                        this.industryDirect= info.content.industryDirect;//行业方向其他
                        this.industryDirects= info.content.industryDirects;//行业方向
                        this.shareholder= info.content.shareholder;//股东背景其他
                        this.shareholders= info.content.shareholders;//股东背景其他
                        this.boolLoan= info.content.boolLoan;//是否有其他贷款其他
                        this.boolLoans= info.content.boolLoans;//是否有其他贷款其他
                    } 
                }else{
                    this.$message.error(res.data.msg);
                }
            }).catch()
        },
        saveInfo(){
            // 保存匹配信息
            this.$emit('save-info',{matchForm:this.$data});
        }
    },
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
.save-btn{
    background:orange;
    border:1px solid orange;
    color:white;
    margin-right:5vw;
    width: 96px;
}
</style>