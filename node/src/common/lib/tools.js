import axios from 'axios'
axios.defaults.baseURL = process.env.NODE_ENV=="development"?location.origin:'http://financial.dev.bigdatacd.com:9000';
axios.defaults.headers = {
  'Content-Type': 'application/json;charset=utf-8',
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
                   .attr('comp',item.institutionName).attr('name',name).attr('did',item.id)
                   .attr('top','c').style('fill',color).style('cursor','pointer');
            })
        }
        // 给添加的点，添加点击事件监听
        $('circle[top="c"]').click((e)=>{
            $('image[name]').css({opacity:0});
            $('image[name="'+e.target.getAttribute('name')+'"]').css({opacity:1});
            $('.c-info').css({opacity:1,top:'15vw',right:'20vw',transform:'scale(0)'})
            .animate({opacity:1,top:'4vw',right:'3.2vw'},'show','linear').css({transform:'scale(1)'});
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