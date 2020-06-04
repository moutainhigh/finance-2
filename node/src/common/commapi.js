export async function getSearchField($http,path,params){
    let res = await $http.post(path,params);
    return new Promise((resolve,reject)=>{
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

// 废弃基本没用
export const mapData = new Map([
    ['RZED','financeQuota'],
    ['RZJD','financeState'],
    ['HYFX','IndustryDirect'],
    ['REGION','registerAddress'],
    ['YYSR','business'],
    ['YGRS','staffCount'],
    ['SCZYL','marketOccupyRate'],
    ['PDCH','evaluateName'],
    ['CPJD','productState'],
    ['CPMLL','productRate'],
    ['CYJL','experience'],
    ['FMZLSL','patentCount'],
    ['GDBJ','shareholder'],
    ['GDLJTRZJ','capitals'],
    ['gsjzys','advantage'],
    ['GWRZJE','oldFinanceQuota'],
    ['JLL','netInterestRate'],
    ['MBKH','targetCustomer'],
    ['QYZT','companyStatus'],
    ['SCRL','marketCapacity'],
    ['SCRLZZL','marketAddRate'],
    ['SFHG','boolBuyBack'],
    ['SSSJ','timeToMarket'],
    ['YYSRZZL','businessAddRate'],
]);