import axios from 'axios'
axios.defaults.baseURL = process.env.NODE_ENV=="development"?location.origin:location.origin  //本地  ;
axios.defaults.headers = {
  'Content-Type': 'application/json;charset=utf-8',
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