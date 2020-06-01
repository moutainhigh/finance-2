import axios from 'axios';
import store from '../../store';
import router from '../../router';
import {message} from 'ant-design-vue';
// axios.defaults.baseURL = process.env.NODE_ENV=="development"?location.origin:location.origin  //本地  ;
axios.defaults.baseURL = process.env.NODE_ENV=="development"?location.origin:'http://financial.dev.bigdatacd.com:9000';  //本地  ;
axios.defaults.headers = {
  'Content-Type': 'application/json;charset=utf-8',
}
axios.interceptors.response.use((response)=>{
  if(response.data.code==408){
    store.state.token='';
    store.state.userInfo='';
    localStorage.removeItem('cdjr_token');
    localStorage.removeItem('userInfo');
    message.error(response.data.msg);
    router.push({name:"Home",params:{islogin:1}});
    return ;
  }
  if(response.data.code==409){
    store.state.token='';
    store.state.userInfo='';
    localStorage.removeItem('cdjr_token');
    localStorage.removeItem('userInfo');
    message.error(response.data.msg);
    router.push({path:"/"});
    return ;
  }
  return response;
}, function (error) {
  return Promise.reject(error);
});

// 封装请求
export const http = {
    post:(url,params)=>{
        return axios.post(url,params);
    },
    postWithAuth:(url,params)=>{
        return axios.post(url,params,{headers:{'Authorization':store.state.token?'Bearer '+store.state.token:''}});
    }
}


