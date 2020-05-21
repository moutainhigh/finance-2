import { resolve } from "path";

export async function getSearchField($http,path){
    let res = $http.post(path);
    return new Promise((resolve,reject)=>{
        console.log(res)
        if(res.data && res.data.code==0){
            resolve(res.content);
        }else{
            let msg = '请求异常';
            if(res.data && res.data.msg){
                msg = res.data.msg
            }
            reject(msg);
        }
    })
}