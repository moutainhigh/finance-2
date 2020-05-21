export async function getSearchField($http,path,params){
    let res = await $http.post(path,params);
    return new Promise((resolve,reject)=>{
        console.log(res)
        if(res.data && res.data.code==0){
            resolve(res.data.content);
        }else{
            let msg = '请求异常';
            if(res.data && res.data.msg){
                msg = res.data.msg
            }
            reject(msg);
        }
    })
}