//统一社会信用代码Tyshyxdm
export function creditCode() {
    this.firstarray = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
    this.firstkeys = [3, 7, 9, 10, 5, 8, 4, 2];
    this.secondarray = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'T', 'U', 'W', 'X', 'Y'];
    this.secondkeys = [1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28];
    this.verify = function(str){
        var code = str.toUpperCase();
        /*
        统一社会信用代码由十八位的阿拉伯数字或大写英文字母（不使用I、O、Z、S、V）组成。
         第1位：登记管理部门代码（共一位字符）
         第2位：机构类别代码（共一位字符）
         第3位~第8位：登记管理机关行政区划码（共六位阿拉伯数字）
         第9位~第17位：主体标识码（组织机构代码）（共九位字符）
         第18位：校验码​（共一位字符）
        */
        if (code.length != 18) {
            return false;
        }
        var reg = /^\w\w\d{6}\w{9}\w$/;
        if (!reg.test(code)) {
            return false;
        }
        /*
         登记管理部门代码：使用阿拉伯数字或大写英文字母表示。​
         机构编制：1​
         民政：5​
         工商：9​
         其他：Y
         */
        reg = /^[1,5,9,Y]\w\d{6}\w{9}\w$/;
        if (!reg.test(code)) {
            return false;
        }
        /*
         机构类别代码：使用阿拉伯数字或大写英文字母表示。​
         机构编制机关：11打头​​
         机构编制事业单位：12打头​
         机构编制中央编办直接管理机构编制的群众团体：13打头​​
         机构编制其他：19打头​
         民政社会团体：51打头​
         民政民办非企业单位：52打头​
         民政基金会：53打头​
         民政其他：59打头​
         工商企业：91打头​
         工商个体工商户：92打头​
         工商农民专业合作社：93打头​
         其他：Y1打头​
         */
        reg = /^(11|12|13|19|51|52|53|59|91|92|93|Y1)\d{6}\w{9}\w$/;
        if (!reg.test(code)) {
            return false;
        }
        /*
         登记管理机关行政区划码：只能使用阿拉伯数字表示。按照GB/T 2260编码。​
         例如：四川省成都市本级就是510100；四川省自贡市自流井区就是510302。​
        */
        reg = /^(11|12|13|19|51|52|53|59|91|92|93|Y1)\d{6}\w{9}\w$/;
        if (!reg.test(code)) {
            return false;
        }
        /*
             主体标识码（组织机构代码）：使用阿拉伯数字或英文大写字母表示。按照GB 11714编码。
             在实行统一社会信用代码之前，以前的组织机构代码证上的组织机构代码由九位字符组成。格式为XXXXXXXX-Y。前面八位被称为“本体代码”；最后一位被称为“校验码”。校验码和本体代码由一个连字号（-）连接起来。以便让人很容易的看出校验码。但是三证合一后，组织机构的九位字符全部被纳入统一社会信用代码的第9位至第17位，其原有组织机构代码上的连字号不带入统一社会信用代码。
             原有组织机构代码上的“校验码”的计算规则是：​
             例如：某公司的组织机构代码是：59467239-9。那其最后一位的组织机构代码校验码9是如何计算出来的呢？
             第一步：取组织机构代码的前八位本体代码为基数。5 9 4 6 7 2 3 9
             提示：如果本体代码中含有英文大写字母。则A的基数是10，B的基数是11，C的基数是12，依此类推，直到Z的基数是35。
             第二步：​​取加权因子数值。因为组织机构代码的本体代码一共是八位字符。则这八位的加权因子数值从左到右分别是：3、7、9、10、5、8、4、2。​
             第三步：本体代码基数与对应位数的因子数值相乘。​
             5×3＝15，9×7＝63，4×9＝36，6×10＝60，
             7×5＝35，2×8＝16，3×4=12，9×2＝18​​
             第四步：将乘积求和相加。​
             15+63+36+60+35+16+12+18=255
             第五步：​将和数除以11，求余数。​​
             255÷11=33，余数是2。​​
          */
        var firstkey = this.calc(code.substr(8), this.firstarray, this.firstkeys, 11);
        /*
         第六步：用阿拉伯数字11减去余数，得求校验码的数值。当校验码的数值为10时，校验码用英文大写字母X来表示；当校验码的数值为11时，校验码用0来表示；其余求出的校验码数值就用其本身的阿拉伯数字来表示。​
         11-2＝9，因此此公司完整的组织机构代码为 59467239-9。​​
        */
        var firstword;
        if (firstkey < 10) {
            firstword = firstkey;
        }
        if (firstkey == 10) {
            firstword = 'X';
        } else if (firstkey == 11) {
            firstword = '0';
        }
        if (firstword != code.substr(16, 1)) {
            return false;
        }
 
        /*
        校验码：使用阿拉伯数字或大写英文字母来表示。校验码的计算方法参照 GB/T 17710。
        例如：某公司的统一社会信用代码为91512081MA62K0260E，那其最后一位的校验码E是如何计算出来的呢？
        第一步：取统一社会信用代码的前十七位为基数。9 1 5 1 2 0 8 1 21 10 6 2 19 0 2 6 0提示：如果前十七位统一社会信用代码含有英文大写字母（不使用I、O、Z、S、V这五个英文字母）。则英文字母对应的基数分别为：A=10、B=11、C=12、D=13、E=14、F=15、G=16、H=17、J=18、K=19、L=20、M=21、N=22、P=23、Q=24、R=25、T=26、U=27、W=28、X=29、Y=30​
        第二步：​​取加权因子数值。因为统一社会信用代码前面前面有十七位字符。则这十七位的加权因子数值从左到右分别是：1、3、9、27、19、26、16、17、20、29、25、13、8、24、10、30、2​8
        第三步：基数与对应位数的因子数值相乘。​
        9×1=9，1×3=3，5×9=45，1×27=27，2×19=38，0×26=0，8×16=128​
        1×17=17，21×20=420，10×29=290，6×25=150，2×13=26，19×8=152​
        0×23=0，2×10=20，6×30=180，0×28=0
        第四步：将乘积求和相加。​9+3+45+27+38+0+128+17+420+290+150+26+152+0+20+180+0=1495
        第五步：​将和数除以31，求余数。​​
        1495÷31=48，余数是17。​​
        */
 
        var secondkey = this.calc(code, this.secondarray, this.secondkeys, 31);
        /*
         第六步：用阿拉伯数字31减去余数，得求校验码的数值。当校验码的数值为0~9时，就直接用该校验码的数值作为最终的统一社会信用代码的校验码；
         如果校验码的数值是10~30，则校验码转换为对应的大写英文字母。对应关系为：A=10、B=11、C=12、D=13、E=14、F=15、G=16、H=17、J=18、K=19、L=20、M=21、N=22、P=23、Q=24、R=25、T=26、U=27、W=28、X=29、Y=30
         因为，31-17＝14，所以该公司完整的统一社会信用代码为 91512081MA62K0260E。​​
        */
        var secondword = this.secondarray[secondkey];
        if (!secondword || secondword != code.substr(17, 1)) {
            return false;
        }
        var word = code.substr(0, 16) + firstword + secondword;
        if (code != word) {
            return false;
        }
        return true;
    }
    this.calc = function (code, array1, array2, b) {
        var count = 0;
        for (var i = 0; i < array2.length; i++) {
            var a = code[i];
            count += array2[i] * array1.indexOf(a);
        }
        var remainder = count % b;
        return remainder === 0 ? 0 : b - remainder;
    }
}



