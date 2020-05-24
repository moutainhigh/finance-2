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
                    <a-form-model-item ref="mobile" label="注册地址" prop="mobile" class="input-item">
                        <a-select v-model="baseForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="不限">不限</a-select-option>
                          <a-select-option key="" value="不限">不限</a-select-option>
                        </a-select> 
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="办公地址" prop="mobile" class="input-item">
                        <a-input v-model="baseForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="联系人" prop="mobile" class="input-item">
                        <a-input v-model="baseForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
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
                    <a-form-model-item ref="mobile" label="公司简介" prop="mobile" class="input-item" :wrapper-col="{ span: 12, offset: 0 }">
                        <a-textarea v-model="baseForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
            </a-form-model>
        </a-card>
        <a-card title="业务信息" class="card-work">
            <a-form-model ref="ruleForm" :model="matchForm" :rules="rules" :label-col="{span:4}" :wrapper-col="{span:12}">
                    <a-form-model-item ref="mobile" label="融资阶段" prop="mobile" class="input-item" >
                        <a-select v-model="matchForm.mobile"  @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="未融资">未融资</a-select-option>
                          <a-select-option key="" value="种子期">种子期</a-select-option>
                          <a-select-option key="" value="天使期">天使期</a-select-option>
                          <a-select-option key="" value="成长期">成长期</a-select-option>
                          <a-select-option key="" value="成熟期">成熟期</a-select-option>
                          <a-select-option key="" :value="1">其它</a-select-option>
                        </a-select>
                        <a-input v-show="matchForm.mobile==1" placeholder="请输入其他的内容" @blur=" 
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="融资额度" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile"  @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="100万以内">100万以内</a-select-option>
                          <a-select-option key="" value="100-200万">100-200万</a-select-option>
                          <a-select-option key="" value="200-500万">200-500万</a-select-option>
                          <a-select-option key="" value="500-1000万">500-1000万</a-select-option>
                          <a-select-option key="" value="1000万以上">1000万以上</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="行业方向" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="未融资">医药/医疗器械</a-select-option>
                          <a-select-option key="" value="电子信息">电子信息</a-select-option>
                          <a-select-option key="" value="轨道交通">轨道交通</a-select-option>
                          <a-select-option key="" value="大数据">大数据</a-select-option>
                          <a-select-option key="" value="互联网">互联网</a-select-option>
                          <a-select-option key="" value="新经济">新经济</a-select-option>
                          <a-select-option key="" value="军民融合">军民融合</a-select-option>
                          <a-select-option key="" value="人工智能">人工智能</a-select-option>
                          <a-select-option key="" value="新经济">新经济</a-select-option>
                          <a-select-option key="" :value="1">其它</a-select-option>
                        </a-select>
                        <a-input v-show="matchForm.mobile==1" placeholder="请输入其他的内容" @blur=" 
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="股东背景" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="无">无</a-select-option>
                          <a-select-option key="" value="副教授">副教授</a-select-option>
                          <a-select-option key="" value="正教授">正教授</a-select-option>
                          <a-select-option key="" value="国家千人">国家千人</a-select-option>
                          <a-select-option key="" value="省千人">省千人</a-select-option>
                          <a-select-option key="" value="其他人才">其他人才</a-select-option>
                          <a-select-option key="" value="上市公司高管">上市公司高管</a-select-option>
                          <a-select-option key="" value="上市公司中层">上市公司中层</a-select-option>
                          <a-select-option key="" :value="1">其它</a-select-option>
                        </a-select>
                        <a-input v-show="matchForm.mobile==1" placeholder="请输入其他的内容" @blur=" 
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="产品阶段" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="研发">研发</a-select-option>
                          <a-select-option key="" value="小试">小试</a-select-option>
                          <a-select-option key="" value="中试">中试</a-select-option>
                          <a-select-option key="" value="扩大生产">扩大生产</a-select-option>
                          <a-select-option key="" value="不涉及">不涉及</a-select-option>
                          <a-select-option key="" :value="1">其它</a-select-option>
                        </a-select>
                        <a-input v-show="matchForm.mobile==1" placeholder="请输入其他的内容" @blur=" 
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            "
                        />
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="营业收入" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="无">无</a-select-option>
                          <a-select-option key="" value="100万以内">100万以内</a-select-option>
                          <a-select-option key="" value="100-500万元">100-500万元</a-select-option>
                          <a-select-option key="" value="1000-5000万元">1000-5000万元</a-select-option>
                          <a-select-option key="" value="5000万元以上">5000万元以上</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="营业收入增长率" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="10%以内">10%以内</a-select-option>
                          <a-select-option key="" value="10%-20%">10%-20%</a-select-option>
                          <a-select-option key="" value="20%-30%">20%-30%</a-select-option>
                          <a-select-option key="" value="30%-50%">30%-50%</a-select-option>
                          <a-select-option key="" value="50%以上">50%以上</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="产品毛利率/预期产品毛利率" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="10%以内">10%以内</a-select-option>
                          <a-select-option key="" value="10%-20%">10%-20%</a-select-option>
                          <a-select-option key="" value="20%-30%">20%-30%</a-select-option>
                          <a-select-option key="" value="30%-50%">30%-50%</a-select-option>
                          <a-select-option key="" value="50%以上">50%以上</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="净利润" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="100万以内">100万以内</a-select-option>
                          <a-select-option key="" value="100-200万">100-200万</a-select-option>
                          <a-select-option key="" value="200-500万">200-500万</a-select-option>
                          <a-select-option key="" value="500-1000万">500-1000万</a-select-option>
                          <a-select-option key="" value="1000万以上">1000万以上</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="过往融资金额" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="无">无</a-select-option>
                          <a-select-option key="" value="100万以内">100万以内</a-select-option>
                          <a-select-option key="" value="100-200万">100-200万</a-select-option>
                          <a-select-option key="" value="200-500万">200-500万</a-select-option>
                          <a-select-option key="" value="500-1000万">500-1000万</a-select-option>
                          <a-select-option key="" value="1000万以上">1000万以上</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="实控人创业经历" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="无">无</a-select-option>
                          <a-select-option key="" value="1次">1次</a-select-option>
                          <a-select-option key="" value="2次">2次</a-select-option>
                          <a-select-option key="" value="3次">3次</a-select-option>
                          <a-select-option key="" value="3次以上">3次以上</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="历史创业企业状态" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="注销">注销</a-select-option>
                          <a-select-option key="" value="存续">存续</a-select-option>
                          <a-select-option key="" value="获得融资">获得融资</a-select-option>
                          <a-select-option key="" value="被并购">被并购</a-select-option>
                          <a-select-option key="" value="pre-IPO">pre-IPO</a-select-option>
                          <a-select-option key="" value="已上市">已上市</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="员工人数" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="1-10人">1-10人</a-select-option>
                          <a-select-option key="" value="10-50人">10-50人</a-select-option>
                          <a-select-option key="" value="50-100人">50-100人</a-select-option>
                          <a-select-option key="" value="100-500人">100-500人</a-select-option>
                          <a-select-option key="" value="500人以上">500人以上</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="市场容量" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="0-10亿元">0-10亿元</a-select-option>
                          <a-select-option key="" value="10-30亿元">10-30亿元</a-select-option>
                          <a-select-option key="" value="30-50亿元">30-50亿元</a-select-option>
                          <a-select-option key="" value="50亿元以上">50亿元以上</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="市场容量预期增长率" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="0-10%">0-10%</a-select-option>
                          <a-select-option key="" value="10-20%">10-20%</a-select-option>
                          <a-select-option key="" value="20%-30%">20%-30%</a-select-option>
                          <a-select-option key="" value="30%-50%">30%-50%</a-select-option>
                          <a-select-option key="" value="50%以上">50%以上</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="目标客户" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="个人">个人</a-select-option>
                          <a-select-option key="" value="企业">企业</a-select-option>
                          <a-select-option key="" value="政府">政府</a-select-option>
                          <a-select-option key="" value="军工">军工</a-select-option>
                          <a-select-option key="" :value="1">其它</a-select-option>
                          <a-input v-show="matchForm.mobile==1" placeholder="请输入其他的内容" @blur=" 
                                () => {
                                    $refs.mobile.onFieldBlur();
                                }
                                "
                            />
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="市场占有率/预期市场占有率" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="0-10%">0-10%</a-select-option>
                          <a-select-option key="" value="10%-30%">10%-30%</a-select-option>
                          <a-select-option key="" value="30%-50%">30%-50%</a-select-option>
                          <a-select-option key="" value="50%以上">50%以上</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="是否接受回购条款" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="1">是</a-select-option>
                          <a-select-option key="" value="0">否</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="发明专利数量" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="无">无</a-select-option>
                          <a-select-option key="" value="1-3个">1-3个</a-select-option>
                          <a-select-option key="" value="3-10个">3-10个</a-select-option>
                          <a-select-option key="" value="10个以上">10个以上</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="公司竞争优势" prop="mobile" class="input-item">
                        <a-checkbox @change="onChange">成本优势</a-checkbox>
                        <a-select v-model="matchForm.mobile" style="width:5vw;" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="成本优势">成本优势</a-select-option>
                          <a-select-option key="" value="渠道优势">渠道优势</a-select-option>
                          <a-select-option key="" value="先发优势">先发优势</a-select-option>
                          <a-select-option key="" value="资质优势">资质优势</a-select-option>
                          <a-select-option key="" value="其它">其它</a-select-option>
                        </a-select>
                        <a-checkbox @change="onChange" style="margin-left:0.3vw;">技术优势</a-checkbox>
                        <a-select v-model="matchForm.mobile" style="width:5vw;" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="成本优势">成本优势</a-select-option>
                          <a-select-option key="" value="渠道优势">渠道优势</a-select-option>
                          <a-select-option key="" value="先发优势">先发优势</a-select-option>
                          <a-select-option key="" value="资质优势">资质优势</a-select-option>
                          <a-select-option key="" value="其它">其它</a-select-option>
                        </a-select>
                        <a-checkbox-group :options="['渠道优势','先发优势','资质优势']" style="margin-left:0.3vw;"></a-checkbox-group>
                        <a-checkbox @change="onChange" style="margin-left:0.3vw;" v-model="matchForm.mobile" >其他</a-checkbox>
                        <a-input v-show="matchForm.mobile" style="width:5vw;margin-left:0.3vw;"></a-input>
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="股东累计投入资金" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="无投入">无投入</a-select-option>
                          <a-select-option key="" value="0-100万元">0-100万元</a-select-option>
                          <a-select-option key="" value="0-100万元">0-100万元</a-select-option>
                          <a-select-option key="" value="500-1000万">500-1000万</a-select-option>
                          <a-select-option key="" value="1000-3000万">1000-3000万</a-select-option>
                          <a-select-option key="" value="1000-3000万">1000-3000万</a-select-option>
                          <a-select-option key="" value="3000万元以上">3000万元以上</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="预计上市时间" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="1-2年">1-2年</a-select-option>
                          <a-select-option key="" value="2-3年">2-3年</a-select-option>
                          <a-select-option key="" value="3-5年">3-5年</a-select-option>
                          <a-select-option key="" value="5年以上">5年以上</a-select-option>
                        </a-select>
                    </a-form-model-item>
                    <a-form-model-item ref="mobile" label="公司所获评定称号" prop="mobile" class="input-item">
                        <a-select v-model="matchForm.mobile" @blur="
                            () => {
                                $refs.mobile.onFieldBlur();
                            }
                            ">
                          <a-select-option key="" value="瞪羚企业">瞪羚企业</a-select-option>
                          <a-select-option key="" value="独角兽企业">独角兽企业</a-select-option>
                          <a-select-option key="" value="平台生态型龙头企业">平台生态型龙头企业</a-select-option>
                          <a-select-option key="" value="不涉及">不涉及</a-select-option>
                          <a-select-option key="" :value="1">其他</a-select-option>
                        </a-select>
                        <a-input v-show="matchForm.mobile==1" key="" placeholder="请输入其他内容"></a-input>
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
export default {
    data(){
        return {
            isLogin:false,
            isReg:false,
            isForget:false,
            active:0,
            baseForm:{
                mobile:''
            },
            baseRules:{
                mobile:[
                    {required:true,message:'请输入联系电话',trigger:'blur'}
                ]
            },
            matchForm:{
                mobile:''
            },
            rules:{
                mobile:[
                    {required:true,message:'请输入联系电话',trigger:'blur'}
                ]
            },
            options:['成本优势','渠道优势','先发优势','资质优势']
        }
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
        onChange(){

        },
        onSubmit(){

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