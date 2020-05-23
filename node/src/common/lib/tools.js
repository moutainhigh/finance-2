import axios from 'axios'
axios.defaults.baseURL = process.env.NODE_ENV=="development"?location.origin:'http://financial.dev.bigdatacd.com:9000';
axios.defaults.headers = {
  'Content-Type': 'application/json;charset=utf-8',
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
    let names = {'RZED':'融资额度','RZJD':'融资阶段','HYFX':'行业方向'};
    if(!(data instanceof Array)){
        return dataArr;
    }else{
        let fields = Object.keys(names);
        data.forEach(item=>{
            let index = fields.indexOf(item.codeType);
            if(index!=-1){
                item['name'] = names[item.codeType];
                dataArr[index]=item;
            }
        });
        return dataArr;
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
            storage(field,date.params,2*60);
            return date.params;
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