function getImageWidthAndHeight(src){
    let img = new Image();
    let loading = new Promise((resolve,reject)=>{
        img.onload = function(){
            let imgAttr = {width:this.width,height:this.height};
            resolve(imgAttr);
        }
    });
    img.src = src;
    return loading;
}
/**
 * 绘制机构
 * @param {要绘制的区域名称} name 
 * @param {要绘制的机构列表} companyList 
 */
export async function drawCompany(driver,name,companyList){
        // 获取当前区域图片
        // let name="彭州市";
        var img = document.querySelector('image[name="'+ name +'"]')
        // 获取当前高亮图层的坐标
        let top = Number(img.style.y);
        let left = Number(img.style.x);
        // 获取当前高亮图层的宽高
        let imgWidthAndHeight = await getImageWidthAndHeight(img.getAttribute('xlink:href'))
        let width = 232;
        let height = 245;
        if(imgWidthAndHeight.width){
            width = imgWidthAndHeight.width;//img.width;
            height = imgWidthAndHeight.height;//img.height; 
        }

        // 当前高亮图层的中心
        let companyObj = {
            cx:left+(width/2),
            cy:top+(height/2),
            r:5,
            layR:10
        }
        // 绘制svg
        let svg = driver.select('#circles');
        // 通过循环创建机构
        if(companyList instanceof Array){
            companyList.forEach((item,index)=>{
                // 通过不同的角度，来设置新的圆心
                let jd = Math.random()*1000;
                let ncx = companyObj.cx + (companyObj.r*2*index) * Math.cos(jd*Math.PI/180); 
                let ncy = companyObj.cy + (companyObj.r*4*index) * Math.sin(jd*Math.PI/180);
                // console.log(ncx,ncy)
                let bgcolor = 'rgba(255, 214, 0, 0.2)';
                let color = 'rgba(255, 214, 0, 0.8)';
                if(item.institutionType==1){
                    bgcolor = "rgba(255, 90, 0,0.2)";
                    color = "rgba(255, 90, 0,0.8)";
                }
                svg.append('circle').attr('cx',ncx).attr('cy',ncy).attr('r',companyObj.layR).style('fill',bgcolor);
                svg.append('circle').attr('cx',ncx).attr('cy',ncy).attr('r',companyObj.r)
                   .attr('comp',item.institutionName).attr('name',name).attr('did',item.id).attr('ctype',item.institutionType)
                   .attr('top','c').style('fill',color).style('cursor','pointer');
            })
        }
        // 给添加的点，添加点击事件监听
        $('circle[top="c"]').click((e)=>{
            $('image[name]').css({opacity:0});
            $('image[name="'+e.target.getAttribute('name')+'"]').css({opacity:1});
            $('.c-info').css({opacity:1,top:'15vw',right:'20vw',transform:'scale(0)'})
            .animate({opacity:1,top:'4vw',right:'3.2vw'},'show','linear').css({transform:'scale(1)'});
            //设置选中动画
            driver.selectAll('circle').attr('class',null);
            driver.select('circle[comp="'+e.target.getAttribute('comp')+'"]').attr('class','seld');
            // 绘画选中点
            drawSeled(e.target.getAttribute('cx'),e.target.getAttribute('cy'));
        }) 
}
/**
 * 绘制选择图片
 * @param {x坐标} cx 
 * @param {y坐标} cy 
 */
export function drawSeled(cx,cy){
    let ims = selsvg.getElementById('#seled');
    if(ims){
      ims.remove();
    }
    ims = document.createElementNS('http://www.w3.org/2000/svg','image')
    ims.setAttribute('x',cx-55);
    ims.setAttribute('y',cy-55);
    ims.setAttribute('width',110);
    ims.setAttribute('height',110);
    ims.setAttribute('id',"#seled");
    ims.href.baseVal = '/image/home/seld.png'
    selsvg.append(ims);
}

/**
 * 分离债券和股权机构
 * @param {机构数据} data 
 */
export function filterCompany(data){
    if(!data instanceof Array){
        return false;
    }
    let gqjg = [];
    let zjjg = [];
    data.forEach(item=>{
        if(item.institutionType==0){
            gqjg.push(item); 
        }else{
            zjjg.push(item); 
        }
    });
    return {gqjg:gqjg,zjjg:zjjg};
}

/**
 * 转化搜索数据为本地
 * @param {要转化的数据} data 
 */
export function matchSearchData(data){
    let dataArr = [];
    // let names = {
    //     'RZED':'融资额度',
    //     'RZJD':'融资阶段',
    //     'HYFX':'行业方向',
    //     'HYFX':'行业方向',
    //     'REGION':'注册地址',
    //     'GDBJ':'股东背景',
    //     'GWRZJE':'过往融资金额',
    //     'SCRLZZL':'实控人创业经历',
    //     'YYSR':'营业收入',
    //     'YYSRZZL':'营业收入增长率',
    //     'CPMLL':'产品毛利率',
    //     'JLL':'净利润',
    //     'YGRS':'员工人数',
    //     'SCRL':'目标市场容量',
    //     'YYSRZZL':'目标市场增长率',
    //     'MBKH':'目标客户',
    //     'SCZYL':'预期市场占有率',
    //     'FMZLSL':'发明专利数量',
    //     'gsjzys':'公司竞争优势',
    //     'GDLJTRZJ':'股东累计投入资金',
    //     'PDCH':'公司所获评定称号',
    // };
    let names = {
        RZED:'融资额度',
        RZJD:'融资阶段',
        HYFX:'行业方向',
        REGION:'注册地区',
        CPJD:'产品阶段',
        CPMLL:'产品毛利率',
        CYJL:'实控人创业经历',
        FMZLSL:'发明专利数量',
        GDBJ:'股东背景',
        GDLJTRZJ:'股东累计投入资金',
        gsjzys:'公司竞争优势',
        GWRZJE:'过往融资金额',
        JLL:'净利率',
        MBKH:'目标客户',
        PDCH:'公司所获评定称号',
        QYZT:'历史创业企业状态',
        SCRL:'市场容量',
        SCRLZZL:'市场容量预期增长率',
        SCZYL:'市场占有率',
        SFHG:'是否接受回购条款',
        SSSJ:'预计上市时间',
        YGRS:'员工人数',
        YYSR:'营业收入',
        YYSRZZL:'营业收入增长率'
    };
    names={
        RZED:'融资额度',
        RZJD:'融资阶段',
        HYFX:'行业方向',
        BOND_FMZLS:'债券融资_发明专利数量',
        BOND_YYSR:'债券融资_营业收入',
        CPJD:'产品阶段',
        CPMLL:'产品毛利率',
        CYJL:'实控人创业经历',
        DKED:'贷款额度',
        DKJE:'现有贷款金额',
        DKQX:'贷款期限',
        FCDY:'是否接受房产抵押',
        GDBJ:'股东背景',
        GDLJTRZJ:'股东累计投入资金',
        GQDDE:'央企/大型国企核心供应商订单额',
        GQTZ:'是否已引入股权投资',
        gsjzys:'公司竞争优势',
        GWRZJE:'过往融资金额',
        JLL:'净利率',
        JLR:'净利润',
        MBKH:'目标客户',
        NRED:'纳税额度',
        PDCH:'公司所获评定称号',
        QTDK:'是否有其他贷款',
        QYZT:'历史创业企业状态',
        QYZZ:'企业资质',
        REGION:'成都地区',
        SCRL:'市场容量',
        SCRLZZL:'市场容量预期增长率',
        SCZYL:'市场占有率',
        SFHG:'是否接受回购条款',
        SSSJ:'预计上市时间',
        STOCKRIGHT_FMZLSL:'股权融资_发明专利数量',
        STOCKRIGHT_YYSR:'股权融资_营业收入',
        XJLJE:'上年度经营活动现金流净额',
        YGRS:'员工人数',
        YYSRZZL:'营业收入增长率',
        ZFBT:'政府补贴',
        ZFDDE:'政府订单额',
        ZXFS:'增信方式'

    }
    if(!(data instanceof Array)){
        return dataArr;
    }else{
        let fields = Object.keys(names);
        // data.forEach(item=>{
        //     let index = fields.indexOf(item.codeType);
        //     if(index!=-1){
        //         item['name'] = names[item.codeType];
        //         dataArr[index]=item;
        //     }
        // });
        for (let item of data){
            let index = fields.indexOf(item.codeType);
            if(index!=-1 && index<4){
                item['name'] = names[item.codeType];
                dataArr[index]=item;
            }else{
                dataArr.push(item) 
            }
        };
        return dataArr.filter(item=>item);
    }
}

/**
 * 存储token
 * @param {要存储的字段} field 
 * @param {要存储的数据} params 
 * @param {过期时间,单位分钟} expire 
 */
export function storage(field,params,expire){
    let date = new Date()
    if(expire && typeof expire != 'number'){
        expire = 2*60;
    }
    let time = Math.floor((date.getTime()+expire*60*1000)/1000);
    localStorage.setItem(field,JSON.stringify({expire:time,params:params}));
}
/**
 * 获取存储的数据
 * @param {获取的数据字段} field 
 */
export function getStorage(field){
    let data = localStorage.getItem(field);
    let date = new Date(); 
    let time = Math.floor((date.getTime())/1000);
    if(data){
        data = JSON.parse(data);
        if(time>data.expire){
            localStorage.removeItem(field);
            return false;
        }else{
            storage(field,data.params,2*60);
            return data.params;
        }
    }else{
        return false;
    }
}

// 发送短信
export async function sendSms(path,params){
    // 正式的发送短信操作
    let res = await axios.post(path,params);
    // 模拟发送成功返回
    // let res = {data:{code:11}}
    return new Promise((resolve,reject)=>{
        if(res.data.code==0){
            resolve(res.data);
        }else{
            console.log(res)
            reject('短信发送失败')
        }
    })
}
// 验证验证码是否有效   /finance/userInfo/verifySmsCode
export async function checkSmsCode(path,params){
    let res = await axios.post(path,params);
    // let res = {data:{code:0}}
    return new Promise((resolve,reject)=>{
        if(res.data.code==0){
            resolve(res.data);
        }else{
            console.log(res)
            reject('验证码错误')
        }
    })
}